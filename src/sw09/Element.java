package sw09;

/**
 * Chemisches Element mit Schmelz- und Siedepunkt (U08, Aufgabe b-e).
 * Demonstriert: Enum-Verwendung, switch-Statement.
 */
public class Element {
    private final String name;
    private final String symbol;
    private final float meltingPoint;  // in Celsius
    private final float boilingPoint;  // in Celsius

    public Element(String name, String symbol, float meltingPoint, float boilingPoint) {
        this.name = name;
        this.symbol = symbol;
        this.meltingPoint = meltingPoint;
        this.boilingPoint = boilingPoint;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public float getMeltingPoint() { return meltingPoint; }
    public float getBoilingPoint() { return boilingPoint; }

    /**
     * Bestimmt den Aggregatzustand bei einer gegebenen Temperatur.
     * @param tempCelsius Temperatur in Celsius
     * @return Aggregatzustand (SOLID, LIQUID oder GAS)
     */
    public AggregateState getStateAt(float tempCelsius) {
        if (tempCelsius < meltingPoint) {
            return AggregateState.SOLID;
        } else if (tempCelsius < boilingPoint) {
            return AggregateState.LIQUID;
        } else {
            return AggregateState.GAS;
        }
    }

    /**
     * Gibt eine lesbare Beschreibung des Zustands bei einer Temperatur aus.
     * Nutzt die erweiterte Enum mit getGermanName() (Aufgabe e).
     */
    public String describeStateAt(float tempCelsius) {
        AggregateState state = getStateAt(tempCelsius);
        return name + " (" + symbol + ") ist " + state.getGermanName()
                + " bei " + tempCelsius + " Grad C";
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ") [Schmelzpunkt: " + meltingPoint
                + " C, Siedepunkt: " + boilingPoint + " C]";
    }
}
