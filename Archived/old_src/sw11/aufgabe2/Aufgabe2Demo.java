package sw11.aufgabe2;

import java.util.Scanner;

import sw11.aufgabe2.events.TemperaturEvent;
import sw11.aufgabe2.interfaces.ITemperaturListener;

/**
 * SW11 / U10 Aufgabe 2: Konsoleneingabe + eigene Events.
 *
 * Aufruf (vom Verzeichnis "src" aus):
 * <pre>
 *   javac sw11/aufgabe2/events/*.java sw11/aufgabe2/interfaces/*.java sw11/aufgabe2/*.java
 *   java sw11.aufgabe2.Aufgabe2Demo
 * </pre>
 *
 * Beenden: "exit" eingeben.
 */
public final class Aufgabe2Demo {

    private Aufgabe2Demo() {
        // Utility-Klasse
    }

    public static void main(final String[] args) {
        System.out.println("=== SW11 Aufgabe 2: Eigene Events (Min/Max) ===");
        System.out.println("Gib Temperaturwerte in Celsius ein. 'exit' beendet das Programm.\n");

        final TemperaturVerlauf verlauf = new TemperaturVerlauf();

        // ANONYME INNERE KLASSE als Listener (Aufgabe h).
        verlauf.addTemperaturListener(new ITemperaturListener() {
            @Override
            public void temperaturChanged(final TemperaturEvent event) {
                if (event.getType() == TemperaturEvent.Type.MAX) {
                    System.out.printf("  >>> Neues MAXIMUM: %.2f C%n", event.getWertCelsius());
                } else {
                    System.out.printf("  >>> Neues MINIMUM: %.2f C%n", event.getWertCelsius());
                }
            }
        });

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Temperatur (C): ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                final String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                try {
                    final float celsius = Float.parseFloat(line.replace(',', '.'));
                    verlauf.add(celsius);
                } catch (final NumberFormatException e) {
                    System.out.println("  [Fehler] Keine gueltige Zahl: " + line);
                }
            }
        }

        System.out.println("\n=== Statistik ===");
        if (verlauf.getCount() == 0) {
            System.out.println("Keine Werte erfasst.");
        } else {
            System.out.printf("Anzahl Punkte:  %d%n", verlauf.getCount());
            System.out.printf("Minimum:        %.2f C%n", verlauf.getMin());
            System.out.printf("Maximum:        %.2f C%n", verlauf.getMax());
            System.out.printf("Durchschnitt:   %.2f C%n", verlauf.getAverage());
        }
    }
}
