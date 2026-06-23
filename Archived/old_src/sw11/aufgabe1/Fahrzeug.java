package sw11.aufgabe1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import sw04.Licht;
import sw04.Switchable;

/**
 * SW11 / U10 Aufgabe 1: Fahrzeug als EVENT-LISTENER.
 *
 * Lerneffekt:
 *  - Listener werden, indem man EIN Interface implementiert (PropertyChangeListener)
 *  - Genau EINE Methode implementieren: propertyChange(event)
 *  - Im Event steht: Quelle (getSource), Property-Name, alter Wert, neuer Wert
 */
public class Fahrzeug implements Switchable, PropertyChangeListener {

    private final Motor motor;
    private final Licht licht;
    private boolean isGlobalOn;

    public Fahrzeug() {
        this.motor = new Motor();
        this.licht = new Licht();
        this.isGlobalOn = false;

        // WICHTIG (Aufgabe k): Listener bei der Quelle registrieren!
        // Ohne diese Zeile waere die ganze Event-Mechanik wirkungslos.
        this.motor.addPropertyChangeListener(this);
    }

    @Override
    public void switchOn() {
        this.motor.switchOn();
        this.licht.switchOn();
        this.isGlobalOn = true;
    }

    @Override
    public void switchOff() {
        this.motor.switchOff();
        this.licht.switchOff();
        this.isGlobalOn = false;
    }

    @Override
    public boolean isSwitchedOn() {
        return this.isGlobalOn;
    }

    public Motor getMotor() {
        return this.motor;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        System.out.printf(
                "[Fahrzeug] Motor-Property '%s' geaendert: %s -> %s (Quelle: %s)%n",
                event.getPropertyName(),
                event.getOldValue(),
                event.getNewValue(),
                event.getSource().getClass().getSimpleName()
        );
    }
}
