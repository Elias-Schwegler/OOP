package sw04;

/**
 * Übung SW04, Aufgabe 2.e) & 2.f) & 2.g)
 * Repräsentiert ein Fahrzeug, das aus verschiedenen Switchable-Komponenten besteht.
 * Das Fahrzeug selbst ist auch Switchable, womit Fragen zur Polymorphie beantwortet werden.
 */
public class Fahrzeug implements Switchable {

    private Motor motor;
    private Licht licht;
    private boolean isGlobalOn;

    /**
     * Erstellt ein neues Fahrzeug mit Standard-Komponenten.
     */
    public Fahrzeug() {
        this.motor = new Motor();
        this.licht = new Licht();
        this.isGlobalOn = false;
    }

    /**
     * Schaltet das gesamte Fahrzeug (und alle seine Komponenten) ein.
     */
    @Override
    public void switchOn() {
        this.motor.switchOn();
        this.licht.switchOn();
        this.isGlobalOn = true;
    }

    /**
     * Schaltet das gesamte Fahrzeug (und alle seine Komponenten) aus.
     */
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

    /**
     * Gibt den Motor-Zustand zurück. Zeigt Polymorphie auf dem Switchable Interface.
     */
    public Switchable getMotorAsSwitchable() {
        return this.motor;
    }

    public int getMotorRpm() {
        return this.motor.getRpm();
    }
}
