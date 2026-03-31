package sw06;

/**
 * Blei – fest bei Raumtemperatur.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public final class Lead extends ChemicalElement {

    public Lead() {
        super("Blei", "Pb", 82);
    }

    @Override
    public String getAggregateState() {
        return "fest";
    }

    @Override
    public double getBoilingPoint() {
        return 1749.0;
    }
}
