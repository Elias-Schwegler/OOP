package sw03;

/**
 * Übungen SW03: Kontrollstrukturen (Selektionen und Iterationen)
 */
public class Sw03Demo {

    // =========================================================================
    // 1. Einfache Bedingungen mit if-Statements
    // =========================================================================

    /**
     * Übung SW03, Aufgabe 1.3 a.)
     * Implementieren Sie je eine Methode int max(int a, int b) welche den maximalen Wert zurückliefert.
     */
    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Übung SW03, Aufgabe 1.3 b.)
     * Schreiben Sie eine Methode int max(int a, int b, int c).
     * Variante 1: Nur if-Statements verwenden.
     */
    public int max1(int a, int b, int c) {
        int maximum = a;
        if (b > maximum) {
            maximum = b;
        }
        if (c > maximum) {
            maximum = c;
        }
        return maximum;
    }

    /**
     * Übung SW03, Aufgabe 1.3 b.) & c.)
     * Lösung von a) wiederverwenden. Bester Ansatz, da Logik wiederverwendet wird.
     * Hinterfragen kritisch: Sehr kompakter und sauberer Code.
     */
    public int max2(int a, int b, int c) {
        return max(max(a, b), c);
    }

    /**
     * Übung SW03, Aufgabe 1.3 d.), e.) & f.)
     * Temperatur-Klasse erweitern mit getAggregatszustand für chemische Elemente.
     * Implementiert mit einem switch-Statement (Aufgabe e).
     * 
     * Vorteile von switch gegenüber if-else bei Strings:
     * - Bessere Lesbarkeit und klarere Struktur
     * - Verhindert Fehler beim String-Vergleich (kein vergessenes .equals())
     */
    public String getAggregatszustand(String element, float celsius) {
        return switch (element) {
            case "N" -> { // Stickstoff: Schmelzpunkt -210°C, Siedepunkt -195.8°C
                if (celsius < -210.0f) yield "fest";
                if (celsius < -195.8f) yield "flüssig";
                yield "gasförmig";
            }
            case "Hg" -> { // Quecksilber: Schmelzpunkt -38.8°C, Siedepunkt 356.7°C
                if (celsius < -38.8f) yield "fest";
                if (celsius < 356.7f) yield "flüssig";
                yield "gasförmig";
            }
            case "Pb" -> { // Blei: Schmelzpunkt 327.4°C, Siedepunkt 1749°C
                if (celsius < 327.4f) yield "fest";
                if (celsius < 1749.0f) yield "flüssig";
                yield "gasförmig";
            }
            default -> "unbekanntes Element";
        };
    }

    // =========================================================================
    // 2. Iterationen mit while-, do-while- und for-Schleifen
    // =========================================================================

    /**
     * Übung SW03, Aufgabe 2.3 a.) & b.)
     * Ausgabe der Zahlen 0-10 mit verschiedenen Schleifen.
     * for-Schleife ist hier am besten, da die Anzahl der Iterationen im Voraus bekannt ist.
     */
    public void printNumbers() {
        System.out.println("--- FOR LOOP --- (Best choice for known iterations)");
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }

        System.out.println("--- WHILE LOOP ---");
        int j = 0;
        while (j <= 10) {
            System.out.println(j);
            j++;
        }

        System.out.println("--- DO-WHILE LOOP --- (Worst choice here, implies code executes at least once regardless of condition)");
        int k = 0;
        do {
            System.out.println(k);
            k++;
        } while (k <= 10);
    }

    /**
     * Übung SW03, Aufgabe 2.3 c.) & d.)
     * Demonstration des Float-Additionsproblems (Endlosschleife!).
     * Warum? Float-Zahlen sind nicht absolut präzise. 0.000025f lässt sich binär nicht
     * exakt abbilden. Die Summe trifft NIEMALS exakt 1.0f!
     * 
     * ACHTUNG: Auskommentiert oder mit Not-Ausstieg gesichert, damit VSCode nicht abstürzt.
     */
    public void dangerousFloatLoop() {
        System.out.println("--- DANGEROUS FLOAT LOOP ---");
        float value = 0.9f;
        int iterations = 0;
        
        // FALSCH: while (value != 1.0f) -> Wird zu einer Endlosschleife!
        // KORREKT: while (value < 1.0f)
        while (value < 1.0f && iterations < 10000) { // Not-Ausstieg bei 10000!
            value += 0.000025f;
            iterations++;
        }
        
        // Erwartet: (1.0 - 0.9) / 0.000025 = 0.1 / 0.000025 = 4000 Iterationen
        System.out.println("Iterationen (erwartet 4000): " + iterations);
        System.out.println("Endwert: " + value);
    }

    /**
     * Übung SW03, Aufgabe 2.3 e.)
     * Sichere Alternative mit for-Schleife (genau 4000 Iterationen).
     * Zeigt, dass der Float-Wert danach wegen Ungenauigkeiten NICHT genau 1.0f ist!
     */
    public void safeForLoop() {
        System.out.println("--- SAFE FOR LOOP ---");
        float value = 0.9f;
        for (int i = 0; i < 4000; i++) {
            value += 0.000025f;
        }
        System.out.println("Wert nach 4000 Iterationen (Sollte 1.0f sein): " + value);
        // Ergebnis ist meistens etwas wie 1.0000001
    }

    /**
     * Übung SW03, Aufgabe 2.3 f.)
     * Ausgabe einer Box mit ASCII-Art.
     */
    public void printBox(final int width, final int height) {
        System.out.println("--- printBox(" + width + ", " + height + ") ---");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Nur am Rand ein '#' drucken
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println(); // Zeilenumbruch
        }
    }
}
