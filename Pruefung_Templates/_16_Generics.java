
import java.util.List;

/**
 * TEMPLATE 16 – Generics (Typ-Parameter <T>).
 *
 * WARUM Generics?
 *  - Typsicherheit zur COMPILE-Zeit (Fehler werden frueh erkannt, nicht erst zur Laufzeit)
 *  - KEIN manuelles Casten mehr noetig (kein (String) box.get())
 *  - Eine Klasse/Methode funktioniert fuer VIELE Typen, ohne Code zu duplizieren
 *
 * Frueher (vor Generics): List speicherte Object -> man musste immer casten
 * und konnte aus Versehen einen falschen Typ reinlegen (-> ClassCastException).
 *
 * Konvention fuer Typ-Parameter-Namen:
 *   T = Type, E = Element, K = Key, V = Value, N = Number
 *
 * ----------------------------------------------------------
 * Dieses Template zeigt:
 *   - Generische Klasse  Box<T>
 *   - Generische Methode <T> ersterOderNull(...)
 *   - Bounded Type       <T extends Comparable<T>> max(...)
 *   - Wildcards + PECS-Merksatz
 */
public class _16_Generics {

    // ======================================================
    // GENERISCHE KLASSE – Box<T> haelt EIN Objekt vom Typ T
    // ======================================================
    // T ist ein Platzhalter. Box<String> -> T wird zu String,
    // Box<Integer> -> T wird zu Integer. EINE Klasse, viele Typen.
    public static class Box<T> {
        private T inhalt;

        public void set(T wert) {   // nimmt genau T entgegen
            this.inhalt = wert;
        }

        public T get() {            // gibt genau T zurueck -> kein Cast noetig!
            return this.inhalt;
        }

        public boolean istLeer() {
            return this.inhalt == null;
        }
    }

    // ======================================================
    // GENERISCHE METHODE – <T> steht VOR dem Rueckgabetyp
    // ======================================================
    // WHY: Die Methode bestimmt T selbst aus dem Argument.
    // So ist sie fuer List<String>, List<Integer>, ... wiederverwendbar.
    public static <T> T ersterOderNull(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            return null;
        }
        return liste.get(0);
    }

    // ======================================================
    // BOUNDED TYPE – <T extends Comparable<T>>
    // ======================================================
    // WHY: Wir wollen max() berechnen, dafuer MUESSEN sich die Werte
    // vergleichen lassen. "extends Comparable<T>" garantiert dem Compiler,
    // dass jedes T eine compareTo(...)-Methode besitzt.
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    // ======================================================
    // WILDCARDS (? ) – fuer flexible Parameter-Typen
    // ======================================================
    // PECS-Merksatz: "Producer Extends, Consumer Super"
    //
    //   List<? extends Number>  -> PRODUCER: man LIEST nur (extends)
    //   List<? super Integer>   -> CONSUMER: man SCHREIBT nur (super)

    // LESEN: akzeptiert List<Integer>, List<Double>, ... (alles Number)
    // -> aus der Liste herausLESEN ist sicher (es ist garantiert Number).
    public static double summe(List<? extends Number> zahlen) {
        double s = 0;
        for (Number n : zahlen) {     // lesen ist erlaubt
            s += n.doubleValue();
        }
        // zahlen.add(...) waere hier VERBOTEN (Compiler weiss exakten Typ nicht)
        return s;
    }

    // SCHREIBEN: akzeptiert List<Integer>, List<Number>, List<Object>
    // -> in die Liste hineinSCHREIBEN von Integer ist sicher.
    public static void fuelleMitZahlen(List<? super Integer> ziel) {
        ziel.add(1);                  // schreiben ist erlaubt
        ziel.add(2);
        // Beim Lesen kaeme nur Object heraus -> daher CONSUMER.
    }

    // ======================================================
    // DEMO
    // ======================================================
    public static void main(String[] args) {
        // --- Box<String>: typsicher, kein Cast beim get() ---
        Box<String> nameBox = new Box<>();   // <> = Diamond Operator (Typ wird abgeleitet)
        nameBox.set("HSLU");
        String name = nameBox.get();         // direkt String, kein (String)-Cast!
        System.out.println("Box-Inhalt: " + name);
        System.out.println("Box leer?  " + nameBox.istLeer());

        // --- Box<Integer>: gleiche Klasse, anderer Typ ---
        Box<Integer> zahlBox = new Box<>();
        zahlBox.set(42);
        int wert = zahlBox.get();
        System.out.println("Zahl-Box:  " + wert);

        // --- Generische Methode: ersterOderNull ---
        var staedte = List.of("Luzern", "Bern", "Zug");
        System.out.println("Erste Stadt: " + ersterOderNull(staedte));
        System.out.println("Leere Liste: " + ersterOderNull(List.of()));

        // --- Bounded Type: max funktioniert fuer jeden Comparable-Typ ---
        System.out.println("max(3, 7)         = " + max(3, 7));
        System.out.println("max(\"Apfel\",\"Birne\") = " + max("Apfel", "Birne"));

        // --- Wildcards: Producer (lesen) / Consumer (schreiben) ---
        System.out.println("Summe: " + summe(List.of(1, 2, 3.5)));

        var sammeln = new java.util.ArrayList<Number>();
        fuelleMitZahlen(sammeln);            // List<Number> ist super von Integer
        System.out.println("Gefuellt: " + sammeln);
    }
}
