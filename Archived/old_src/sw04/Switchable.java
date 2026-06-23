package sw04;

/**
 * Übung SW04, Aufgabe 2.a) & 2.b)
 * Definiert einen einfachen Schalter mit Ein/Aus-Semantik.
 * 
 * @author Elias Schwegler
 * @version 1.0
 */
public interface Switchable {

    /**
     * Schaltet das Objekt ein.
     */
    void switchOn();

    /**
     * Schaltet das Objekt aus.
     */
    void switchOff();

    /**
     * Prüft, ob das Objekt eingeschaltet ist.
     * @return true, falls das Objekt eingeschaltet ist, ansonsten false.
     */
    boolean isSwitchedOn();
}
