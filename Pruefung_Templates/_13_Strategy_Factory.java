
/**
 * 📌 TEMPLATE: Strategy + Factory Pattern.
 *
 * STRATEGY: austauschbarer Algorithmus zur Laufzeit (z.B. verschiedene Sortier-Verfahren).
 *   -&gt; Vermeidet "switch on type" und ermoeglicht Dependency Injection.
 *
 * FACTORY: Erzeugung von Objekten kapseln (Aufrufer kennt nur Interface, nicht konkrete Klasse).
 *   -&gt; Lose Kopplung.
 *
 * Klausur-Pattern: "Refactor switch -&gt; Polymorphismus" ist genau das hier!
 */
public class _13_Strategy_Factory {

    // ===== STRATEGY =====
    @FunctionalInterface
    public interface PreisStrategie {
        double berechne(double basisPreis);
    }

    public static final class KeinRabatt    implements PreisStrategie { public double berechne(double p) { return p; } }
    public static final class StudentRabatt implements PreisStrategie { public double berechne(double p) { return p * 0.80; } }
    public static final class ClubRabatt    implements PreisStrategie { public double berechne(double p) { return p - 5.0; } }

    // ===== KONTEXT (verwendet Strategie) =====
    public static class Kasse {
        private final PreisStrategie strategie;
        public Kasse(final PreisStrategie strategie) { this.strategie = strategie; }
        public double zahle(final double basisPreis) { return strategie.berechne(basisPreis); }
    }

    // ===== FACTORY =====
    public static final class PreisStrategieFactory {
        private PreisStrategieFactory() {}

        public static PreisStrategie create(final KundenTyp typ) {
            return switch (typ) {                      // Java 21+ switch expression
                case NORMAL  -> new KeinRabatt();
                case STUDENT -> new StudentRabatt();
                case CLUB    -> new ClubRabatt();
            };
        }
    }

    public enum KundenTyp { NORMAL, STUDENT, CLUB }

    public static void main(final String[] args) {
        for (final KundenTyp typ : KundenTyp.values()) {
            final Kasse k = new Kasse(PreisStrategieFactory.create(typ));
            System.out.printf("%-8s -> %.2f%n", typ, k.zahle(100.0));
        }
    }
}
