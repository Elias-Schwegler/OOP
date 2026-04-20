package sw09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Verwaltet eine Sammlung von Temperaturwerten (U08, Teil 2).
 * Demonstriert: Collections-Einsatz, Iterator, Collections-Utilities.
 */
public class TemperaturVerlauf {
    private final List<Float> verlauf;

    public TemperaturVerlauf() {
        this.verlauf = new ArrayList<>();
    }

    public void add(float celsiusValue) {
        verlauf.add(celsiusValue);
    }

    public void clear() {
        verlauf.clear();
    }

    public int getCount() {
        return verlauf.size();
    }

    /**
     * Gibt die maximale Temperatur zurueck (Aufgabe c).
     * Nutzt Collections.max().
     * @return Maximum oder Float.NaN wenn leer
     */
    public float getMax() {
        if (verlauf.isEmpty()) {
            return Float.NaN;
        }
        return Collections.max(verlauf);
    }

    /**
     * Gibt die minimale Temperatur zurueck (Aufgabe d).
     * @return Minimum oder Float.NaN wenn leer
     */
    public float getMin() {
        if (verlauf.isEmpty()) {
            return Float.NaN;
        }
        return Collections.min(verlauf);
    }

    /**
     * Berechnet den Durchschnitt aller Temperaturen (Aufgabe e).
     * Nutzt Iterator-Pattern fuer Datenstruktur-Unabhaengigkeit.
     * @return Durchschnitt oder Float.NaN wenn leer
     */
    public float getAverage() {
        if (verlauf.isEmpty()) {
            return Float.NaN;
        }
        float sum = 0f;
        Iterator<Float> it = verlauf.iterator();
        while (it.hasNext()) {
            sum += it.next();
        }
        return sum / verlauf.size();
    }

    @Override
    public String toString() {
        return "TemperaturVerlauf{count=" + getCount()
                + ", min=" + getMin()
                + ", max=" + getMax()
                + ", avg=" + getAverage() + "}";
    }
}
