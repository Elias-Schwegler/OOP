package sw05;

/**
 * Übung SW05, Aufgabe 1.e) & 1.f)
 * Quecksilber (Mercury) – flüssig bei Raumtemperatur.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public final class Mercury extends ChemicalElement {

    public Mercury() {
        super("Quecksilber", "Hg", 80);
    }

    @Override
    public String getAggregateState() {
        return "flüssig";
    }

    @Override
    public double getBoilingPoint() {
        return 357.0;
    }
}
