package sw05;

/**
 * Übung SW05 – Demo/Hauptklasse
 * Testet alle SW05-Übungsaufgaben über die main()-Methode.
 * (Statt BlueJ-Interaktion, gemäss Aufgabe 2.f)
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public class Sw05Demo {

    public static void main(final String[] args) {

        System.out.println("=== SW05: Vererbung & Entwicklungsumgebung ===\n");

        // ─── Aufgabe 1.a) & 1.b): Shape-Hierarchie ───
        System.out.println("--- Aufgabe 1.a/b: Shape-Hierarchie ---");
        Rectangle rect = new Rectangle(0, 0, 10, 5);
        Circle circle = new Circle(3, 4, 7);

        System.out.println(rect);
        System.out.println(circle);

        // Polymorphie: Shape-Variable hält verschiedene Subtypen
        Shape shape1 = rect;    // Rectangle ist-ein Shape
        Shape shape2 = circle;  // Circle ist-ein Shape
        System.out.println("Polymorphe Variable shape1: " + shape1);
        System.out.println("Polymorphe Variable shape2: " + shape2);

        // move() testen (geerbte Methode)
        shape1.move(100, 200);
        System.out.println("Nach move: " + shape1);
        System.out.println();

        // ─── Aufgabe 1.c) & 1.d): Square ───
        System.out.println("--- Aufgabe 1.c/d: Square (extends Rectangle) ---");
        Square square = new Square(0, 0, 6);
        System.out.println(square);
        System.out.println("Seitenlänge: " + square.getSide());
        System.out.println("Umfang: " + square.getPerimeter());
        System.out.println("Fläche: " + square.getArea());

        // Square ist-ein Rectangle ist-ein Shape
        Shape shapeSquare = square;
        Rectangle rectSquare = square;
        System.out.println("Als Shape: " + shapeSquare);
        System.out.println("Als Rectangle: " + rectSquare);
        System.out.println();

        // ─── Aufgabe 1.e) & 1.f): Chemische Elemente ───
        System.out.println("--- Aufgabe 1.e/f: Chemische Elemente ---");
        ChemicalElement nitrogen = new Nitrogen();
        ChemicalElement mercury = new Mercury();
        ChemicalElement lead = new Lead();

        System.out.println(nitrogen);
        System.out.println(mercury);
        System.out.println(lead);

        // Polymorphie: Alle Elemente einheitlich behandeln
        ChemicalElement[] elements = { nitrogen, mercury, lead };
        System.out.println("\nAlle Elemente (polymorphe Schleife):");
        for (ChemicalElement e : elements) {
            System.out.println("  " + e.getSymbol() + " → " + e.getAggregateState());
        }
        System.out.println();

        // ─── Aufgabe 1.g) & 1.h): CountingSwitchable & Named ───
        System.out.println("--- Aufgabe 1.g/h: CountingSwitchable & Named ---");
        CountingMotor motor = new CountingMotor("Hauptmotor");
        System.out.println("Anfangszustand: " + motor);

        motor.switchOn();
        System.out.println("Nach switchOn(): " + motor);

        motor.switchOff();
        System.out.println("Nach switchOff(): " + motor);

        motor.switchOn();
        motor.switchOff();
        motor.switchOn();
        System.out.println("Nach 3 weiteren Schaltvorgängen: " + motor);
        System.out.println("Gesamte Schaltvorgänge: " + motor.getSwitchCount());

        // Named-Interface testen
        motor.setName("Hilfsmotor");
        System.out.println("Nach Umbenennung: " + motor.getName());

        // Polymorphie über Interfaces
        Named namedObj = motor;           // Motor als Named
        CountingSwitchable csObj = motor;  // Motor als CountingSwitchable
        System.out.println("Über Named-Interface: " + namedObj.getName());
        System.out.println("Über CountingSwitchable: " + csObj.getSwitchCount() + " Schaltvorgänge");

        System.out.println("\n=== Alle Tests abgeschlossen! ===");
    }
}
