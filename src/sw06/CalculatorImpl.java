package sw06;

/**
 * Übung SW06, Aufgabe 3.c) & 3.f)
 * Konkrete Implementation des Calculator-Interfaces.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public class CalculatorImpl implements Calculator {

    /**
     * Addiert zwei ganzzahlige Summanden.
     * Hinweis (Aufgabe 3.g): Bei sehr grossen Werten kann ein Integer-Overflow auftreten!
     * z.B. Integer.MAX_VALUE + 1 ergibt Integer.MIN_VALUE.
     *
     * @param a Erster Summand.
     * @param b Zweiter Summand.
     * @return Summe von a und b.
     */
    @Override
    public int addition(int a, int b) {
        return a + b;
    }
}
