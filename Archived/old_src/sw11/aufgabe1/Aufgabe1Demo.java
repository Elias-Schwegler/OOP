package sw11.aufgabe1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * SW11 / U10 Aufgabe 1: Demo zum PropertyChangeListener-Pattern.
 *
 * Aufruf (vom Verzeichnis "src" aus):
 * <pre>
 *   javac sw04/*.java sw11/aufgabe1/*.java
 *   java sw11.aufgabe1.Aufgabe1Demo
 * </pre>
 */
public final class Aufgabe1Demo {

    private Aufgabe1Demo() {
        // Utility-Klasse
    }

    public static void main(final String[] args) {
        System.out.println("=== SW11 Aufgabe 1: PropertyChangeListener-Pattern ===\n");

        final Fahrzeug fahrzeug = new Fahrzeug();
        final Motor motor = fahrzeug.getMotor();

        // Anonyme Klasse als zusaetzlicher Listener.
        motor.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent event) {
                System.out.printf("[Anonymer Listener] %s: %s -> %s%n",
                        event.getPropertyName(),
                        event.getOldValue(),
                        event.getNewValue());
            }
        });

        // Lambda-Listener (PropertyChangeListener ist ein Functional Interface).
        final PropertyChangeListener lambdaListener = e ->
                System.out.printf("[Lambda] Motor ist jetzt %s%n",
                        ((Boolean) e.getNewValue()) ? "AN" : "AUS");
        motor.addPropertyChangeListener(lambdaListener);

        System.out.println("--- Motor einschalten ---");
        motor.switchOn();
        System.out.println("RPM: " + motor.getRpm() + "\n");

        System.out.println("--- Motor nochmal einschalten (kein Event!) ---");
        motor.switchOn();
        System.out.println();

        System.out.println("--- Lambda entfernen, dann ausschalten ---");
        motor.removePropertyChangeListener(lambdaListener);
        motor.switchOff();
        System.out.println("RPM: " + motor.getRpm());
    }
}
