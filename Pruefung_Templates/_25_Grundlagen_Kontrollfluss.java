
/**
 * TEMPLATE 25 – Grundlagen & Kontrollfluss.
 *
 * Der absolute Java-Werkzeugkasten: Datentypen, Verzweigungen, Schleifen.
 * Diese Dinge braucht man in JEDER Pruefungsaufgabe - also auswendig koennen!
 *
 * THEMEN:
 *  - Elementare (primitive) Datentypen + Wertebereich vs. Genauigkeit
 *  - if / else if / else
 *  - switch als STATEMENT (klassisch) vs. switch als EXPRESSION (modern)
 *  - Schleifen: while, do-while, for, for-each (+ wann welche?)
 *  - Typumwandlung (Cast) und AutoBoxing (primitiv <-> Wrapper)
 *  - var, ternaerer Operator, final
 */
public class _25_Grundlagen_Kontrollfluss {

    // ======================================================
    // ELEMENTARE DATENTYPEN (primitives)
    // ======================================================
    // WARUM kennen? Man muss den passenden Typ waehlen: zu klein -> Overflow,
    // zu ungenau -> falsche Resultate. Faustregel: Ganzzahlen=int, Geld=BigDecimal,
    // wissenschaftlich=double, Schalter=boolean.
    static void datentypen() {
        boolean flag = true;        // nur true/false
        byte b = 127;               //  8 Bit, -128 .. 127
        short s = 32_000;           // 16 Bit, ~ +/- 32 Tsd. (_ ist nur optische Trennung)
        int i = 2_000_000_000;      // 32 Bit, ~ +/- 2 Mrd.  -> DER Standard-Ganzzahltyp
        long l = 9_000_000_000L;    // 64 Bit, riesig        -> Suffix L nicht vergessen!
        float f = 3.14f;            // 32 Bit Kommazahl       -> Suffix f, ~7 Stellen genau
        double d = 3.141592653589;  // 64 Bit Kommazahl       -> Standard fuer Kommazahlen
        char c = 'A';               // EIN Unicode-Zeichen (in Wahrheit eine Zahl: 65)

        // WICHTIG: Wertebereich (wie GROSS) ist nicht dasselbe wie Genauigkeit (wie GENAU)!
        // float/double sind ungenau -> 0.1 + 0.2 != 0.3 (typische Falle!)
        System.out.println("0.1 + 0.2 = " + (0.1 + 0.2)); // 0.30000000000000004
        System.out.println(b + " " + s + " " + i + " " + l + " " + f + " " + d + " " + c + " " + flag);
    }

    // ======================================================
    // if / else if / else  -> mehrere Bedingungen der Reihe nach
    // ======================================================
    static String note(int punkte) {
        if (punkte >= 90) {
            return "sehr gut";
        } else if (punkte >= 60) {   // wird nur geprueft, wenn das obere FALSE war
            return "genuegend";
        } else {
            return "ungenuegend";
        }
    }

    // ======================================================
    // switch STATEMENT (klassisch) vs. switch EXPRESSION (modern)
    // ======================================================
    // STATEMENT: alt, braucht break (sonst "Fallthrough"!), gibt nichts zurueck.
    static void switchStatement(int tag) {
        switch (tag) {
            case 6:
            case 7:                         // mehrere Labels = ODER
                System.out.println("Wochenende");
                break;                      // OHNE break laeuft es in den naechsten case!
            default:
                System.out.println("Arbeitstag");
        }
    }

    // EXPRESSION: modern (Java 14+), -> statt :, KEIN break, liefert einen WERT.
    static String switchExpression(int tag) {
        return switch (tag) {
            case 6, 7 -> "Wochenende";       // mehrere Labels mit Komma
            case 1, 2, 3, 4, 5 -> "Arbeitstag";
            default -> {
                // Brauchst du mehrere Zeilen? Block + yield gibt den Wert zurueck.
                String s = "ungueltig: " + tag;
                yield s;                     // yield = "gib diesen Wert zurueck"
            }
        };
    }

    // ======================================================
    // SCHLEIFEN – vier Varianten + wann welche?
    // ======================================================
    static void schleifen() {
        // while: Bedingung VORHER pruefen -> evtl. 0 Durchlaeufe. Wenn Anzahl unbekannt.
        int n = 3;
        while (n > 0) {
            System.out.print(n + " ");
            n--;                             // WICHTIG: Abbruchbedingung muss sich aendern,
        }                                    // sonst ENDLOSSCHLEIFE (Programm haengt)!

        // do-while: Bedingung NACHHER -> laeuft MINDESTENS einmal (z.B. Menue-Eingabe).
        int x = 10;
        do {
            System.out.print(x + " ");
            x--;
        } while (x > 8);

        // for: wenn die Anzahl bekannt ist (Zaehlschleife). init; bedingung; schritt.
        for (int k = 0; k < 3; k++) {
            System.out.print("k" + k + " ");
        }

        // for-each: das Schoenste fuer Arrays/Collections - kein Index, kein Verzaehlen.
        int[] zahlen = {10, 20, 30};
        int summe = 0;
        for (int z : zahlen) {               // "fuer jedes z aus zahlen"
            summe += z;
        }
        System.out.println("| Summe=" + summe);
    }

    // ======================================================
    // TYPUMWANDLUNG (Cast) & AUTOBOXING
    // ======================================================
    static void umwandlungen() {
        // Cast double -> int: schneidet die Nachkommastellen AB (rundet NICHT!).
        double pi = 3.99;
        int ganz = (int) pi;                 // 3, nicht 4!

        // Cast int -> double: passiert automatisch (kleiner -> groesser, kein Datenverlust).
        int zaehler = 7, teiler = 2;
        double quotient = (double) zaehler / teiler; // 3.5  (ohne Cast waere 3 -> Ganzzahl!)

        // AutoBoxing: primitiv (int) <-> Wrapper-Objekt (Integer) automatisch.
        Integer boxed = zaehler;             // Boxing:   int  -> Integer
        int unboxed = boxed;                 // Unboxing: Integer -> int
        // WARUM Wrapper? Collections speichern nur Objekte (List<Integer>, nie List<int>).

        System.out.println("cast=" + ganz + " quotient=" + quotient
                + " boxed=" + boxed + " unboxed=" + unboxed);
    }

    // ======================================================
    // var, ternaerer Operator, final
    // ======================================================
    static void modern() {
        // var: Typ wird vom Compiler abgeleitet (nur bei lokalen Variablen mit Zuweisung).
        var text = "Hallo";                  // Compiler weiss: String
        var liste = new java.util.ArrayList<Integer>();
        liste.add(42);

        // ternaerer Operator: kurzes if/else als AUSDRUCK -> bedingung ? wennWahr : wennFalsch
        int alter = 20;
        String status = alter >= 18 ? "volljaehrig" : "minderjaehrig";

        // final: Wert kann NACH der Zuweisung nicht mehr geaendert werden (Konstante).
        final double MWST = 0.077;
        // MWST = 0.08;  // <- Compilerfehler! Genau das will man absichern.

        System.out.println(text + " " + liste + " " + status + " MwSt=" + MWST);
    }

    // ======================================================
    // DEMO
    // ======================================================
    public static void main(String[] args) {
        datentypen();
        System.out.println("Note(85)=" + note(85));
        switchStatement(7);
        System.out.println("switchExpr(3)=" + switchExpression(3));
        schleifen();
        umwandlungen();
        modern();
    }
}
