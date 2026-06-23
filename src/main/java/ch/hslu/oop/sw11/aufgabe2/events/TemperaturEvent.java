package ch.hslu.oop.sw11.aufgabe2.events;

import java.util.EventObject;

/**
 * SW11 / U10 Aufgabe 2: Eigene Event-Klasse fuer Temperatur-Min/Max-Wechsel.
 *
 * Variante 1 aus dem PDF: EINE Event-Klasse mit Enum-Attribut.
 *
 * Lerneffekt:
 *  - Eigene Events erben IMMER von java.util.EventObject (Java-Konvention)
 *  - Klasse ist FINAL + Felder sind FINAL -> immutable -> threadsafe
 *  - Enum statt String/int fuer Typ-Unterscheidung -> type-safe + lesbar
 *
 * Liegt im Sub-Package "events", weil DTOs/Events von Klassenlogik
 * getrennt werden sollten (Trennung Daten vs. Verhalten).
 */
public final class TemperaturEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public enum Type { MIN, MAX }

    private final Type type;
    private final float wertCelsius;

    public TemperaturEvent(final Object source, final Type type, final float wertCelsius) {
        super(source);
        this.type = type;
        this.wertCelsius = wertCelsius;
    }

    public Type getType() {
        return this.type;
    }

    public float getWertCelsius() {
        return this.wertCelsius;
    }

    @Override
    public String toString() {
        return String.format("TemperaturEvent[%s = %.2f C]", type, wertCelsius);
    }
}
