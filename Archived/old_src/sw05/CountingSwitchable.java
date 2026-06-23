package sw05;

import sw04.Switchable;

/**
 * Übung SW05, Aufgabe 1.g)
 * Spezialisierung von Switchable, die die Anzahl Schaltvorgänge mitzählt.
 * Demonstriert Interface-Vererbung mit extends (nicht implements!).
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public interface CountingSwitchable extends Switchable {

    /**
     * Liefert die Gesamtanzahl der Ein-/Ausschaltvorgänge.
     * @return Anzahl Schaltvorgänge.
     */
    long getSwitchCount();
}
