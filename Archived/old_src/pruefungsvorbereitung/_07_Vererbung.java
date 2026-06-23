package pruefungsvorbereitung;

/**
 * TEMPLATE 07 – Vererbung (extends + super + Polymorphie).
 *
 * Grundregeln:
 *  - extends = Klasse erbt von Klasse
 *  - super() = Konstruktor der Oberklasse aufrufen (MUSS erste Zeile sein!)
 *  - super.methode() = Methode der Oberklasse aufrufen
 *  - super.attribut = Attribut der Oberklasse (bei Namenskollision)
 *  - Java kennt nur EINFACHVERERBUNG (eine Basisklasse)
 *
 * Overriding (@Override):
 *  - Subklasse ueberschreibt Methode der Oberklasse
 *  - Signatur MUSS exakt uebereinstimmen
 *  - @Override-Annotation hilft dem Compiler zu pruefen
 *
 * Polymorphie:
 *  - Statischer Typ (zur Compile-Zeit): was der Compiler sieht
 *  - Dynamischer Typ (zur Laufzeit): was tatsaechlich im Speicher ist
 *  - Methodenaufruf wird DYNAMISCH aufgeloest (late binding)
 */
public class _07_Vererbung {

    // ======================================================
    // BASIS-KLASSE
    // ======================================================
    public static class Fahrzeug {
        protected final String marke;
        protected int kilometerstand;

        public Fahrzeug(final String marke) {
            this.marke = marke;
            this.kilometerstand = 0;
        }

        public void fahre(final int kilometer) {
            this.kilometerstand += kilometer;
            System.out.println("[" + marke + "] faehrt " + kilometer + " km");
        }

        @Override
        public String toString() {
            return "Fahrzeug(" + marke + ", " + kilometerstand + " km)";
        }
    }

    // ======================================================
    // SPEZIALISIERUNG 1: Auto
    // ======================================================
    public static class Auto extends Fahrzeug {
        private final int anzahlTueren;

        public Auto(final String marke, final int anzahlTueren) {
            super(marke);  // Konstruktor der Oberklasse aufrufen – MUSS erste Zeile sein!
            this.anzahlTueren = anzahlTueren;
        }

        @Override
        public void fahre(final int kilometer) {
            super.fahre(kilometer);  // Erst die Basis-Methode...
            System.out.println("[" + marke + "] Auto verbrennt Benzin");  // ...dann unsere Ergaenzung
        }

        public int getAnzahlTueren() {
            return this.anzahlTueren;
        }

        @Override
        public String toString() {
            return "Auto(" + marke + ", " + anzahlTueren + " Tueren, " + kilometerstand + " km)";
        }
    }

    // ======================================================
    // SPEZIALISIERUNG 2: E-Auto (Auto -> Fahrzeug: 2 Level tief!)
    // ======================================================
    public static class ElektroAuto extends Auto {
        private int akkuProzent;

        public ElektroAuto(final String marke, final int anzahlTueren) {
            super(marke, anzahlTueren);
            this.akkuProzent = 100;
        }

        @Override
        public void fahre(final int kilometer) {
            if (akkuProzent <= 0) {
                System.out.println("[" + marke + "] Akku leer!");
                return;
            }
            // Achtung: hier rufe ich NICHT super.fahre() auf, weil wir den Akku
            // anders handhaben wollen und kein Benzin verbrennen
            kilometerstand += kilometer;
            akkuProzent = Math.max(0, akkuProzent - kilometer / 2);
            System.out.println("[" + marke + "] E-Auto faehrt " + kilometer
                    + " km, Akku: " + akkuProzent + "%");
        }

        @Override
        public String toString() {
            return "ElektroAuto(" + marke + ", " + akkuProzent + "% Akku)";
        }
    }

    // ======================================================
    // DEMO: Polymorphie in Aktion
    // ======================================================
    public static void main(String[] args) {
        // Statischer Typ: Fahrzeug, Dynamischer Typ: unterschiedlich
        Fahrzeug[] flotte = {
            new Fahrzeug("GenericBike"),
            new Auto("Toyota", 4),
            new ElektroAuto("Tesla", 4)
        };

        // Polymorphie: fahre() ruft bei jedem Objekt die RICHTIGE Variante auf
        for (Fahrzeug f : flotte) {
            System.out.println("\n-- " + f.getClass().getSimpleName() + " --");
            f.fahre(50);
            System.out.println("Ergebnis: " + f);
        }

        // Downcast (nur wenn man WEISS, dass es wirklich das ist)
        Fahrzeug f = flotte[1];
        if (f instanceof Auto auto) {   // Pattern Matching (ab Java 16)
            System.out.println("\nAuto mit " + auto.getAnzahlTueren() + " Tueren");
        }

        // Substitutionsprinzip: jeder Fahrzeug-Typ passt ueberall wo Fahrzeug erwartet wird
        parkiere(new ElektroAuto("BMW", 5));
    }

    // Methode akzeptiert JEDES Fahrzeug (inkl. Subklassen)
    private static void parkiere(final Fahrzeug f) {
        System.out.println("Parkiere: " + f);
    }
}
