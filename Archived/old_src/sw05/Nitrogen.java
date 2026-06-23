package sw05;

/**
 * Übung SW05, Aufgabe 1.e) & 1.f)
 * Stickstoff (Nitrogen) – gasförmig bei Raumtemperatur.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public final class Nitrogen extends ChemicalElement {

    public Nitrogen() {
        super("Stickstoff", "N", 7);
    }

    @Override
    public String getAggregateState() {
        return "gasförmig";
    }

    @Override
    public double getBoilingPoint() {
        return -196.0;
    }
}
