package sw05;

/**
 * Übung SW05, Aufgabe 1.e) & 1.f)
 * Abstrakte Basisklasse für chemische Elemente.
 * Definiert gemeinsame Eigenschaften (Name, Symbol, Ordnungszahl)
 * und erzwingt die Angabe des Aggregatzustands bei Raumtemperatur.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public abstract class ChemicalElement {

    private final String name;
    private final String symbol;
    private final int atomicNumber;

    /**
     * Konstruktor für ein chemisches Element.
     *
     * @param name         Name des Elements (z.B. "Stickstoff").
     * @param symbol       Chemisches Symbol (z.B. "N").
     * @param atomicNumber Ordnungszahl im Periodensystem.
     */
    protected ChemicalElement(final String name, final String symbol, final int atomicNumber) {
        this.name = name;
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
    }

    /**
     * Liefert den Aggregatzustand bei Raumtemperatur.
     * Abstrakt: Muss von jedem konkreten Element implementiert werden.
     *
     * @return Aggregatzustand als String ("fest", "flüssig", "gasförmig").
     */
    public abstract String getAggregateState();

    /**
     * Liefert den Siedepunkt in Grad Celsius.
     * @return Siedepunkt.
     */
    public abstract double getBoilingPoint();

    public final String getName() {
        return this.name;
    }

    public final String getSymbol() {
        return this.symbol;
    }

    public final int getAtomicNumber() {
        return this.atomicNumber;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.symbol + ", Z=" + this.atomicNumber + ")"
                + " – " + getAggregateState() + " bei Raumtemperatur"
                + ", Siedepunkt: " + getBoilingPoint() + "°C";
    }
}
