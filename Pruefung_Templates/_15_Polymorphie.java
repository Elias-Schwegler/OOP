
/**
 * TEMPLATE 15 - Polymorphie.
 *
 * Polymorphie = "Vielgestaltigkeit". Ein und dieselbe Methode (z.B. gibLaut())
 * verhaelt sich je nach TATSAECHLICHEM Objekt unterschiedlich.
 *
 * KERNIDEEN:
 *  - Statischer Typ vs. dynamischer Typ.
 *  - Dynamic Dispatch: bei ueberschriebenen Methoden zaehlt das ECHTE Objekt.
 *  - Overriding (ueberschreiben) vs. Overloading (ueberladen).
 *  - Upcasting (implizit) und Downcasting (explizit, mit instanceof).
 *
 * WHY (das Wichtigste):
 *  Polymorphie = ERWEITERBARKEIT. Eine neue Tier-Art hinzufuegen, OHNE
 *  bestehenden Code (z.B. die Schleife in main) anfassen zu muessen.
 *  -> Open/Closed-Prinzip: offen fuer Erweiterung, geschlossen fuer Aenderung.
 */
public class _15_Polymorphie {

    // ======================================================
    // ABSTRAKTE BASISKLASSE
    // ======================================================
    public static abstract class Tier {
        protected final String name;

        protected Tier(String name) {
            this.name = name;
        }

        // ABSTRAKT: jede Subklasse MUSS gibLaut() ueberschreiben.
        // Genau das macht Dynamic Dispatch moeglich.
        public abstract String gibLaut();

        // Konkrete Methode, die gibLaut() nutzt.
        // WHY: Hier ist zur Compile-Zeit noch UNBEKANNT, welches gibLaut()
        // laeuft - das entscheidet sich erst zur Laufzeit am echten Objekt.
        public String stelleDichVor() {
            return name + " macht: " + gibLaut();
        }
    }

    // ======================================================
    // KONKRETE SUBKLASSEN - ueberschreiben gibLaut()
    // ======================================================
    public static class Hund extends Tier {
        public Hund(String name) {
            super(name);
        }

        @Override // OVERRIDING: gleiche Signatur wie in der Basisklasse
        public String gibLaut() {
            return "Wuff";
        }

        // Methode, die NUR der Hund hat (fuer Downcasting-Demo wichtig).
        public String apportieren() {
            return name + " bringt den Ball zurueck.";
        }
    }

    public static class Katze extends Tier {
        public Katze(String name) {
            super(name);
        }

        @Override
        public String gibLaut() {
            return "Miau";
        }
    }

    // ======================================================
    // OVERLOADING (ueberladen) - NICHT mit Overriding verwechseln!
    // Gleicher Name, ABER unterschiedliche Parameter.
    // WHY: Overloading wird zur COMPILE-Zeit aufgeloest (statischer Typ),
    // Overriding erst zur LAUFZEIT (dynamischer Typ).
    // ======================================================
    public static String beschreibe(Hund h) {
        return "Ein Hund namens " + h.name;
    }

    public static String beschreibe(Katze k) {
        return "Eine Katze namens " + k.name;
    }

    // ======================================================
    // DEMO
    // ======================================================
    public static void main(String[] args) {

        // ----- Statischer vs. dynamischer Typ -----
        // Statischer Typ = Tier (Deklaration links).
        // Dynamischer Typ = Hund (echtes Objekt rechts mit new).
        Tier t = new Hund("Rex");          // UPCASTING (implizit, immer erlaubt)
        System.out.println(t.gibLaut());   // -> "Wuff" : Dynamic Dispatch!

        // ----- Polymorphe Sammlung -----
        // Array vom statischen Typ Tier[], enthaelt verschiedene echte Typen.
        Tier[] tiere = {
            new Hund("Rex"),
            new Katze("Minka"),
            new Hund("Bello")
        };

        // WHY: Diese Schleife kennt nur "Tier". Trotzdem ruft jedes Objekt
        // sein EIGENES gibLaut() auf. Neue Tier-Art? Schleife bleibt unveraendert.
        for (Tier tier : tiere) {
            System.out.println(tier.stelleDichVor());
        }

        // ----- Downcasting mit instanceof + Pattern Matching -----
        // WHY: Wollen wir hund-spezifische Methoden (apportieren), muessen wir
        // sicher zurueck-casten. instanceof prueft den dynamischen Typ.
        for (Tier tier : tiere) {
            if (tier instanceof Hund hund) {   // Pattern Matching (ab Java 16)
                System.out.println(hund.apportieren());
            }
        }

        // ----- Overloading: Compiler waehlt anhand des STATISCHEN Typs -----
        Hund rex = new Hund("Rex");
        Katze minka = new Katze("Minka");
        System.out.println(beschreibe(rex));   // -> beschreibe(Hund)
        System.out.println(beschreibe(minka)); // -> beschreibe(Katze)
        // ACHTUNG: beschreibe(t) mit Tier t waehlt KEINE der beiden Methoden,
        // weil es kein beschreibe(Tier) gibt -> Overloading ist statisch!

        // ----- Modernes switch + Pattern Matching ueber den dynamischen Typ -----
        for (Tier tier : tiere) {
            String info = switch (tier) {
                case Hund h  -> "Hund: " + h.gibLaut();
                case Katze k -> "Katze: " + k.gibLaut();
                default      -> "Unbekanntes Tier";
            };
            System.out.println(info);
        }
    }
}
