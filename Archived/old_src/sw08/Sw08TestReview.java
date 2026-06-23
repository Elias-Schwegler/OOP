package sw08;

/**
 * SW08 – Online Test 1 Korrektur-Demo.
 * Demonstriert die korrekten Antworten zu den Fragen,
 * die im Test falsch beantwortet wurden.
 */
public class Sw08TestReview {

    // =========================================================
    // KORREKTUR 1: do-while Schleife (Ausgangstest!)
    // =========================================================

    /**
     * Demonstriert: do-while wird IMMER mindestens 1x ausgeführt,
     * auch wenn die Bedingung sofort false ist.
     */
    public void demoDoWhile() {
        System.out.println("--- do-while Demo ---");

        // do-while mit false: wird trotzdem 1x ausgeführt!
        int count = 0;
        do {
            count++;
            System.out.println("  do-while Durchlauf #" + count);
        } while (false); // Bedingung sofort false
        System.out.println("  Ergebnis: " + count + " Durchlauf (Ausgangstest!)");

        // Vergleich: while mit false: wird NIE ausgeführt
        count = 0;
        while (false) {
            count++; // Dieser Code ist unerreichbar!
        }
        System.out.println("  while(false): " + count + " Durchläufe (Eingangstest!)");
    }

    // =========================================================
    // KORREKTUR 2: Scope-Hierarchie
    // =========================================================

    private static int klassenAttribut = 100;  // 4. Grösster Scope (Klasse)
    private int attribut = 50;                  // 3. Instanz-Scope

    /**
     * Demonstriert die Scope-Hierarchie:
     * lokale Variable < Methodenparameter < Attribut < Klassenattribut
     */
    public void demoScope(int methodenParameter) {  // 2. Methoden-Scope
        int lokaleVariable = 10;  // 1. Kleinster Scope (nur in diesem Block)

        System.out.println("--- Scope-Hierarchie (aufsteigend) ---");
        System.out.println("  1. Lokale Variable:    " + lokaleVariable + " (nur im Block)");
        System.out.println("  2. Methodenparameter:  " + methodenParameter + " (ganze Methode)");
        System.out.println("  3. Attribut:           " + attribut + " (ganze Instanz)");
        System.out.println("  4. Klassenattribut:    " + klassenAttribut + " (ganze Klasse, static)");

        // Lokale Variable existiert nur innerhalb eines Blocks:
        if (true) {
            int blockVariable = 99; // Existiert NUR in diesem if-Block
            System.out.println("  Block-Variable: " + blockVariable);
        }
        // blockVariable ist hier NICHT mehr zugänglich!
    }

    // =========================================================
    // KORREKTUR 3: extends vs. implements
    // =========================================================

    /**
     * Demonstriert: extends = Klasse erbt von Klasse
     *               implements = Klasse implementiert Interface
     */
    public void demoExtendsVsImplements() {
        System.out.println("--- extends vs. implements ---");
        System.out.println("  class Mitarbeiter EXTENDS Person  --> Vererbung (Klasse -> Klasse)");
        System.out.println("  class Motor IMPLEMENTS Switchable --> Interface-Implementierung");
        System.out.println("  MERKE: Person ist eine Klasse, daher 'extends'!");
    }

    // =========================================================
    // KORREKTUR 4: Fliesskomma-Genauigkeit
    // =========================================================

    public void demoFloatPrecision() {
        System.out.println("--- Fliesskomma-Genauigkeit ---");

        float f = 1.0f / 3.0f;
        double d = 1.0 / 3.0;

        System.out.println("  float  (32 Bit, ~7 Stellen):  " + f);
        System.out.println("  double (64 Bit, ~15 Stellen): " + d);
        System.out.println("  MERKE: float ~7, double ~15 (NICHT 16!)");
    }

    // =========================================================
    // KORREKTUR 5: Information Hiding IST anspruchsvoll
    // =========================================================

    public void demoInformationHiding() {
        System.out.println("--- Information Hiding ---");
        System.out.println("  Aussage: 'Information Hiding ist anspruchsvoll' = RICHTIG!");
        System.out.println("  Warum? Es reicht NICHT, einfach private + Getter/Setter zu haben.");
        System.out.println("  Man muss die interne Repraesentation bewusst von der");
        System.out.println("  Schnittstelle entkoppeln (z.B. intern Kelvin, extern Celsius).");
    }

    // =========================================================
    // KORREKTUR 6: Vererbung & final
    // =========================================================

    public void demoVererbungFinal() {
        System.out.println("--- Vererbung & final ---");
        System.out.println("  'A kann nicht mehr spezialisiert werden' = FALSCH!");
        System.out.println("  Eine Klasse kann beliebig oft spezialisiert werden,");
        System.out.println("  AUSSER sie ist 'final' deklariert.");
        System.out.println("  Bsp: class A {}  -> class B extends A {}  -> class C extends A {}");
    }
}
