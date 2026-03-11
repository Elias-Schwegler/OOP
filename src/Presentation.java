import sw03.Point;
import sw03.Sw03Demo;

public class Presentation {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("HSRLU OOP Präsentation: SW03 & SW04");
        System.out.println("=========================================\n");

        runSw03();
        runSw04();
    }

    private static void runSw03() {
        System.out.println(">>> STARTING SW03 DEMO <<<\n");
        Sw03Demo demo = new Sw03Demo();

        System.out.println("1. Maximum von (5, 10): " + demo.max(5, 10));
        System.out.println("2. Maximum von (10, 25, 15): " + demo.max2(10, 25, 15));
        
        System.out.println("\n3. Aggregatszustände:");
        System.out.println("Wasserstoff (H) gibts nicht: " + demo.getAggregatszustand("H", 20.0f));
        System.out.println("Stickstoff (N) bei -200°C: " + demo.getAggregatszustand("N", -200.0f));
        System.out.println("Blei (Pb) bei 20°C: " + demo.getAggregatszustand("Pb", 20.0f));

        System.out.println("\n4. Quadranten (Point):");
        Point p1 = new Point(5, 5);
        Point p2 = new Point(-3, 8);
        Point p3 = new Point(0, 5); // auf der y-Achse
        System.out.println(p1 + " ist in Quadrant " + p1.getQuadrant());
        System.out.println(p2 + " ist in Quadrant " + p2.getQuadrant());
        System.out.println(p3 + " ist in Quadrant " + p3.getQuadrant() + " (0 = auf Achse/Ursprung)");

        System.out.println("\n5. Iterationen (Schleifen):");
        demo.printNumbers();
        
        System.out.println("\n6. Float-Precision Problem (Endlosschleifen-Falle):");
        demo.dangerousFloatLoop();
        demo.safeForLoop();

        System.out.println("\n7. ASCII Box Printing:");
        demo.printBox(10, 4);
        
        System.out.println("\n>>> SW03 END <<<\n");
    }

    private static void runSw04() {
        System.out.println(">>> STARTING SW04 DEMO <<<\n");
        
        System.out.println("1. Interfaces & Polymorphie (Switchable, Motor, Fahrzeug):");
        sw04.Fahrzeug auto = new sw04.Fahrzeug();
        
        System.out.println("Auto Zustand vor Switch-On:");
        System.out.println("Auto On? " + auto.isSwitchedOn() + " | Motor RPM: " + auto.getMotorRpm());
        
        auto.switchOn(); // Polymorphie-Aufruf auf alle Unterkomponenten
        
        System.out.println("Auto Zustand NACH Switch-On:");
        System.out.println("Auto On? " + auto.isSwitchedOn() + " | Motor RPM: " + auto.getMotorRpm());


        System.out.println("\n2. Datenkapselung & Defensive Copies (Line & Point):");
        sw04.Line line = new sw04.Line(0, 0, 10, 10);
        System.out.println("Ursprüngliche " + line);

        // Versuch, das interne Objekt von aussen zu manipulieren (Information Hiding Test)
        sw03.Point startPoint = line.getStartPoint();
        System.out.println("Getter liefert Startpunkt: " + startPoint);
        
        System.out.println("--> Hacker versucht startPoint.setX(999) aufzurufen...");
        startPoint.setX(999); 
        
        System.out.println("Hat sich die Linie verändert?");
        System.out.println(line);
        System.out.println("...NEIN! Weil getStartPoint() eine DEFENSIVE KOPIE zurückgibt. Kapselung erfolgreich!");

        System.out.println("\n>>> SW04 END <<<\n");
    }
}
