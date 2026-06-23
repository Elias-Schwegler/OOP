
import java.io.IOException;

/**
 * TEMPLATE 17 – Exceptions (Ausnahmebehandlung).
 *
 * WARUM Exceptions?
 *  - Trennt den NORMALFALL (Programmlogik) vom FEHLERFALL (Behandlung).
 *  - Ein Fehler "blubbert" den Aufrufstapel hoch, bis ihn jemand faengt.
 *  - Verhindert, dass man jeden Rueckgabewert manuell auf -1/null pruefen muss.
 *
 * CHECKED (extends Exception):
 *  - Compiler ERZWINGT Behandlung: try/catch ODER throws in der Signatur.
 *  - Fuer erwartbare, behebbare Fehler (Datei fehlt, Netzwerk weg).
 *
 * UNCHECKED (extends RuntimeException):
 *  - Compiler erzwingt NICHTS. Fuer Programmierfehler (null, falsches Argument).
 *  - Beispiele aus JDK: IllegalArgumentException, NullPointerException.
 *
 * FAUSTREGEL: "Kann der Aufrufer sinnvoll reagieren?" -> checked, sonst unchecked.
 *
 * Hinweis: Exceptions testet man mit assertThrows(...) -> siehe _18_JUnit_AssertJ.
 */
public class _17_Exceptions {

    // ======================================================
    // EIGENE CHECKED EXCEPTION
    // ======================================================
    // extends Exception -> der Compiler zwingt zur Behandlung.
    public static class KontoUeberzogenException extends Exception {
        // Konstruktor(message): nur eine Fehlermeldung.
        public KontoUeberzogenException(final String message) {
            super(message);
        }
        // Konstruktor(message, cause): WARUM cause? Damit die urspruengliche
        // Exception nicht verloren geht (Chaining / "caused by:" im Stacktrace).
        public KontoUeberzogenException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    // ======================================================
    // EIGENE UNCHECKED EXCEPTION
    // ======================================================
    // extends RuntimeException -> keine throws-Pflicht, signalisiert Bug/Missbrauch.
    public static class UngueltigerBetragException extends RuntimeException {
        public UngueltigerBetragException(final String message) {
            super(message);
        }
        public UngueltigerBetragException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    // ======================================================
    // TRY-WITH-RESOURCES: eigene AutoCloseable-Klasse
    // ======================================================
    // WARUM AutoCloseable? close() wird AUTOMATISCH aufgerufen, sobald der
    // try-Block endet – egal ob normal oder per Exception. Kein vergessenes close()!
    public static class Ressource implements AutoCloseable {
        private final String name;
        public Ressource(final String name) {
            this.name = name;
            System.out.println("OEFFNE " + name);
        }
        public void benutze() {
            System.out.println("BENUTZE " + name);
        }
        @Override
        public void close() {
            // Wird garantiert aufgerufen (auch bei Exception im try).
            System.out.println("SCHLIESSE " + name);
        }
    }

    // ======================================================
    // throw und throws
    // ======================================================
    // "throws KontoUeberzogenException" -> Teil der Signatur (Pflicht bei checked).
    public static int abheben(final int kontostand, final int betrag)
            throws KontoUeberzogenException {
        // throw bei unchecked: kein throws noetig, kennzeichnet ein Programmierfehler.
        if (betrag <= 0) {
            throw new UngueltigerBetragException("Betrag muss positiv sein: " + betrag);
        }
        if (betrag > kontostand) {
            // throw bei checked: der Aufrufer MUSS damit umgehen.
            throw new KontoUeberzogenException(
                    "Deckung fehlt: " + betrag + " > " + kontostand);
        }
        return kontostand - betrag;
    }

    // Beispiel fuer "cause": wir fangen einen Low-Level-Fehler und werfen
    // eine fachliche Exception mit dem Original als Ursache.
    public static int parseBetrag(final String text) throws KontoUeberzogenException {
        try {
            return Integer.parseInt(text);
        } catch (final NumberFormatException e) {
            // WHY cause: Ursache (e) anhaengen -> "caused by:" bleibt sichtbar.
            throw new KontoUeberzogenException("Kein gueltiger Betrag: " + text, e);
        }
    }

    // ======================================================
    // MULTI-CATCH + try/catch/finally
    // ======================================================
    // catch (A | B e): ein Block fuer mehrere Typen -> spart Code-Duplikate.
    public static void demoMultiCatch(final String eingabe) {
        try {
            if (eingabe.isEmpty()) {
                throw new IOException("leere Eingabe simuliert");
            }
            int wert = Integer.parseInt(eingabe);   // kann NumberFormatException werfen
            System.out.println("OK, Wert = " + wert);
        } catch (final IOException | NumberFormatException e) {
            // e ist hier effektiv final -> man darf ihn nicht neu zuweisen.
            System.out.println("Gefangen (" + e.getClass().getSimpleName()
                    + "): " + e.getMessage());
        } finally {
            // finally GARANTIERT: laeuft IMMER (Erfolg, Exception, sogar return).
            // Ideal fuer Aufraeumen. (try-with-resources ist dafuer oft eleganter.)
            System.out.println("finally: Aufraeumen erledigt");
        }
    }

    // ======================================================
    // DEMO
    // ======================================================
    public static void main(final String[] args) {
        // 1) Checked Exception ausloesen und fangen
        try {
            int rest = abheben(100, 150);   // wirft KontoUeberzogenException
            System.out.println("Neuer Stand: " + rest);
        } catch (final KontoUeberzogenException e) {
            System.out.println("Gefangen: " + e.getMessage());
        }

        // 2) Chaining mit cause sichtbar machen
        try {
            parseBetrag("zwoelf");
        } catch (final KontoUeberzogenException e) {
            System.out.println("Gefangen: " + e.getMessage()
                    + " | cause = " + e.getCause().getClass().getSimpleName());
        }

        // 3) Multi-Catch + finally
        demoMultiCatch("");        // -> IOException
        demoMultiCatch("abc");     // -> NumberFormatException
        demoMultiCatch("42");      // -> OK

        // 4) try-with-resources: close() laeuft automatisch
        try (Ressource r = new Ressource("Datei.txt")) {
            r.benutze();
        }   // <- hier wird close() automatisch aufgerufen

        // 5) Unchecked demonstrieren. WHY zwei catch-Bloecke?
        // abheben(...) deklariert throws KontoUeberzogenException (checked) ->
        // diese MUSS behandelt werden, auch wenn wir hier die unchecked erwarten.
        try {
            abheben(100, -5);   // wirft UngueltigerBetragException (unchecked)
        } catch (final UngueltigerBetragException e) {
            System.out.println("Gefangen (unchecked): " + e.getMessage());
        } catch (final KontoUeberzogenException e) {
            System.out.println("Gefangen (checked): " + e.getMessage());
        }
    }
}
