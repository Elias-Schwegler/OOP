
/**
 * TEMPLATE 24 – Event/Listener-Pattern + Anonyme innere Klassen (Woche 11).
 *
 * IDEE (Beobachter-Muster / Observer):
 *  - Eine EVENTQUELLE meldet "es ist etwas passiert", OHNE zu wissen WER zuhoert.
 *  - Beliebig viele LISTENER registrieren sich und reagieren selbst.
 *
 * WARUM das wichtig ist (Pruefungs-Argument!):
 *  - Senkt die KOPPLUNG massiv: Die Quelle kennt nur das Listener-INTERFACE,
 *    nicht die konkreten Klassen. Neue Reaktionen kann man hinzufuegen,
 *    OHNE die Quelle anzufassen (Open-Closed-Prinzip).
 *
 * JAVA-KONVENTIONEN fuer Events:
 *  - Event-Klasse erbt von java.util.EventObject (hat source + getSource()).
 *  - Listener-Interface erbt von java.util.EventListener (Marker-Interface).
 *  - Methoden: addXxxListener / removeXxxListener / privates fireXxx().
 *
 * Dieses Template zeigt das Temperatur-Beispiel aus SW11/aufgabe2 –
 * und registriert Listener auf DREI Arten: benannt, anonym, Lambda.
 */
public class _24_EventListener_AnonymeKlassen {

    // ======================================================
    // EVENT – die Daten, die "verschickt" werden
    // erbt von EventObject -> liefert source automatisch mit
    // FINAL + immutable -> sicher, kann nicht nachtraeglich veraendert werden
    // ======================================================
    public static final class TemperaturEvent extends java.util.EventObject {

        public enum Type { MIN, MAX }

        private final Type type;
        private final float wertCelsius;

        public TemperaturEvent(final Object source, final Type type, final float wertCelsius) {
            super(source);            // EventObject merkt sich die Quelle
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

    // ======================================================
    // LISTENER-INTERFACE – der Vertrag fuer "Zuhoerer"
    // extends EventListener (Java-Konvention, Marker)
    // @FunctionalInterface (genau 1 Methode) -> Lambda-faehig!
    // ======================================================
    @FunctionalInterface
    public interface TemperaturListener extends java.util.EventListener {
        void temperaturChanged(TemperaturEvent event);
    }

    // ======================================================
    // EVENTQUELLE – verwaltet Listener und feuert Events
    // WICHTIG: kennt nur das Interface, NIE die konkreten Listener!
    // ======================================================
    public static class TemperaturVerlauf {

        private final java.util.List<Float> verlauf = new java.util.ArrayList<>();
        private final java.util.List<TemperaturListener> listeners = new java.util.ArrayList<>();

        // --- Listener registrieren / entfernen ---
        public void addTemperaturListener(final TemperaturListener listener) {
            if (listener == null) {
                throw new IllegalArgumentException("listener darf nicht null sein");
            }
            this.listeners.add(listener);
        }

        public void removeTemperaturListener(final TemperaturListener listener) {
            this.listeners.remove(listener);
        }

        // --- fachliche Logik: feuert bei neuem Min ODER Max ---
        public void add(final float celsius) {
            final boolean ersterWert = verlauf.isEmpty();
            final float oldMin = ersterWert ? Float.NaN : java.util.Collections.min(verlauf);
            final float oldMax = ersterWert ? Float.NaN : java.util.Collections.max(verlauf);

            verlauf.add(celsius);

            if (ersterWert || celsius < oldMin) {
                fire(TemperaturEvent.Type.MIN, celsius);
            }
            if (ersterWert || celsius > oldMax) {
                fire(TemperaturEvent.Type.MAX, celsius);
            }
        }

        // --- PRIVATE fire-Methode (Kapselung): baut Event und ruft alle Listener ---
        private void fire(final TemperaturEvent.Type type, final float wert) {
            final TemperaturEvent event = new TemperaturEvent(this, type, wert);
            // Kopie iterieren -> sicher, falls ein Listener sich beim Reagieren abmeldet
            for (final TemperaturListener l : new java.util.ArrayList<>(this.listeners)) {
                l.temperaturChanged(event);
            }
        }
    }

    // ======================================================
    // VARIANTE (a): BENANNTE Listener-Klasse
    // Gut, wenn die Reaktion wiederverwendet wird oder Zustand braucht.
    // ======================================================
    public static class ProtokollListener implements TemperaturListener {
        private int anzahl = 0;

        @Override
        public void temperaturChanged(final TemperaturEvent event) {
            anzahl++;
            System.out.printf("  [benannt] Ereignis #%d: %s%n", anzahl, event);
        }
    }

    // ======================================================
    // DEMO – ein Event ausloesen, alle 3 Listener-Arten reagieren
    // ======================================================
    public static void main(final String[] args) {
        final TemperaturVerlauf verlauf = new TemperaturVerlauf();

        // (a) BENANNTE Klasse
        verlauf.addTemperaturListener(new ProtokollListener());

        // (b) ANONYME innere Klasse: Klasse OHNE Namen, direkt an Ort und Stelle.
        //     Lohnt sich fuer einmalige Reaktionen; kann ueber mehrere Zeilen gehen.
        verlauf.addTemperaturListener(new TemperaturListener() {
            @Override
            public void temperaturChanged(final TemperaturEvent event) {
                final String label = switch (event.getType()) {   // switch-expression (Java 14+)
                    case MIN -> "Neues MINIMUM";
                    case MAX -> "Neues MAXIMUM";
                };
                System.out.printf("  [anonym]  %s: %.2f C%n", label, event.getWertCelsius());
            }
        });

        // (c) LAMBDA: kuerzeste Form, NUR weil das Interface @FunctionalInterface ist.
        //     event ist automatisch vom Typ TemperaturEvent.
        verlauf.addTemperaturListener(event ->
                System.out.printf("  [lambda]  -> %s%n", event));

        // Werte eingeben -> loest intern fire(...) aus
        final float[] messwerte = {20.0f, 25.5f, 18.0f, 30.0f, 15.0f};
        for (final float wert : messwerte) {
            System.out.println("add(" + wert + "):");
            verlauf.add(wert);   // jeder neue Rekord ruft ALLE Listener auf
        }
    }
}
