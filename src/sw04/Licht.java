package sw04;

/**
 * Eine einfache Licht-Komponente, die Switchable implementiert.
 */
public class Licht implements Switchable {

    private boolean isOn;

    public Licht() {
        this.isOn = false;
    }

    @Override
    public void switchOn() {
        this.isOn = true;
    }

    @Override
    public void switchOff() {
        this.isOn = false;
    }

    @Override
    public boolean isSwitchedOn() {
        return this.isOn;
    }
}
