package sw06;

/**
 * Übung SW06, Aufgabe 1.g)
 * Quecksilber – fluessig bei Raumtemperatur.
 * Ueberschreibt toString() mit Warnhinweis "GIFTIG" unter Verwendung von super.
 *
 * @author Elias Schwegler
 * @version 2.0
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

    /**
     * Aufgabe 1.g) toString() mit Warnhinweis.
     * Verwendet super.toString() um Coderedundanz zu vermeiden,
     * und ergaenzt den String mit "GIFTIG".
     *
     * @return Textuelle Beschreibung mit Warnhinweis.
     */
    @Override
    public String toString() {
        return super.toString() + " *** GIFTIG ***";
    }
}
