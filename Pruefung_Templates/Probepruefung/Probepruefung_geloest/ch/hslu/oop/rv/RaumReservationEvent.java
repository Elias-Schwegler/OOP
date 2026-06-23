package ch.hslu.oop.rv;

import java.util.EventObject;
import java.util.Objects;

/**
 * Event, das bei einer Reservation oder Freigabe eines Raums ausgeloest wird.
 *
 * <h2>WHY extends EventObject?</h2>
 * <p>Das Java-Event-Modell (wie z.B. bei AWT/Swing) baut auf
 * {@link java.util.EventObject} auf. Diese Basisklasse haelt die "source"
 * (das Objekt, das das Event ausgeloest hat). Indem wir ableiten, fuegen wir
 * nur die fachlich relevanten Zusatzinfos hinzu: welcher Raum und wie viele
 * Plaetze betroffen sind.</p>
 *
 * <p>Das Event ist immutable (Felder final, keine Setter). WHY? Ein Event
 * beschreibt etwas, das passiert ist - es soll von Listenern nicht veraendert
 * werden koennen.</p>
 */
public class RaumReservationEvent extends EventObject {

    // WHY serialVersionUID: EventObject ist Serializable. Eine explizite UID
    // vermeidet Warnungen und macht die Serialisierung stabil.
    private static final long serialVersionUID = 1L;

    private final Raum raum;
    private final int anzahlPlaetze;

    /**
     * @param source        die Event-Quelle (typisch die {@link RaumVerwaltung}).
     * @param raum          der betroffene Raum, darf nicht {@code null} sein.
     * @param anzahlPlaetze Anzahl betroffener Plaetze (z.B. reservierte Personen).
     */
    public RaumReservationEvent(final Object source, final Raum raum, final int anzahlPlaetze) {
        // WHY super(source): EventObject verlangt die Quelle im Konstruktor und
        // wirft selbst eine IllegalArgumentException, falls source null ist.
        super(source);
        this.raum = Objects.requireNonNull(raum, "raum darf nicht null sein");
        this.anzahlPlaetze = anzahlPlaetze;
    }

    /**
     * @return der von der Reservation/Freigabe betroffene Raum.
     */
    public Raum getRaum() {
        return raum;
    }

    /**
     * @return die Anzahl betroffener Plaetze.
     */
    public int getAnzahlPlaetze() {
        return anzahlPlaetze;
    }

    @Override
    public String toString() {
        return "RaumReservationEvent[raum=%s, anzahlPlaetze=%d]".formatted(raum, anzahlPlaetze);
    }
}
