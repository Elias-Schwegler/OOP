package sw06;

import sw04.Switchable;
import sw05.CountingSwitchable;
import sw05.CountingMotor;
import sw05.Named;

/**
 * Übung SW06 – Demo/Hauptklasse.
 * Demonstriert alle Aufgaben aus SW06 ueber die main()-Methode.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public class Sw06Demo {

    public static void main(final String[] args) {

        System.out.println("=== SW06: Polymorphie & Unit Testing ===\n");

        // ─── Aufgabe 1.a) moveRelative(int, int) ───
        System.out.println("--- Aufgabe 1.a: moveRelative(int, int) ---");
        Point p = new Point(3, 4);
        System.out.println("Vor moveRelative: " + p);
        p.moveRelative(2, -1);
        System.out.println("Nach moveRelative(2, -1): " + p);
        System.out.println();

        // ─── Aufgabe 1.b) moveRelative(Point) ───
        System.out.println("--- Aufgabe 1.b: moveRelative(Point) ---");
        Point vektor = new Point(10, 10);
        p.moveRelative(vektor);
        System.out.println("Nach moveRelative(Point(10,10)): " + p);
        System.out.println();

        // ─── Aufgabe 1.c) moveRelative(double, int) – Polarkoordinaten ───
        System.out.println("--- Aufgabe 1.c: moveRelative(double, int) – Polar ---");
        Point p2 = new Point(0, 0);
        p2.moveRelative(0.0, 5);       // 0 Grad → reine X-Verschiebung
        System.out.println("Polar(0 rad, 5): " + p2);
        p2.moveRelative(Math.PI / 2, 5); // 90 Grad → reine Y-Verschiebung
        System.out.println("Polar(PI/2 rad, 5): " + p2);
        System.out.println("Diskussion: Diese Ueberladung ist technisch moeglich, aber");
        System.out.println("  die Signatur (double, int) kann mit (int, int) verwechselt werden.");
        System.out.println("  Besser waere ein eigener Methodenname: moveRelativePolar().");
        System.out.println();

        // ─── Aufgabe 1.d) Copy-Constructor ───
        System.out.println("--- Aufgabe 1.d: Copy-Constructor ---");
        Point original = new Point(42, 99);
        Point kopie = new Point(original);
        System.out.println("Original: " + original);
        System.out.println("Kopie:    " + kopie);
        kopie.moveRelative(1, 1);
        System.out.println("Kopie nach move(1,1): " + kopie);
        System.out.println("Original unveraendert: " + original);
        System.out.println();

        // ─── Aufgabe 1.e) & 1.f) toString() auf ChemicalElement ───
        System.out.println("--- Aufgabe 1.e/f: toString() auf Element ---");
        ChemicalElement nitrogen = new Nitrogen();
        ChemicalElement mercury = new Mercury();
        ChemicalElement lead = new Lead();
        System.out.println(nitrogen);
        System.out.println(lead);
        System.out.println();

        // ─── Aufgabe 1.g) toString() auf Mercury mit "GIFTIG" und super ───
        System.out.println("--- Aufgabe 1.g: Mercury.toString() mit GIFTIG ---");
        System.out.println(mercury);
        System.out.println();

        // ─── Aufgabe 1.h) Subtyping: Shape-Variable mit Circle/Rectangle ───
        System.out.println("--- Aufgabe 1.h: Subtyping ---");
        Shape shape1 = new Circle(0, 0, 5);
        Shape shape2 = new Rectangle(0, 0, 10, 4);
        System.out.println("shape1 (Circle):    " + shape1);
        System.out.println("shape2 (Rectangle): " + shape2);
        System.out.println("Warum funktioniert das? Circle und Rectangle SIND Shapes (Subtyping).");
        System.out.println();

        // ─── Aufgabe 1.i) move() ueber Shape-Variable ───
        System.out.println("--- Aufgabe 1.i: move() ueber Shape-Variable ---");
        shape1.move(100, 200);
        System.out.println("shape1 nach move(100,200): " + shape1);
        System.out.println("Ja, move() ist auf Shape definiert, daher aufrufbar!");
        System.out.println();

        // ─── Aufgabe 1.j) getDiameter() braucht Cast ───
        System.out.println("--- Aufgabe 1.j: Cast fuer getDiameter() ---");
        // shape1.getDiameter();  // Kompilierfehler! Statischer Typ ist Shape.
        if (shape1 instanceof Circle) {
            Circle kreis = (Circle) shape1;  // Cast: Shape → Circle
            System.out.println("Durchmesser (nach Cast): " + kreis.getDiameter());
        }
        System.out.println();

        // ─── Aufgabe 1.k) Statischer vs. dynamischer Typ ───
        System.out.println("--- Aufgabe 1.k: Statischer vs. Dynamischer Typ ---");
        System.out.println("Shape shape1 = new Circle(...)");
        System.out.println("  Statischer Typ: Shape    (das was der Compiler sieht)");
        System.out.println("  Dynamischer Typ: Circle  (das was zur Laufzeit wirklich da ist)");
        System.out.println("Der Compiler erlaubt nur Methoden des statischen Typs.");
        System.out.println("getDiameter() ist nur auf Circle definiert → Cast noetig!");
        System.out.println();

        // ─── Aufgabe 1.l) Subtyping mit Interfaces ───
        System.out.println("--- Aufgabe 1.l: Subtyping mit Interfaces ---");
        CountingMotor motor = new CountingMotor("TestMotor");
        motor.switchOn();
        motor.switchOff();
        motor.switchOn();

        // Verschiedene Interface-Typen fuer dasselbe Objekt
        Switchable schaltbar = motor;       // Motor als Switchable
        CountingSwitchable zaehlbar = motor; // Motor als CountingSwitchable
        Named benannt = motor;              // Motor als Named

        System.out.println("Ueber Switchable:         isOn = " + schaltbar.isSwitchedOn());
        System.out.println("Ueber CountingSwitchable: count = " + zaehlbar.getSwitchCount());
        System.out.println("Ueber Named:              name = " + benannt.getName());
        System.out.println("Das ist Polymorphie! Dasselbe Objekt, verschiedene 'Sichten'.");

        System.out.println("\n=== Alle SW06-Aufgaben abgeschlossen! ===");
    }
}
