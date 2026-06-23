package ch.hslu.oop.pruefungsvorbereitung;

/**
 * 📌 TEMPLATE: Observer Pattern (SW11 - Events).
 *
 * Beobachtermuster: 1 Subject -&gt; n Listener.
 * Java-Beans-Konvention:
 *   - Event-Klasse erbt von java.util.EventObject
 *   - Listener-Interface erbt von java.util.EventListener
 *   - Listener hat EINE Methode mit Endung "ed" (changed, fired, ...)
 *   - Subject hat add/remove/fire-Methoden
 *
 * REZEPT (Klausur):
 *   1. XxxEvent      extends EventObject     (Daten ueber das Ereignis)
 *   2. XxxListener   extends EventListener   (Interface mit Callback)
 *   3. XxxSource     hat Liste von Listenern (Subject - feuert Events)
 *
 * ANWENDUNG: GUI-Buttons, Modell-Aenderungen, Sensor-Daten, Game-Engine, ...
 */
public class _10_Observer_Pattern {

    // ===== 1. EVENT (immer immutable!) =====
    public static final class WertChangeEvent extends java.util.EventObject {
        private static final long serialVersionUID = 1L;
        private final double oldValue;
        private final double newValue;
        public WertChangeEvent(final Object src, final double oldV, final double newV) {
            super(src);
            this.oldValue = oldV;
            this.newValue = newV;
        }
        public double getOldValue() { return oldValue; }
        public double getNewValue() { return newValue; }
        public double getDelta()    { return newValue - oldValue; }
    }

    // ===== 2. LISTENER (Functional Interface -> Lambda-fähig) =====
    @FunctionalInterface
    public interface WertChangeListener extends java.util.EventListener {
        void wertChanged(WertChangeEvent event);
    }

    // ===== 3. SUBJECT (= Source / Observable) =====
    public static class WertSource {
        private final java.util.List<WertChangeListener> listeners = new java.util.ArrayList<>();
        private double wert;

        public void addWertChangeListener(final WertChangeListener l)    { listeners.add(l); }
        public void removeWertChangeListener(final WertChangeListener l) { listeners.remove(l); }

        public double getWert() { return wert; }
        public void setWert(final double newValue) {
            if (Double.compare(this.wert, newValue) == 0) return;
            final double old = this.wert;
            this.wert = newValue;
            fireWertChanged(old, newValue);
        }

        // ✅ private + ueber Snapshot iterieren (concurrent-safe)
        private void fireWertChanged(final double oldV, final double newV) {
            final var event = new WertChangeEvent(this, oldV, newV);
            for (final var l : new java.util.ArrayList<>(listeners)) {
                l.wertChanged(event);
            }
        }
    }

    // ===== DEMO / Anwendung =====
    public static void main(final String[] args) {
        final WertSource src = new WertSource();
        // Lambda dank @FunctionalInterface
        src.addWertChangeListener(e -> System.out.printf("Aenderung: %+.2f%n", e.getDelta()));
        src.setWert(42);
        src.setWert(50);
    }
}
