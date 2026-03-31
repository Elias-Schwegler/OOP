package sw06;

/**
 * Übung SW06, Aufgabe 1.e) & 1.f)
 * Kopie aus SW05, erweitert um toString()-Überschreibung.
 * Abstrakte Basisklasse fuer chemische Elemente.
 *
 * @author Elias Schwegler
 * @version 2.0
 */
public abstract class ChemicalElement {

    private final String name;
    private final String symbol;
    private final int atomicNumber;

    /**
     * Konstruktor fuer ein chemisches Element.
     *
     * @param name         Name des Elements.
     * @param symbol       Chemisches Symbol.
     * @param atomicNumber Ordnungszahl.
     */
    protected ChemicalElement(final String name, final String symbol, final int atomicNumber) {
        this.name = name;
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
    }

    /**
     * Liefert den Aggregatzustand bei Raumtemperatur.
     *
     * @return Aggregatzustand als String.
     */
    public abstract String getAggregateState();

    /**
     * Liefert den Siedepunkt in Grad Celsius.
     *
     * @return Siedepunkt.
     */
    public abstract double getBoilingPoint();

    public final String getName() { return this.name; }
    public final String getSymbol() { return this.symbol; }
    public final int getAtomicNumber() { return this.atomicNumber; }

    /**
     * Aufgabe 1.f) Ueberschreiben von Object.toString().
     * Liefert eine lesbare Darstellung des chemischen Elements.
     *
     * @return Textuelle Beschreibung des Elements.
     */
    @Override
    public String toString() {
        return this.name + " (" + this.symbol + ", Z=" + this.atomicNumber + ")"
                + " – " + getAggregateState() + " bei Raumtemperatur"
                + ", Siedepunkt: " + getBoilingPoint() + " °C";
    }
}
