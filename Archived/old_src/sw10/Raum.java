package sw10;

/**
 * Klasse Raum (U09, Optionale Repetitionsaufgabe Teil 1).
 *
 * Features laut Aufgabenstellung:
 *  - FINAL (nicht spezialisierbar)
 *  - Immutable (beide Attribute final, keine Setter)
 *  - Raumnummer (100-999), sonst IllegalArgumentException
 *  - Platzanzahl (&gt; 2), sonst IllegalArgumentException
 *  - equals-Contract: Zwei Raeume sind gleich wenn Raumnummer identisch
 *
 * Hinweis: Die Aufgabe sagt "package-private Konstruktor" (Package ch.hslu.oop.rv).
 * Hier in unserem flachen sw10-Package nutze ich public, aber die Idee bleibt:
 * Die Sichtbarkeit wuerde im echten HSLU-Setup via Maven-Template eingeschraenkt.
 */
public final class Raum {

    private final int raumnummer;
    private final int kapazitaet;

    /**
     * Erzeugt einen Raum.
     *
     * @param raumnummer 100 &lt;= raumnummer &lt;= 999
     * @param kapazitaet &gt; 2
     * @throws IllegalArgumentException bei ungueltigen Werten
     */
    public Raum(final int raumnummer, final int kapazitaet) {
        if (raumnummer < 100 || raumnummer > 999) {
            throw new IllegalArgumentException(
                "Raumnummer muss zwischen 100 und 999 sein, war: " + raumnummer);
        }
        if (kapazitaet <= 2) {
            throw new IllegalArgumentException(
                "Kapazitaet muss groesser als 2 sein, war: " + kapazitaet);
        }
        this.raumnummer = raumnummer;
        this.kapazitaet = kapazitaet;
    }

    public int getRaumnummer() {
        return this.raumnummer;
    }

    public int getKapazitaet() {
        return this.kapazitaet;
    }

    // equals-Contract: Gleich wenn Raumnummer identisch (Aufgabe 4d)
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Raum other = (Raum) obj;
        return this.raumnummer == other.raumnummer;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.raumnummer);
    }

    @Override
    public String toString() {
        return "Raum " + raumnummer + " (Kap: " + kapazitaet + ")";
    }
}
