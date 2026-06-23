import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TEMPLATE 20 – Streams (Woche 13).
 *
 * Ein Stream ist KEINE Datenstruktur, sondern eine PIPELINE, durch die
 * Elemente fliessen. Man beschreibt deklarativ WAS passieren soll
 * (filter, map, ...) statt WIE man es per Schleife macht.
 *
 * AUFBAU EINER PIPELINE (immer dieselben 3 Teile):
 *   1) QUELLE          -> z.B. list.stream(), IntStream.range(...)
 *   2) INTERMEDIATE-Ops -> filter/map/sorted/distinct/limit
 *                          -> geben wieder einen Stream zurueck, sind LAZY
 *                          -> (passiert nichts, bis eine Terminal-Op kommt!)
 *   3) TERMINAL-Op      -> collect/forEach/count/reduce/anyMatch/findFirst
 *                          -> startet die Verarbeitung, liefert ein Ergebnis
 *
 * WHY deklarativ?  Weniger Boilerplate, lesbarer, kein manuelles Index-Gehampel.
 * WHY lazy?        Intermediate-Ops werden erst ausgefuehrt, wenn die Terminal-Op
 *                  sie "zieht" -> z.B. bei limit(3) werden nur 3 Elemente verarbeitet.
 */
public class _20_Streams {

    // ======================================================
    // INTERMEDIATE-OPERATIONEN (Stream -> Stream, lazy)
    // ======================================================
    static void intermediateDemo(List<String> namen) {
        // filter  = behalte nur Elemente, die das Praedikat erfuellen
        // map     = wandle jedes Element um (hier: in Grossbuchstaben)
        // sorted  = sortiere (natuerliche Ordnung)
        // distinct= entferne Duplikate
        // limit   = nimm nur die ersten n
        List<String> result = namen.stream()
                .filter(n -> n.length() >= 4)        // INTERMEDIATE
                .map(String::toUpperCase)            // INTERMEDIATE (Methodenreferenz)
                .distinct()                          // INTERMEDIATE
                .sorted()                            // INTERMEDIATE
                .limit(3)                            // INTERMEDIATE
                .collect(Collectors.toList());       // TERMINAL -> jetzt laeuft alles

        System.out.println("Gefiltert/sortiert: " + result);
    }

    // ======================================================
    // TERMINAL-OPERATIONEN (starten die Pipeline)
    // ======================================================
    static void terminalDemo(List<Integer> zahlen) {
        // count = wie viele Elemente kommen durch?
        long anzahlGerade = zahlen.stream()
                .filter(z -> z % 2 == 0)
                .count();                            // TERMINAL -> long
        System.out.println("Gerade Zahlen: " + anzahlGerade);

        // reduce = falte den Stream zu EINEM Wert zusammen (hier: Summe)
        // Startwert 0, dann immer (Zwischenergebnis, naechstes Element) -> neu
        int summe = zahlen.stream()
                .reduce(0, (a, b) -> a + b);         // TERMINAL -> int
        System.out.println("Summe (reduce): " + summe);

        // anyMatch = gibt es mindestens ein Element, das passt? -> boolean
        boolean hatGrosse = zahlen.stream().anyMatch(z -> z > 100);
        System.out.println("Zahl > 100 vorhanden? " + hatGrosse);

        // forEach = Seiteneffekt fuer jedes Element (hier: Ausgabe)
        System.out.print("forEach: ");
        zahlen.stream().limit(3).forEach(z -> System.out.print(z + " "));
        System.out.println();
    }

    // ======================================================
    // OPTIONAL – Terminal-Ops koennen "nichts" liefern
    // ======================================================
    static void optionalDemo(List<Integer> zahlen) {
        // findFirst liefert KEIN int, sondern Optional<Integer>:
        // es koennte ja kein passendes Element geben -> kein Wert.
        Optional<Integer> ersteUeber50 = zahlen.stream()
                .filter(z -> z > 50)
                .findFirst();                        // TERMINAL -> Optional

        // orElse  = Wert auspacken ODER Fallback, wenn leer
        int wert = ersteUeber50.orElse(-1);
        System.out.println("Erste > 50 (orElse): " + wert);

        // ifPresent = Code nur ausfuehren, wenn ein Wert da ist (kein null-Check noetig)
        ersteUeber50.ifPresent(z -> System.out.println("ifPresent fand: " + z));
    }

    // ======================================================
    // COLLECTORS – Endergebnis einsammeln/umformen
    // ======================================================
    static void collectorsDemo(List<String> namen) {
        // joining = alle Strings zu EINEM zusammenkleben, mit Trennzeichen
        String csv = namen.stream().collect(Collectors.joining(", "));
        System.out.println("joining: " + csv);

        // groupingBy = Map bilden: Schluessel -> Liste der Elemente
        // hier gruppiert nach Wortlaenge
        Map<Integer, List<String>> nachLaenge = namen.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("groupingBy(length): " + nachLaenge);

        // groupingBy + counting = Schluessel -> ANZAHL (Downstream-Collector)
        Map<Character, Long> nachAnfangsbuchstabe = namen.stream()
                .collect(Collectors.groupingBy(
                        n -> n.charAt(0),            // Schluessel
                        Collectors.counting()));     // statt Liste -> Anzahl
        System.out.println("groupingBy + counting: " + nachAnfangsbuchstabe);
    }

    // ======================================================
    // IntStream – fuer Zahlenbereiche & Statistik
    // ======================================================
    static void intStreamDemo() {
        // range(0, 5) erzeugt 0,1,2,3,4 (Obergrenze EXKLUSIV)
        int summe = IntStream.range(0, 5).sum();     // 0+1+2+3+4 = 10
        System.out.println("IntStream.range(0,5).sum() = " + summe);

        // average() liefert OptionalDouble (Bereich koennte leer sein)
        double schnitt = IntStream.range(1, 6).average().orElse(0.0);
        System.out.println("Durchschnitt 1..5 = " + schnitt);

        // mapToInt: aus einem Objekt-Stream einen primitiven IntStream machen,
        // damit sum()/average() ueberhaupt verfuegbar sind.
        List<String> woerter = List.of("a", "bb", "ccc");
        int summeLaengen = woerter.stream()
                .mapToInt(String::length)            // Stream<String> -> IntStream
                .sum();
        System.out.println("Summe der Wortlaengen = " + summeLaengen);
    }

    // ======================================================
    // DEMO
    // ======================================================
    public static void main(String[] args) {
        List<String> namen = List.of("Anna", "Tom", "Bea", "Anna", "Christian", "Bob");
        List<Integer> zahlen = List.of(3, 42, 7, 128, 60, 15, 200, 4);

        System.out.println("=== Intermediate ===");
        intermediateDemo(namen);

        System.out.println("\n=== Terminal ===");
        terminalDemo(zahlen);

        System.out.println("\n=== Optional ===");
        optionalDemo(zahlen);

        System.out.println("\n=== Collectors ===");
        collectorsDemo(namen);

        System.out.println("\n=== IntStream ===");
        intStreamDemo();
    }
}
