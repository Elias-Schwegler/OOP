package ch.hslu.oop.rv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Verwaltet eine Menge von {@link Raum}-Objekten und kuemmert sich um
 * Reservation und Freigabe. Zusaetzlich ist die Verwaltung Event-Quelle:
 * bei Reservation und Freigabe feuert sie {@link RaumReservationEvent}s.
 *
 * <h2>WHY TreeMap&lt;Integer, Raum&gt;?</h2>
 * <p>Wir brauchen zwei Eigenschaften gleichzeitig:</p>
 * <ul>
 *   <li><b>Sortiert nach Raumnummer</b>: TreeMap haelt die Schluessel
 *       (Raumnummern) automatisch in aufsteigender Reihenfolge.</li>
 *   <li><b>Schneller Zugriff per Raumnummer</b>: TreeMap bietet
 *       {@code get(key)} in O(log n).</li>
 * </ul>
 * <p>Eine reine Liste waere nicht direkt nach Schluessel adressierbar, eine
 * HashMap waere nicht sortiert. TreeMap vereint beides.</p>
 */
public final class RaumVerwaltung {

    // WHY TreeMap: sortiert nach Raumnummer + O(log n)-Zugriff per Nummer.
    private final TreeMap<Integer, Raum> raeume = new TreeMap<>();

    // WHY List fuer Listener: einfache, geordnete Sammlung von Beobachtern.
    private final List<RaumReservationListener> listeners = new ArrayList<>();

    /**
     * Erzeugt die Verwaltung und legt einen festen Satz von Test-Raeumen an.
     *
     * <p>WHY hier? Die Aufgabe verlangt diese konkreten Raeume direkt im
     * Konstruktor. In einer echten Anwendung kaeme das aus einer Datenbank.</p>
     */
    public RaumVerwaltung() {
        // Raumnummer(Kapazitaet): 600(18), 602(6), 603(12), 605(24), 610(12)
        fuegeHinzu(new Raum(600, 18));
        fuegeHinzu(new Raum(602, 6));
        fuegeHinzu(new Raum(603, 12));
        fuegeHinzu(new Raum(605, 24));
        fuegeHinzu(new Raum(610, 12));
    }

    /**
     * Fuegt einen Raum hinzu. Package-private Hilfsmethode: nur intern bzw.
     * vom Package genutzt.
     */
    private void fuegeHinzu(final Raum raum) {
        raeume.put(raum.getRaumnummer(), raum);
    }

    /**
     * Liefert den Raum mit der gegebenen Raumnummer.
     *
     * <p>WHY Exception statt Optional? Hier ist die Entscheidung bewusst:
     * Wer gezielt {@code getRaum(605)} aufruft, geht in der Regel davon aus,
     * dass dieser Raum existiert. Ein nicht vorhandener Raum ist dann ein
     * Programmierfehler/Sonderfall -&gt; eine {@link NoSuchElementException}
     * macht den Fehler sofort sichtbar (fail fast), statt ihn still mit einem
     * leeren Optional zu verschleiern. Fuer "vielleicht vorhanden"-Abfragen
     * waere Optional die bessere Wahl.</p>
     *
     * @param raumnummer die gesuchte Raumnummer.
     * @return der gefundene Raum (nie {@code null}).
     * @throws NoSuchElementException wenn kein Raum mit dieser Nummer existiert.
     */
    public Raum getRaum(final int raumnummer) {
        final Raum raum = raeume.get(raumnummer);
        if (raum == null) {
            throw new NoSuchElementException("Kein Raum mit Nummer " + raumnummer);
        }
        return raum;
    }

    /**
     * Reserviert den KLEINSTEN noch passenden freien Raum fuer die gegebene
     * Personenzahl und setzt ihn auf {@link RaumStatus#BELEGT}.
     *
     * <p>WHY kleinster passender? So werden grosse Raeume fuer grosse Gruppen
     * freigehalten ("best fit"). Wuerde man einfach den ersten passenden
     * nehmen, koennte eine 3er-Gruppe den 24er-Raum blockieren.</p>
     *
     * <p>Bei Erfolg wird ein {@link RaumReservationEvent} gefeuert.</p>
     *
     * @param anzahlPersonen Anzahl Personen, fuer die ein Raum gesucht wird.
     * @return der reservierte Raum.
     * @throws IllegalArgumentException wenn {@code anzahlPersonen <= 0}.
     * @throws NoSuchElementException   wenn kein passender freier Raum existiert.
     */
    public Raum reserviere(final int anzahlPersonen) {
        if (anzahlPersonen <= 0) {
            throw new IllegalArgumentException(
                    "anzahlPersonen muss positiv sein, war: " + anzahlPersonen);
        }

        // WHY Stream: liest sich wie die fachliche Anforderung -
        // "alle Raeume, die frei sind UND gross genug, davon den kleinsten".
        final Raum gewaehlt = raeume.values().stream()
                .filter(Raum::istFrei)
                .filter(r -> r.getKapazitaet() >= anzahlPersonen)
                .min(Comparator.comparingInt(Raum::getKapazitaet))
                .orElseThrow(() -> new NoSuchElementException(
                        "Kein freier Raum fuer " + anzahlPersonen + " Personen verfuegbar"));

        gewaehlt.setStatus(RaumStatus.BELEGT);
        fireEvent(new RaumReservationEvent(this, gewaehlt, anzahlPersonen));
        return gewaehlt;
    }

    /**
     * Gibt den uebergebenen Raum wieder frei (setzt ihn auf
     * {@link RaumStatus#FREI}).
     *
     * <p>WHY boolean als Rueckgabe? Der Aufrufer will wissen, ob die Freigabe
     * tatsaechlich etwas bewirkt hat. {@code true} = Raum war belegt und ist
     * jetzt frei; {@code false} = Raum war bereits frei (oder gesperrt), es gab
     * nichts freizugeben.</p>
     *
     * @param raum der freizugebende Raum, darf nicht {@code null} sein.
     * @return {@code true}, wenn der Raum vorher belegt war und freigegeben
     *         wurde, sonst {@code false}.
     */
    public boolean gibFrei(final Raum raum) {
        Objects.requireNonNull(raum, "raum darf nicht null sein");
        // WHY nur aus BELEGT freigeben: einen GESPERRTen Raum soll gibFrei
        // nicht versehentlich oeffnen.
        if (raum.getStatus() != RaumStatus.BELEGT) {
            return false;
        }
        raum.setStatus(RaumStatus.FREI);
        fireEvent(new RaumReservationEvent(this, raum, raum.getKapazitaet()));
        return true;
    }

    /**
     * Ueberladung: gibt den Raum mit der gegebenen Raumnummer frei.
     *
     * <p>WHY Ueberladung? Bequemlichkeit fuer den Aufrufer, der nur die Nummer
     * kennt. Intern wird {@link #getRaum(int)} und {@link #gibFrei(Raum)}
     * wiederverwendet (kein duplizierter Code).</p>
     *
     * @param raumnummer die Nummer des freizugebenden Raums.
     * @return {@code true}, wenn der Raum vorher belegt war und freigegeben
     *         wurde, sonst {@code false}.
     * @throws NoSuchElementException wenn kein Raum mit dieser Nummer existiert.
     */
    public boolean gibFrei(final int raumnummer) {
        return gibFrei(getRaum(raumnummer));
    }

    /**
     * @return eine nach Raumnummer sortierte, unveraenderliche Liste aller
     *         Raeume. WHY Kopie? Damit der Aufrufer die interne Datenstruktur
     *         nicht von aussen manipulieren kann (Datenkapselung).
     */
    public List<Raum> getAlleRaeume() {
        // WHY List.copyOf: liefert eine unveraenderliche Kopie. values() ist
        // dank TreeMap bereits nach Raumnummer sortiert.
        return List.copyOf(raeume.values());
    }

    // ----------------------------------------------------------------------
    // Event-Mechanismus (Beobachter-Muster / Observer)
    // ----------------------------------------------------------------------

    /**
     * Registriert einen Listener fuer Reservations-/Freigabe-Events.
     *
     * @param listener der zu registrierende Listener, darf nicht {@code null}
     *                 sein.
     */
    public void addListener(final RaumReservationListener listener) {
        listeners.add(Objects.requireNonNull(listener, "listener darf nicht null sein"));
    }

    /**
     * Entfernt einen zuvor registrierten Listener.
     *
     * @param listener der zu entfernende Listener.
     */
    public void removeListener(final RaumReservationListener listener) {
        listeners.remove(listener);
    }

    /**
     * Feuert ein Event an alle registrierten Listener.
     *
     * <p>WHY private? Nur die Verwaltung selbst entscheidet, wann ein Event
     * ausgeloest wird. Aussenstehender Code darf keine Events erfinden.</p>
     *
     * <p>WHY Kopie der Liste? Falls ein Listener waehrend der Benachrichtigung
     * sich selbst ab-/anmeldet, wuerde das eine
     * {@link java.util.ConcurrentModificationException} verursachen. Das
     * Iterieren ueber eine Kopie ist robust dagegen.</p>
     */
    private void fireEvent(final RaumReservationEvent event) {
        for (final RaumReservationListener listener : new ArrayList<>(listeners)) {
            listener.onReservation(event);
        }
    }
}
