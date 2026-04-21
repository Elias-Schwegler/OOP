package sw10;

import java.util.Scanner;

/**
 * Aufgabe 1 aus U09: Exception-Handling Grundlagen.
 *
 * Original-Gerust (ohne Exception Handling) wuerde mit Stacktrace abbrechen,
 * sobald der User etwas eingibt, das keine Zahl ist (z.B. "abc").
 * -> Float.valueOf() wirft NumberFormatException (UNCHECKED, RuntimeException).
 *
 * Diese Version:
 *  - try/catch fangt NumberFormatException ab
 *  - Programm laeuft weiter bei ungueltiger Eingabe
 *  - "exit" beendet das Programm sauber
 *  - Bei gueltiger Eingabe wird die Zahl ausgegeben
 *
 * Zum Testen in IntelliJ: Rechtsklick -> Run. Im Terminal unten Werte eingeben:
 *   20.5     -> "Temperatur: 20.5"
 *   abc      -> "FEHLER: Ungueltige Eingabe: abc"
 *   -300     -> "FEHLER: Temperatur unter absolutem Nullpunkt"
 *   exit     -> "Programm beendet."
 */
public class Sw10KonsolenDemo {

    public static void main(String[] args) {
        String input = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Bitte Temperatur eingeben (oder 'exit' zum Beenden): ");
            input = scanner.next();

            if ("exit".equals(input)) {
                break;   // Abbruch der Schleife
            }

            // ===== EXCEPTION HANDLING =====
            try {
                // Float.valueOf kann NumberFormatException werfen!
                // Wir verwenden gleich unsere Temperatur-Klasse (Aufgabe 1f)
                float value = Float.parseFloat(input);
                Temperatur t = Temperatur.createFromCelsius(value);
                System.out.println("  OK -> " + t);

            } catch (NumberFormatException nfe) {
                // Eingabe war keine Zahl
                System.out.println("  FEHLER: Ungueltige Eingabe: '" + input + "'");
                System.out.println("          (bitte Zahl eingeben, z.B. 20.5)");

            } catch (IllegalArgumentException iae) {
                // Temperatur unter absolutem Nullpunkt
                System.out.println("  FEHLER: " + iae.getMessage());

            } finally {
                // Wird IMMER ausgefuehrt - auch bei Exception, auch bei "exit"
                // (hier eher zu Demo-Zwecken)
                // System.out.println("  [finally: Eingabe fertig verarbeitet]");
            }

        } while (!"exit".equals(input));

        scanner.close();
        System.out.println("Programm beendet.");
    }
}
