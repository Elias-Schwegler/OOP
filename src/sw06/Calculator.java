package sw06;

/**
 * Übung SW06, Aufgabe 3.b)
 * Interface fuer einen einfachen Taschenrechner.
 * Definiert die Schnittstelle fuer arithmetische Operationen.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public interface Calculator {

    /**
     * Addiert zwei ganzzahlige Summanden.
     *
     * @param a Erster Summand.
     * @param b Zweiter Summand.
     * @return Summe von a und b.
     */
    int addition(int a, int b);
}
