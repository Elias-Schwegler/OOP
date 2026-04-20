package sw09;

/**
 * Enumeration fuer Aggregatzustaende (U08, Aufgabe a + d).
 * Demonstriert: Enum mit Attributen und Methoden.
 */
public enum AggregateState {
    SOLID("fest"),
    LIQUID("fluessig"),
    GAS("gasfoermig");

    private final String germanName;

    private AggregateState(final String germanName) {
        this.germanName = germanName;
    }

    public String getGermanName() {
        return this.germanName;
    }

    @Override
    public String toString() {
        return this.germanName;
    }
}
