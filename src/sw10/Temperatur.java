package sw10;

/**
 * Immutable Temperatur-Klasse (U09, Aufgabe 2).
 *
 * Features:
 *  - FINAL Klasse (nicht spezialisierbar)
 *  - FINAL Attribut (immutable object)
 *  - PRIVATE Konstruktor -> Objekte nur via Factory-Methoden erzeugbar
 *  - Factory-Methoden: createFromCelsius(), createFromKelvin()
 *  - Validation: wirft IllegalArgumentException bei T &lt; -273.15 C (absoluter Nullpunkt)
 *  - KEINE Setter mehr (bei Aenderung: neues Objekt erzeugen!)
 */
public final class Temperatur {

    // Konstante (static final, UPPERCASE)
    public static final float KELVIN_OFFSET = 273.15f;

    // Attribut immutable (final) - nur im Konstruktor setzbar
    private final float kelvin;

    /**
     * Privater Konstruktor! Objekte nur via Factory-Methoden erzeugbar.
     *
     * @param kelvin Absolute Temperatur in Kelvin (&gt;= 0)
     * @throws IllegalArgumentException wenn kelvin &lt; 0
     */
    private Temperatur(final float kelvin) {
        if (kelvin < 0f) {
            throw new IllegalArgumentException(
                "Temperatur unter absolutem Nullpunkt: " + kelvin + " K");
        }
        this.kelvin = kelvin;
    }

    // ======================================================
    // FACTORY-METHODEN (U09, Aufgabe 2c + 2d)
    // ======================================================

    /**
     * Erzeugt eine Temperatur aus einem Celsius-Wert.
     *
     * @param celsius Temperatur in Celsius
     * @return neues Temperatur-Objekt
     * @throws IllegalArgumentException wenn celsius &lt; -273.15
     */
    public static Temperatur createFromCelsius(final float celsius) {
        // Ruft internen Konstruktor auf - minimiert Code-Redundanz
        return new Temperatur(celsius + KELVIN_OFFSET);
    }

    /**
     * Erzeugt eine Temperatur aus einem Kelvin-Wert.
     *
     * @param kelvin Temperatur in Kelvin
     * @return neues Temperatur-Objekt
     * @throws IllegalArgumentException wenn kelvin &lt; 0
     */
    public static Temperatur createFromKelvin(final float kelvin) {
        return new Temperatur(kelvin);
    }

    // ======================================================
    // GETTER (keine Setter! Immutable!)
    // ======================================================

    public float getCelsius() {
        return this.kelvin - KELVIN_OFFSET;
    }

    public float getKelvin() {
        return this.kelvin;
    }

    public float getFahrenheit() {
        return getCelsius() * 9f / 5f + 32f;
    }

    // ======================================================
    // equals / hashCode / toString
    // ======================================================

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Temperatur other = (Temperatur) obj;
        return Float.compare(this.kelvin, other.kelvin) == 0;
    }

    @Override
    public int hashCode() {
        return Float.hashCode(this.kelvin);
    }

    @Override
    public String toString() {
        return String.format("Temperatur[%.2f C / %.2f K]", getCelsius(), this.kelvin);
    }
}
