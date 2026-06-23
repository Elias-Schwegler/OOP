package sw06;

/**
 * Übung SW06, Aufgabe 2.a)
 * Einfache Hilfsklasse mit max()-Methoden fuer Unit-Testing-Uebung.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public final class MaxUtil {

    private MaxUtil() {
        // Utility-Klasse, keine Instanziierung
    }

    /**
     * Liefert das Maximum von zwei Ganzzahlen.
     *
     * @param a Erster Wert.
     * @param b Zweiter Wert.
     * @return Der groessere der beiden Werte.
     */
    public static int max(int a, int b) {
        if (a >= b) {
            return a;
        }
        return b;
    }

    /**
     * Liefert das Maximum von drei Ganzzahlen.
     *
     * @param a Erster Wert.
     * @param b Zweiter Wert.
     * @param c Dritter Wert.
     * @return Der groesste der drei Werte.
     */
    public static int max(int a, int b, int c) {
        return max(a, max(b, c));
    }
}
