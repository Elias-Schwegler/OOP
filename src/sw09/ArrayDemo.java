package sw09;

/**
 * Demonstriert Array-Konzepte aus Kapitel 7.
 * Arrays deklarieren, erzeugen, befuellen, durchlaufen.
 */
public class ArrayDemo {

    /**
     * Demonstriert Array-Grundlagen: Deklaration, Erzeugung, Zugriff.
     */
    public void basicArrayDemo() {
        System.out.println("--- Array Grundlagen ---");

        // Deklaration + Erzeugung
        int[] zugriffeProStunde = new int[24];

        // Befuellen (simulierte Weblog-Daten)
        zugriffeProStunde[0] = 12;
        zugriffeProStunde[8] = 145;
        zugriffeProStunde[12] = 230;
        zugriffeProStunde[17] = 189;
        zugriffeProStunde[23] = 34;

        // Ausgabe mit for-Schleife
        System.out.println("  Stunde: Zugriffe");
        for (int i = 0; i < zugriffeProStunde.length; i++) {
            if (zugriffeProStunde[i] > 0) {
                System.out.println("  " + i + ":00 Uhr: " + zugriffeProStunde[i]);
            }
        }

        // Gesamtzugriffe mit for-each
        int gesamt = 0;
        for (int zugriffe : zugriffeProStunde) {
            gesamt += zugriffe;
        }
        System.out.println("  Gesamt: " + gesamt + " Zugriffe");
    }

    /**
     * Findet das Maximum in einem int-Array.
     * Typische Pruefungsaufgabe!
     */
    public int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array darf nicht leer sein!");
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Findet den Index des Maximums.
     * Braucht for-Schleife (nicht for-each), weil Index benoetigt wird.
     */
    public int findMaxIndex(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Demonstriert den Konditionaloperator (Ternary Operator).
     */
    public void ternaryDemo() {
        System.out.println("\n--- Konditionaloperator ? : ---");
        for (int stunde = 0; stunde < 5; stunde++) {
            // Konditionaloperator: fuehrende Null bei einstelligen Zahlen
            String anzeige = (stunde < 10) ? "0" + stunde : "" + stunde;
            System.out.println("  " + anzeige + ":00");
        }
    }

    /**
     * Demonstriert Array-Initialisierer.
     */
    public void initializerDemo() {
        System.out.println("\n--- Array-Initialisierer ---");
        String[] wochentage = {"Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"};
        int[] primzahlen = {2, 3, 5, 7, 11, 13, 17, 19};

        System.out.print("  Wochentage: ");
        for (String tag : wochentage) {
            System.out.print(tag + " ");
        }
        System.out.println();

        System.out.print("  Primzahlen: ");
        for (int p : primzahlen) {
            System.out.print(p + " ");
        }
        System.out.println();
    }
}
