package sw10;

/**
 * Hauptdemo fuer SW10 - Exception Handling.
 * Zeigt alle Konzepte ohne User-Interaktion (im Gegensatz zu Sw10KonsolenDemo).
 */
public class Sw10Demo {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("SW10 DEMO: Exception Handling");
        System.out.println("=========================================\n");

        demoTemperaturFactoryMethoden();
        demoImmutable();
        demoValidationException();
        demoOwnCheckedException();
        demoRaum();
    }

    // ======================================================
    // DEMO 1: Factory-Methoden statt public Konstruktor
    // ======================================================
    private static void demoTemperaturFactoryMethoden() {
        System.out.println("--- 1. Factory-Methoden (Aufgabe 2c, 2d) ---");

        Temperatur zimmer = Temperatur.createFromCelsius(20.0f);
        Temperatur gefrier = Temperatur.createFromKelvin(273.15f);

        System.out.println("  Zimmertemperatur: " + zimmer);
        System.out.println("  Gefrierpunkt:     " + gefrier);

        // Private Konstruktor -> dieser Aufruf waere Compile-Fehler:
        // new Temperatur(300f);   // <- nicht mehr moeglich!
        System.out.println("  -> new Temperatur(...) direkt waere Compile-Fehler!");
        System.out.println();
    }

    // ======================================================
    // DEMO 2: Immutable - keine Setter mehr!
    // ======================================================
    private static void demoImmutable() {
        System.out.println("--- 2. Immutable Objekt (Aufgabe 2k) ---");

        Temperatur t1 = Temperatur.createFromCelsius(20.0f);
        // t1.setCelsius(30);   // <- gibt's nicht mehr! Immutable!

        // Aenderung -> neues Objekt erzeugen
        Temperatur t2 = Temperatur.createFromCelsius(30.0f);

        System.out.println("  t1 bleibt unveraendert: " + t1);
        System.out.println("  t2 ist neues Objekt:    " + t2);
        System.out.println("  t1.equals(t2)?          " + t1.equals(t2));
        System.out.println();
    }

    // ======================================================
    // DEMO 3: IllegalArgumentException (UNCHECKED)
    // ======================================================
    private static void demoValidationException() {
        System.out.println("--- 3. Validation mit IllegalArgumentException (Aufgabe 2h) ---");

        try {
            Temperatur fehler = Temperatur.createFromCelsius(-300f);
            System.out.println("  Das sollte nie ausgegeben werden: " + fehler);
        } catch (IllegalArgumentException iae) {
            System.out.println("  Exception gefangen!");
            System.out.println("  Typ:     " + iae.getClass().getSimpleName());
            System.out.println("  Message: " + iae.getMessage());
        } finally {
            System.out.println("  finally: Aufraeumen, egal was passiert");
        }
        System.out.println();
    }

    // ======================================================
    // DEMO 4: Eigene CHECKED Exception (Aufgabe 2l optional)
    // ======================================================
    private static void demoOwnCheckedException() {
        System.out.println("--- 4. Eigene Checked Exception (Aufgabe 2l) ---");

        try {
            validiereTemperaturChecked(-500);
        } catch (InvalidTemperatureException ite) {
            System.out.println("  Eigene Exception gefangen!");
            System.out.println("  Message: " + ite.getMessage());
        }

        try {
            validiereTemperaturChecked(25);
            System.out.println("  25 Grad sind OK, keine Exception.");
        } catch (InvalidTemperatureException ite) {
            // Hier kommen wir nicht hin
        }
        System.out.println();
    }

    /**
     * throws-Deklaration ist bei checked Exceptions ZWINGEND.
     */
    private static void validiereTemperaturChecked(final float celsius)
            throws InvalidTemperatureException {
        if (celsius < -273.15f) {
            throw new InvalidTemperatureException(
                "Temperatur unter absolutem Nullpunkt: " + celsius);
        }
    }

    // ======================================================
    // DEMO 5: Raumverwaltung (optionale Aufgabe 4)
    // ======================================================
    private static void demoRaum() {
        System.out.println("--- 5. Raumverwaltung mit Validation (Optionale Aufgabe) ---");

        // Gueltiger Raum
        Raum r1 = new Raum(202, 30);
        Raum r2 = new Raum(202, 50);   // gleiche Nummer, andere Kap.
        Raum r3 = new Raum(305, 30);

        System.out.println("  r1: " + r1);
        System.out.println("  r2: " + r2);
        System.out.println("  r1.equals(r2)? " + r1.equals(r2)
                + " (gleiche Raumnummer!)");
        System.out.println("  r1.equals(r3)? " + r1.equals(r3));

        // Ungueltige Werte
        try {
            new Raum(50, 30);   // zu niedrige Raumnummer
        } catch (IllegalArgumentException iae) {
            System.out.println("  r(50, 30):  " + iae.getMessage());
        }

        try {
            new Raum(202, 1);   // zu wenig Kapazitaet
        } catch (IllegalArgumentException iae) {
            System.out.println("  r(202, 1):  " + iae.getMessage());
        }
    }
}
