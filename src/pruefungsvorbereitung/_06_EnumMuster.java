package pruefungsvorbereitung;

/**
 * TEMPLATE 06 – Enumerationen (enum).
 *
 * Enum-Eigenschaften:
 *  - Eigener Datentyp mit FESTER Menge benannter Konstanten
 *  - TYPSICHER (Hauptvorteil!)
 *  - Implizit final (nicht spezialisierbar)
 *  - Implizit static
 *  - Privater Konstruktor (automatisch instanziiert)
 *
 * NIEMALS:
 *  - ordinal() fuer Persistenz verwenden (aendert sich bei Umordnung!)
 *  - Stattdessen: name() oder toString()
 */
public class _06_EnumMuster {

    // ======================================================
    // 1. EINFACHE ENUMERATION (ohne Attribute)
    // ======================================================
    public enum Wochentag {
        MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG, SAMSTAG, SONNTAG
    }

    // ======================================================
    // 2. ENUM MIT ATTRIBUTEN + METHODEN
    // ======================================================
    public enum Aggregatzustand {
        FEST("fest", "f"),
        FLUESSIG("fluessig", "fl"),
        GASFOERMIG("gasfoermig", "g");

        // Attribute (immer final!)
        private final String langName;
        private final String kurzName;

        // Konstruktor ist IMPLIZIT privat
        Aggregatzustand(final String langName, final String kurzName) {
            this.langName = langName;
            this.kurzName = kurzName;
        }

        // Getter wie bei normalen Klassen
        public String getLangName() {
            return this.langName;
        }

        public String getKurzName() {
            return this.kurzName;
        }

        // Eigene Methode auf Enum-Wert
        public boolean istKondensiert() {
            return this == FEST || this == FLUESSIG;
        }
    }

    // ======================================================
    // 3. ENUM MIT ABSTRAKTEN METHODEN (fortgeschritten)
    // ======================================================
    public enum Operation {
        PLUS {
            @Override
            public double apply(double a, double b) { return a + b; }
        },
        MINUS {
            @Override
            public double apply(double a, double b) { return a - b; }
        },
        MAL {
            @Override
            public double apply(double a, double b) { return a * b; }
        };

        // Jeder Wert implementiert das!
        public abstract double apply(double a, double b);
    }

    // ======================================================
    // 4. ENUM IN SWITCH verwenden
    // ======================================================
    public static String beschreibe(final Aggregatzustand z) {
        switch (z) {
            case FEST:
                return "Molekuele dicht gepackt";
            case FLUESSIG:
                return "Molekuele verschiebbar";
            case GASFOERMIG:
                return "Molekuele frei beweglich";
            default:
                throw new IllegalArgumentException("Unbekannt: " + z);
        }
    }

    // Moderner: switch-Expression (Java 14+)
    public static String beschreibeModern(final Aggregatzustand z) {
        return switch (z) {
            case FEST -> "Molekuele dicht gepackt";
            case FLUESSIG -> "Molekuele verschiebbar";
            case GASFOERMIG -> "Molekuele frei beweglich";
        };
    }

    // ======================================================
    // DEMO
    // ======================================================
    public static void main(String[] args) {
        // Alle Werte durchlaufen
        for (Aggregatzustand z : Aggregatzustand.values()) {
            System.out.println(z + " (" + z.getKurzName() + "): " + beschreibe(z));
        }

        // String -> Enum konvertieren
        Aggregatzustand z = Aggregatzustand.valueOf("FEST");
        System.out.println("Aus String: " + z);

        // Enum in Comparison (weil Enum automatisch Comparable ist!)
        System.out.println("FEST < GASFOERMIG? " +
                (Aggregatzustand.FEST.compareTo(Aggregatzustand.GASFOERMIG) < 0));

        // Abstrakte Methode auf Enum
        double result = Operation.PLUS.apply(3, 4);
        System.out.println("3 + 4 = " + result);
    }
}
