package ch.hslu.oop.sw11.aufgabe1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import ch.hslu.oop.sw04.Switchable;

/**
 * SW11 / U10 Aufgabe 1: Motor als EVENT-QUELLE (PropertyChangeListener-Pattern).
 *
 * Lerneffekt:
 *  - Wir mussten KEINE eigene Event-Klasse schreiben - Java liefert PropertyChangeEvent mit.
 *  - Konvention "addXxxListener / removeXxxListener" stammt aus den JavaBeans-Spec.
 *  - fireXxx-Methoden sind PRIVATE, damit niemand von aussen Fake-Events feuern kann.
 */
public class Motor implements Switchable {

    public static final String PROPERTY_IS_ON = "isOn";

    private boolean isOn;
    private int rpm;

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    public Motor() {
        this.isOn = false;
        this.rpm = 0;
    }

    @Override
    public void switchOn() {
        if (this.isOn) {
            return; // schon an -> kein Event
        }
        this.isOn = true;
        this.rpm = 2000;
        firePropertyChange(PROPERTY_IS_ON, false, true);
    }

    @Override
    public void switchOff() {
        if (!this.isOn) {
            return;
        }
        this.isOn = false;
        this.rpm = 0;
        firePropertyChange(PROPERTY_IS_ON, true, false);
    }

    @Override
    public boolean isSwitchedOn() {
        return this.isOn;
    }

    public int getRpm() {
        return this.rpm;
    }

    // ============================================================
    // LISTENER-MANAGEMENT (Aufgabe g, h, i)
    // ============================================================

    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener darf nicht null sein");
        }
        this.listeners.add(listener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener darf nicht null sein");
        }
        this.listeners.remove(listener);
    }

    // ============================================================
    // FIRE-METHODE (Aufgabe j) - HERZSTUECK, PRIVATE!
    // ============================================================

    private void firePropertyChange(final String property, final Object oldValue, final Object newValue) {
        final PropertyChangeEvent event = new PropertyChangeEvent(this, property, oldValue, newValue);
        for (final PropertyChangeListener l : new ArrayList<>(this.listeners)) {
            l.propertyChange(event);
        }
    }
}
