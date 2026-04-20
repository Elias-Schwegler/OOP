import sw03.Point;
import sw03.Sw03Demo;

public class Presentation {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("HSLU OOP Praesentation: SW03 - SW09");
        System.out.println("=========================================\n");

        runSw03();
        runSw04();
        runSw08();
        runSw09();
    }

    private static void runSw03() {
        System.out.println(">>> STARTING SW03 DEMO <<<\n");
        Sw03Demo demo = new Sw03Demo();

        System.out.println("1. Maximum von (5, 10): " + demo.max(5, 10));
        System.out.println("2. Maximum von (10, 25, 15): " + demo.max2(10, 25, 15));

        System.out.println("\n3. Aggregatszustaende:");
        System.out.println("Wasserstoff (H) gibts nicht: " + demo.getAggregatszustand("H", 20.0f));
        System.out.println("Stickstoff (N) bei -200 C: " + demo.getAggregatszustand("N", -200.0f));
        System.out.println("Blei (Pb) bei 20 C: " + demo.getAggregatszustand("Pb", 20.0f));

        System.out.println("\n4. Quadranten (Point):");
        Point p1 = new Point(5, 5);
        Point p2 = new Point(-3, 8);
        Point p3 = new Point(0, 5);
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

        auto.switchOn();

        System.out.println("Auto Zustand NACH Switch-On:");
        System.out.println("Auto On? " + auto.isSwitchedOn() + " | Motor RPM: " + auto.getMotorRpm());

        System.out.println("\n2. Datenkapselung & Defensive Copies (Line & Point):");
        sw04.Line line = new sw04.Line(0, 0, 10, 10);
        System.out.println("Urspruengliche " + line);

        sw03.Point startPoint = line.getStartPoint();
        System.out.println("Getter liefert Startpunkt: " + startPoint);

        System.out.println("--> Hacker versucht startPoint.setX(999) aufzurufen...");
        startPoint.setX(999);

        System.out.println("Hat sich die Linie veraendert?");
        System.out.println(line);
        System.out.println("...NEIN! Weil getStartPoint() eine DEFENSIVE KOPIE zurueckgibt.");

        System.out.println("\n>>> SW04 END <<<\n");
    }

    private static void runSw08() {
        System.out.println(">>> STARTING SW08 DEMO (Test-Korrektur) <<<\n");

        sw08.Sw08TestReview review = new sw08.Sw08TestReview();

        System.out.println("Online Test 1 Ergebnis: 48/58 (82.76%)\n");

        review.demoDoWhile();
        System.out.println();
        review.demoScope(42);
        System.out.println();
        review.demoExtendsVsImplements();
        System.out.println();
        review.demoFloatPrecision();
        System.out.println();
        review.demoInformationHiding();
        System.out.println();
        review.demoVererbungFinal();

        System.out.println("\n>>> SW08 END <<<\n");
    }

    private static void runSw09() {
        System.out.println(">>> STARTING SW09 DEMO <<<\n");

        sw09.Sw09Demo demo = new sw09.Sw09Demo();

        System.out.println("=== 1. Arrays (Kapitel 7) ===");
        demo.runArrayDemo();

        System.out.println("\n=== 2. Enumerationen (O10) ===");
        demo.runEnumDemo();

        System.out.println("\n=== 3. Collections-Einsatz (D02) ===");
        demo.runCollectionsDemo();

        System.out.println("\n=== 4. static & final (O10) ===");
        demo.runStaticDemo();

        System.out.println("\n>>> SW09 END <<<\n");
    }
}
