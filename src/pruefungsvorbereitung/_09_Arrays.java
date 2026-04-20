package pruefungsvorbereitung;

import java.util.Arrays;

/**
 * TEMPLATE 09 – Arrays und for-Schleifen.
 *
 * Wichtig:
 *  - Array ist KEINE Collection -> eigene Syntax
 *  - Feste Groesse, einmal festgelegt, nicht aenderbar
 *  - .length ist ein ATTRIBUT (kein Methodenaufruf)
 *  - Indizes: 0 bis length-1 (NICHT bis length!)
 *  - Arrays speichern primitive Typen UND Objekte direkt
 *
 * Typische Fehler:
 *  - arr[arr.length] -> ArrayIndexOutOfBoundsException
 *  - arr.size() -> falsch! Es ist arr.length
 *  - new String[10] erzeugt 10 null-Referenzen, NICHT 10 Strings
 */
public class _09_Arrays {

    public static void main(String[] args) {
        demoDeklaration();
        demoIteration();
        demoUtilities();

        int[] test = {5, 23, 1, 42, 17, 8, 42};
        System.out.println("\nMaximum:       " + findMax(test));
        System.out.println("Index von Max: " + findMaxIndex(test));
        System.out.println("Summe:         " + summe(test));
        System.out.println("Durchschnitt:  " + durchschnitt(test));
    }

    // ======================================================
    // DEKLARATION + ERZEUGUNG
    // ======================================================
    private static void demoDeklaration() {
        System.out.println("=== Arrays: Deklaration ===");

        // Variante 1: Deklaration, dann Erzeugung
        int[] a;
        a = new int[5];  // alle Elemente sind 0

        // Variante 2: kompakt
        int[] b = new int[3];

        // Variante 3: mit Initialisierer
        int[] primzahlen = {2, 3, 5, 7, 11, 13};

        // Variante 4: explizit
        String[] namen = new String[] {"Alice", "Bob", "Charlie"};

        // Array von Objekten: alle initial null!
        String[] leer = new String[3];  // [null, null, null]

        // 2D-Array
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("a.length:         " + a.length);
        System.out.println("primzahlen[0]:    " + primzahlen[0]);
        System.out.println("matrix[1][2]:     " + matrix[1][2]);  // 6
        System.out.println("matrix.length:    " + matrix.length); // Zeilenanzahl
        System.out.println("matrix[0].length: " + matrix[0].length); // Spaltenanzahl
    }

    // ======================================================
    // ITERATION – for, for-each, while
    // ======================================================
    private static void demoIteration() {
        System.out.println("\n=== Arrays: Iteration ===");
        int[] zahlen = {10, 20, 30, 40, 50};

        // Klassisch for – mit Index!
        System.out.print("for:       ");
        for (int i = 0; i < zahlen.length; i++) {
            System.out.print(i + "=" + zahlen[i] + " ");
        }
        System.out.println();

        // for-each – ohne Index, einfacher
        System.out.print("for-each:  ");
        for (int z : zahlen) {
            System.out.print(z + " ");
        }
        System.out.println();

        // Rueckwaerts iterieren
        System.out.print("backwards: ");
        for (int i = zahlen.length - 1; i >= 0; i--) {
            System.out.print(zahlen[i] + " ");
        }
        System.out.println();
    }

    // ======================================================
    // Arrays.x – nuetzliche Utilities
    // ======================================================
    private static void demoUtilities() {
        System.out.println("\n=== Arrays.x ===");
        int[] a = {5, 2, 8, 1, 9};

        System.out.println("toString: " + Arrays.toString(a));

        int[] kopie = Arrays.copyOf(a, a.length);
        Arrays.sort(kopie);
        System.out.println("sortiert: " + Arrays.toString(kopie));

        int pos = Arrays.binarySearch(kopie, 5);  // braucht sortiert!
        System.out.println("binarySearch(5): Index " + pos);

        boolean gleich = Arrays.equals(a, new int[]{5, 2, 8, 1, 9});
        System.out.println("equals: " + gleich);

        int[] gefuellt = new int[5];
        Arrays.fill(gefuellt, 42);
        System.out.println("fill:     " + Arrays.toString(gefuellt));
    }

    // ======================================================
    // KLASSISCHE ALGORITHMEN
    // ======================================================

    /** Findet das groesste Element im Array. */
    public static int findMax(final int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array leer!");
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /** Findet den Index des ersten Vorkommens des Maximums. */
    public static int findMaxIndex(final int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int maxIdx = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    /** Summiert alle Werte. */
    public static int summe(final int[] array) {
        int sum = 0;
        for (int v : array) {  // for-each reicht!
            sum += v;
        }
        return sum;
    }

    /** Durchschnitt. */
    public static double durchschnitt(final int[] array) {
        if (array.length == 0) return 0.0;
        return (double) summe(array) / array.length;
    }
}
