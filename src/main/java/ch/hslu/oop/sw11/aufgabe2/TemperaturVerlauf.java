package ch.hslu.oop.sw11.aufgabe2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.hslu.oop.sw11.aufgabe2.events.TemperaturEvent;
import ch.hslu.oop.sw11.aufgabe2.interfaces.ITemperaturListener;

/**
 * SW11 / U10 Aufgabe 2: TemperaturVerlauf als EVENT-QUELLE.
 *
 *  - speichert eingegebene Celsius-Werte
 *  - feuert bei JEDEM neuen Min ODER Max ein TemperaturEvent
 *
 * Aufbau Sub-Packages:
 *  - sw11.aufgabe2.events     -> TemperaturEvent (DTO/Daten)
 *  - sw11.aufgabe2.interfaces -> ITemperaturListener (Vertrag)
 *  - sw11.aufgabe2            -> TemperaturVerlauf (Verhalten/Logik) + Demo
 */
public class TemperaturVerlauf {

    private final List<Float> verlauf = new ArrayList<>();
    private final List<ITemperaturListener> listeners = new ArrayList<>();

    public void add(final float celsius) {
        final float oldMin = verlauf.isEmpty() ? Float.NaN : Collections.min(verlauf);
        final float oldMax = verlauf.isEmpty() ? Float.NaN : Collections.max(verlauf);

        verlauf.add(celsius);

        // Erster Wert -> ist gleichzeitig Min UND Max.
        if (verlauf.size() == 1) {
            fire(TemperaturEvent.Type.MIN, celsius);
            fire(TemperaturEvent.Type.MAX, celsius);
            return;
        }

        if (celsius < oldMin) {
            fire(TemperaturEvent.Type.MIN, celsius);
        }
        if (celsius > oldMax) {
            fire(TemperaturEvent.Type.MAX, celsius);
        }
    }

    public int getCount() {
        return verlauf.size();
    }

    public float getMin() {
        return verlauf.isEmpty() ? Float.NaN : Collections.min(verlauf);
    }

    public float getMax() {
        return verlauf.isEmpty() ? Float.NaN : Collections.max(verlauf);
    }

    public float getAverage() {
        if (verlauf.isEmpty()) {
            return Float.NaN;
        }
        float sum = 0f;
        for (final float v : verlauf) {
            sum += v;
        }
        return sum / verlauf.size();
    }

    @Override
    public String toString() {
        return String.format("TemperaturVerlauf[count=%d, min=%.2f, max=%.2f, avg=%.2f]",
                getCount(), getMin(), getMax(), getAverage());
    }

    public void addTemperaturListener(final ITemperaturListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener darf nicht null sein");
        }
        this.listeners.add(listener);
    }

    public void removeTemperaturListener(final ITemperaturListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * PRIVATE Fire-Methode (Datenkapselung).
     * Iteriert ueber Snapshot-Kopie -> Concurrent-Modification-safe.
     */
    private void fire(final TemperaturEvent.Type type, final float wert) {
        final TemperaturEvent event = new TemperaturEvent(this, type, wert);
        for (final ITemperaturListener l : new ArrayList<>(this.listeners)) {
            l.temperaturChanged(event);
        }
    }
}
