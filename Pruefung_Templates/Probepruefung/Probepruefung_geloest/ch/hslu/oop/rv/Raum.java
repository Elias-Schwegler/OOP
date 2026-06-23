package ch.hslu.oop.rv;

import java.util.Objects;

/**
 * Repraesentiert einen einzelnen Raum mit Raumnummer, Kapazitaet und Status.
 *
 * <h2>Design-Entscheidungen (WHY)</h2>
 * <ul>
 *   <li><b>final class</b>: Der Raum soll laut Aufgabe NICHT spezialisierbar
 *       sein. {@code final} verbietet das Ableiten (kein {@code extends Raum}).</li>
 *   <li><b>Immutable Kerndaten</b>: {@code raumnummer} und {@code kapazitaet}
 *       sind {@code final} und werden nur im Konstruktor gesetzt. Sie aendern
 *       sich waehrend der Lebenszeit des Raums nie -> Objekt ist bzgl. dieser
 *       Daten unveraenderlich. Das macht die Identitaet (Raumnummer) stabil,
 *       was wichtig fuer {@link #equals(Object)}/{@link #hashCode()} ist.</li>
 *   <li><b>status ist NICHT immutable</b>: Der Status veraendert sich bewusst
 *       (FREI -&gt; BELEGT -&gt; FREI). Das ist gewollter, kontrollierter
 *       Zustand. Der Setter ist deshalb package-private, damit nur Klassen im
 *       Package (z.B. {@link RaumVerwaltung}) den Status steuern.</li>
 *   <li><b>Konstruktor package-private</b>: Nur Klassen im Package
 *       {@code ch.hslu.oop.rv} duerfen Raeume erzeugen. Aussenstehender Code
 *       muss den Umweg ueber die {@link RaumVerwaltung} gehen -&gt;
 *       Datenkapselung / kontrollierte Erzeugung.</li>
 * </ul>
 */
public final class Raum {

    // WHY final: einmal gesetzt, nie wieder geaendert (Immutability der Kerndaten).
    private final int raumnummer;
    private final int kapazitaet;

    // WHY nicht final: der Status ist der einzige veraenderliche Zustand.
    private RaumStatus status;

    /**
     * Erzeugt einen neuen Raum. Package-private, damit nur Klassen im Package
     * {@code ch.hslu.oop.rv} Raeume erzeugen koennen.
     *
     * @param raumnummer Raumnummer, gueltig im Bereich 100..999.
     * @param kapazitaet maximale Platzanzahl, muss groesser als 2 sein.
     * @throws IllegalArgumentException wenn die Werte ausserhalb des gueltigen
     *                                  Bereichs liegen.
     */
    Raum(final int raumnummer, final int kapazitaet) {
        // WHY Validierung im Konstruktor: Ein Objekt soll niemals in einem
        // ungueltigen Zustand existieren ("fail fast"). Wir pruefen, BEVOR wir
        // die finalen Felder setzen, sodass kein kaputtes Objekt entsteht.
        if (raumnummer < 100 || raumnummer > 999) {
            throw new IllegalArgumentException(
                    "Raumnummer muss im Bereich 100..999 liegen, war: " + raumnummer);
        }
        if (kapazitaet <= 2) {
            throw new IllegalArgumentException(
                    "Kapazitaet muss groesser als 2 sein, war: " + kapazitaet);
        }
        this.raumnummer = raumnummer;
        this.kapazitaet = kapazitaet;
        // WHY: Neue Raeume sind laut Aufgabe immer FREI.
        this.status = RaumStatus.FREI;
    }

    /**
     * @return die Raumnummer (eindeutige Identitaet des Raums).
     */
    public int getRaumnummer() {
        return raumnummer;
    }

    /**
     * @return die maximale Platzanzahl des Raums.
     */
    public int getKapazitaet() {
        return kapazitaet;
    }

    /**
     * @return den aktuellen Status. Getter ist public: jeder darf den Status
     *         lesen.
     */
    public RaumStatus getStatus() {
        return status;
    }

    /**
     * Setzt den Status. WHY package-private: nur Klassen im Package (z.B.
     * {@link RaumVerwaltung}) duerfen den Status aendern. Von aussen ist der
     * Status nur lesbar (Datenkapselung).
     *
     * @param status der neue Status, darf nicht {@code null} sein.
     */
    void setStatus(final RaumStatus status) {
        this.status = Objects.requireNonNull(status, "status darf nicht null sein");
    }

    /**
     * @return {@code true}, wenn der Raum aktuell frei (reservierbar) ist.
     */
    public boolean istFrei() {
        return status == RaumStatus.FREI;
    }

    /**
     * Zwei Raeume gelten als gleich, wenn sie dieselbe Raumnummer haben.
     *
     * <p>WHY nur raumnummer? Die Raumnummer ist die fachliche Identitaet (ID)
     * eines Raums. Kapazitaet und Status sind Eigenschaften, aber nicht die
     * Identitaet. Ein Raum bleibt "derselbe" Raum, auch wenn sich sein Status
     * aendert.</p>
     */
    @Override
    public boolean equals(final Object obj) {
        // WHY pattern matching (Java 16+): kompakter als instanceof + Cast.
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Raum other)) {
            return false;
        }
        return this.raumnummer == other.raumnummer;
    }

    /**
     * Konsistent zu {@link #equals(Object)}: nur die Raumnummer fliesst ein.
     *
     * <p>WHY Contract? Gleiche Objekte (laut equals) MUESSEN den gleichen
     * hashCode liefern, sonst funktionieren HashMap/HashSet nicht korrekt.</p>
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(raumnummer);
    }

    /**
     * Kompaktes, fuer Logging geeignetes Format.
     *
     * <p>WHY kompakt? In Log-Zeilen will man auf einen Blick die wichtigsten
     * Infos sehen, ohne mehrzeilige Ausgaben.</p>
     */
    @Override
    public String toString() {
        return "Raum[nr=%d, kap=%d, status=%s]".formatted(raumnummer, kapazitaet, status);
    }
}
