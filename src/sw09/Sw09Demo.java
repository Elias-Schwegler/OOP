package sw09;

/**
 * Hauptdemo fuer SW09 – fasst alle Konzepte zusammen.
 * Wird von Presentation.java aufgerufen.
 */
public class Sw09Demo {

    // ============ static final Konstante ============
    public static final float KELVIN_OFFSET = 273.15f;

    // ============ Statische Konvertierungsmethoden ============

    public static float convertCelsiusToKelvin(float celsius) {
        return celsius + KELVIN_OFFSET;
    }

    public static float convertKelvinToCelsius(float kelvin) {
        return kelvin - KELVIN_OFFSET;
    }

    // ============ Demo-Methoden ============

    public void runArrayDemo() {
        ArrayDemo demo = new ArrayDemo();
        demo.basicArrayDemo();
        demo.ternaryDemo();
        demo.initializerDemo();

        // Maximum finden
        int[] testArray = {5, 23, 1, 42, 17, 8};
        System.out.println("\n--- Maximum finden ---");
        System.out.print("  Array: ");
        for (int v : testArray) System.out.print(v + " ");
        System.out.println();
        System.out.println("  Maximum: " + demo.findMax(testArray));
        System.out.println("  Index des Maximums: " + demo.findMaxIndex(testArray));
    }

    public void runEnumDemo() {
        System.out.println("\n--- Enumerationen (AggregateState) ---");

        // Enum-Werte durchlaufen
        System.out.println("  Alle Zustaende:");
        for (AggregateState state : AggregateState.values()) {
            System.out.println("    " + state.name() + " = " + state.getGermanName());
        }

        // Elemente mit Aggregatzustand
        Element blei = new Element("Blei", "Pb", 327.5f, 1749.0f);
        Element quecksilber = new Element("Quecksilber", "Hg", -38.83f, 356.73f);
        Element stickstoff = new Element("Stickstoff", "N", -210.0f, -195.79f);

        float testTemp = 20.0f;
        System.out.println("\n  Aggregatzustaende bei " + testTemp + " Grad C:");
        System.out.println("    " + blei.describeStateAt(testTemp));
        System.out.println("    " + quecksilber.describeStateAt(testTemp));
        System.out.println("    " + stickstoff.describeStateAt(testTemp));

        // Switch mit Enum
        System.out.println("\n  Switch-Demo:");
        AggregateState state = blei.getStateAt(testTemp);
        switch (state) {
            case SOLID:
                System.out.println("    " + blei.getName() + " ist ein Feststoff.");
                break;
            case LIQUID:
                System.out.println("    " + blei.getName() + " ist eine Fluessigkeit.");
                break;
            case GAS:
                System.out.println("    " + blei.getName() + " ist ein Gas.");
                break;
        }
    }

    public void runCollectionsDemo() {
        System.out.println("\n--- TemperaturVerlauf (Collections) ---");

        TemperaturVerlauf verlauf = new TemperaturVerlauf();

        // Edge Case: leere Collection
        System.out.println("  Leerer Verlauf: avg=" + verlauf.getAverage()
                + ", max=" + verlauf.getMax());

        // Werte hinzufuegen
        verlauf.add(10.2f);
        verlauf.add(15.8f);
        verlauf.add(21.3f);
        verlauf.add(18.5f);
        verlauf.add(7.1f);

        System.out.println("  Nach 5 Messungen: " + verlauf);
        System.out.println("  Anzahl:       " + verlauf.getCount());
        System.out.println("  Maximum:      " + verlauf.getMax() + " C");
        System.out.println("  Minimum:      " + verlauf.getMin() + " C");
        System.out.println("  Durchschnitt: " + verlauf.getAverage() + " C");
    }

    public void runStaticDemo() {
        System.out.println("\n--- static & final ---");
        System.out.println("  KELVIN_OFFSET (static final): " + KELVIN_OFFSET);
        System.out.println("  20 C -> Kelvin: " + convertCelsiusToKelvin(20.0f));
        System.out.println("  300 K -> Celsius: " + convertKelvinToCelsius(300.0f));
        System.out.println("  Zugriff via Klasse: Sw09Demo.KELVIN_OFFSET = " + Sw09Demo.KELVIN_OFFSET);
    }
}
