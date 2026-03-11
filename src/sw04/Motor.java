package sw04;

/**
 * Übung SW04, Aufgabe 2.c) & 2.d)
 * Ein Motor, der ein- und ausgeschaltet werden kann und dabei seinen Zustand (Umdrehungen pro Minute) anpasst.
 * Implementiert die Switchable-Schnittstelle.
 */
public class Motor implements Switchable {

    private boolean isOn;
    private int rpm;

    public Motor() {
        this.isOn = false;
        this.rpm = 0;
    }

    @Override
    public void switchOn() {
        this.isOn = true;
        this.rpm = 2000; // Standard-Drehzahl beim Einschalten
    }

    @Override
    public void switchOff() {
        this.isOn = false;
        this.rpm = 0; // Motor stoppt
    }

    @Override
    public boolean isSwitchedOn() {
        return this.isOn;
    }

    /**
     * Liefert die aktuellen Umdrehungen pro Minute.
     * @return Die Drehzahl (RPM).
     */
    public int getRpm() {
        return this.rpm;
    }
}
