
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * TEMPLATE 19 – Lambdas & Funktionale Interfaces.
 *
 * WHY (Kernidee): Mit Lambdas geben wir VERHALTEN als Parameter weiter.
 * Statt "Daten in eine Methode reinstecken" stecken wir "eine Funktion rein".
 * Das ist funktionale Programmierung: Code wird zu Daten, die man herumreichen kann.
 *
 * WAS IST EINE LAMBDA?
 *  - Eine kurze, namenlose Funktion: parameter -> ausdruck
 *  - Sie passt auf JEDES funktionale Interface (= Interface mit GENAU einer
 *    abstrakten Methode). Der Compiler leitet die Typen selbst ab.
 *
 * SYNTAX-VARIANTEN:
 *   ()       -> 42                 // keine Parameter
 *   x        -> x * 2              // ein Parameter (Klammern optional)
 *   (a, b)   -> a + b              // mehrere Parameter
 *   (a, b)   -> { return a + b; }  // Block-Body braucht return + Semikolon
 */
public class _19_Lambdas {

    // ======================================================
    // 1) EIGENES @FunctionalInterface
    // ======================================================
    // WHY: @FunctionalInterface laesst den Compiler PRUEFEN, dass genau EINE
    // abstrakte Methode existiert. Sonst koennten wir kein Lambda zuweisen.
    @FunctionalInterface
    public interface Rechnung {
        int apply(int a, int b);   // die EINE abstrakte Methode
    }

    // ======================================================
    // 2) java.util.function – die Standard-Bausteine
    // ======================================================
    // Merksatz: nimmt-rein / gibt-raus
    //   Predicate<T>      : T  -> boolean   (Test/Filter)
    //   Function<T,R>     : T  -> R         (Umwandlung)
    //   Consumer<T>       : T  -> void      (verbraucht, z.B. ausgeben)
    //   Supplier<T>       : () -> T         (liefert, z.B. erzeugen)
    //   BiFunction<T,U,R> : T,U -> R        (zwei rein, eins raus)

    public static void main(String[] args) {

        // ======================================================
        // EIGENES INTERFACE: dieselbe Signatur, verschiedenes Verhalten
        // ======================================================
        Rechnung plus = (a, b) -> a + b;              // Ausdruck-Lambda
        Rechnung mal  = (a, b) -> a * b;
        Rechnung max  = (a, b) -> {                    // Block-Lambda mit return
            return Math.max(a, b);
        };
        System.out.println("plus: " + plus.apply(3, 4));   // 7
        System.out.println("mal:  " + mal.apply(3, 4));    // 12
        System.out.println("max:  " + max.apply(3, 4));    // 4

        // WHY: Verhalten als Parameter! rechneAus() weiss nicht, WAS gerechnet
        // wird – wir reichen die Operation als Lambda herein.
        System.out.println("via Param: " + rechneAus(10, 5, (a, b) -> a - b)); // 5

        // ======================================================
        // PREDICATE<T> : T -> boolean (testen)
        // ======================================================
        Predicate<Integer> istGerade = n -> n % 2 == 0;
        System.out.println("4 gerade? " + istGerade.test(4));   // true
        // Kombinierbar: .and() / .or() / .negate()
        System.out.println("3 ungerade? " + istGerade.negate().test(3)); // true

        // ======================================================
        // FUNCTION<T,R> : T -> R (umwandeln)
        // ======================================================
        Function<String, Integer> laenge = s -> s.length();
        System.out.println("Laenge 'Hallo': " + laenge.apply("Hallo")); // 5

        // ======================================================
        // CONSUMER<T> : T -> void (verbrauchen)
        // ======================================================
        Consumer<String> drucker = s -> System.out.println("Consumer: " + s);
        drucker.accept("ich verbrauche nur");

        // ======================================================
        // SUPPLIER<T> : () -> T (liefern, ohne Eingabe)
        // ======================================================
        Supplier<String> gruss = () -> "Hallo Welt";
        System.out.println("Supplier: " + gruss.get());

        // ======================================================
        // BIFUNCTION<T,U,R> : (T,U) -> R (zwei rein, eins raus)
        // ======================================================
        BiFunction<Integer, Integer, String> beschreibe =
                (a, b) -> a + " + " + b + " = " + (a + b);
        System.out.println("BiFunction: " + beschreibe.apply(2, 3));

        // ======================================================
        // 3) METHODENREFERENZEN  (Klasse::methode)
        // ======================================================
        // WHY: Wenn das Lambda NUR eine existierende Methode aufruft, ist die
        // Methodenreferenz kuerzer und lesbarer. Vier Formen:

        // (a) Statische / Instanz-Methode eines Objekts: instanz::methode
        Consumer<String> println = System.out::println;     // statt s -> System.out.println(s)
        println.accept("Methodenreferenz: instanz::methode");

        // (b) Methode auf dem Parameter:  Klasse::instanzMethode
        Function<String, String> gross = String::toUpperCase; // statt s -> s.toUpperCase()
        System.out.println(gross.apply("klein->gross"));

        // (c) Konstruktor:  Klasse::new
        Supplier<StringBuilder> bauer = StringBuilder::new;   // statt () -> new StringBuilder()
        System.out.println("Konstruktor-Ref: " + bauer.get().append("frisch gebaut"));

        // (d) gebundene Instanzmethode: konkretesObjekt::methode
        String prefix = "Hallo ";
        Function<String, String> begruesse = prefix::concat;  // statt s -> prefix.concat(s)
        System.out.println(begruesse.apply("HSLU"));

        // ======================================================
        // ZUSAMMENSPIEL: forEach nimmt einen Consumer entgegen
        // ======================================================
        List<String> namen = List.of("Anna", "Beat", "Cyril");
        // WHY: forEach ist HIGHER-ORDER – es bekommt Verhalten (Consumer) uebergeben.
        namen.forEach(System.out::println);
    }

    // ======================================================
    // HIGHER-ORDER METHODE: nimmt selbst eine Funktion entgegen
    // ======================================================
    // WHY: Der Parameter 'op' ist VERHALTEN. So bleibt rechneAus() flexibel,
    // ohne fuer jede Operation eine neue Methode zu schreiben.
    private static int rechneAus(int a, int b, Rechnung op) {
        return op.apply(a, b);
    }
}
