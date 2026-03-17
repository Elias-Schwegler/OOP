package sw05;

/**
 * Übung SW05, Aufgabe 1.g) & 1.h)
 * Motor mit Zählfunktion und Name – implementiert CountingSwitchable UND Named.
 * Demonstriert: Eine Klasse kann mehrere Interfaces implementieren.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public class CountingMotor implements CountingSwitchable, Named {

    private boolean isOn;
    private int rpm;
    private long switchCount;
    private String name;

    /**
     * Erstellt einen benannten Motor mit Schaltvorgang-Zähler.
     *
     * @param name Name des Motors.
     */
    public CountingMotor(final String name) {
        this.isOn = false;
        this.rpm = 0;
        this.switchCount = 0;
        this.name = name;
    }

    // === Switchable-Methoden ===

    @Override
    public void switchOn() {
        if (!this.isOn) {
            this.isOn = true;
            this.rpm = 2000;
            this.switchCount++;
        }
    }

    @Override
    public void switchOff() {
        if (this.isOn) {
            this.isOn = false;
            this.rpm = 0;
            this.switchCount++;
        }
    }

    @Override
    public boolean isSwitchedOn() {
        return this.isOn;
    }

    // === CountingSwitchable-Methode ===

    @Override
    public long getSwitchCount() {
        return this.switchCount;
    }

    // === Named-Methoden ===

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Liefert die aktuellen Umdrehungen pro Minute.
     * @return RPM.
     */
    public int getRpm() {
        return this.rpm;
    }

    @Override
    public String toString() {
        return "CountingMotor \"" + this.name + "\" [" + (this.isOn ? "EIN" : "AUS")
                + ", " + this.rpm + " RPM, " + this.switchCount + " Schaltvorgänge]";
    }
}
