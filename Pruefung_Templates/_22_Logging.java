import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TEMPLATE 22 - Logging mit SLF4J + Logback.
 *
 * ACHTUNG: Diese Datei braucht die externe Library slf4j-api
 * (im Maven-Projekt unter pom.xml vorhanden). Sie kompiliert NICHT
 * mit reinem javac ohne diese Lib (import org.slf4j.* schlaegt fehl).
 * Im echten HSLU-Projekt laeuft das problemlos.
 *
 * ----------------------------------------------------------
 * WHY LOGGING statt System.out.println?
 *  - LEVEL steuerbar: pro Klasse/Paket ein- und ausschaltbar (error..trace).
 *  - ABSCHALTBAR ohne Code-Aenderung: nur logback.xml anpassen, nicht neu kompilieren.
 *  - FORMATIERT: Zeitstempel, Thread, Level, Logger-Name automatisch dabei.
 *  - ZIELE: Konsole UND/ODER Datei (Appender), je nach Konfiguration.
 *  - System.out.println dagegen ist immer an, ohne Kontext, schwer entfernbar.
 *
 * SLF4J = Fassade (API), Logback = Implementierung (macht die Arbeit).
 * Man programmiert gegen SLF4J und kann die Implementierung austauschen.
 */
public class _22_Logging {

    // ======================================================
    // DER LOGGER - immer dieses Idiom verwenden!
    // ======================================================
    // private  -> nur diese Klasse nutzt ihn
    // static   -> einer pro Klasse (nicht pro Objekt), spart Speicher
    // final    -> wird nie neu zugewiesen
    // .class   -> Logger kennt so seinen Namen (erscheint in der Ausgabe,
    //             dient zum gezielten Konfigurieren in logback.xml)
    private static final Logger LOGGER = LoggerFactory.getLogger(_22_Logging.class);

    // ======================================================
    // BEISPIEL-KLASSE die in ihren Methoden loggt
    // ======================================================
    public static class Konto {
        private double saldo;

        public void einzahlen(final double betrag) {
            // DEBUG: Detail fuer Entwickler, im Normalbetrieb (INFO) unsichtbar.
            LOGGER.debug("einzahlen() aufgerufen mit betrag={}", betrag);

            if (betrag <= 0) {
                // WARN: verdaechtig, aber kein Absturz - Programm laeuft weiter.
                LOGGER.warn("Ungueltiger Betrag {} wird ignoriert", betrag);
                return;
            }
            this.saldo += betrag;
            // INFO: wichtiges Geschaeftsereignis, das man im Normalbetrieb sehen will.
            LOGGER.info("Einzahlung {} -> neuer Saldo {}", betrag, this.saldo);
        }
    }

    // ======================================================
    // DEMO: alle Log-Level + Exception loggen
    // ======================================================
    public static void main(final String[] args) {

        // --- DIE 5 LEVEL (von laut/wichtig zu leise/detailliert) -------------
        // Faustregel: Welches Level WANN?
        LOGGER.error("ERROR: schwerer Fehler, Funktion gescheitert");   // muss man wissen
        LOGGER.warn("WARN: verdaechtig, aber Programm laeuft weiter");  // Vorsicht
        LOGGER.info("INFO: normaler Programmablauf, Geschaeftsereignis");// Standardbetrieb
        LOGGER.debug("DEBUG: Detailinfo zum Suchen von Fehlern");       // Entwicklung
        LOGGER.trace("TRACE: extrem feine Schritte (z.B. Schleifen)");  // selten an

        // HINWEIS: logback.xml steht im Projekt auf root level="INFO".
        // -> error/warn/info erscheinen, debug/trace werden UNTERDRUECKT.
        // Aendert man das Level auf DEBUG, erscheinen sie - OHNE Code-Aenderung!

        // --- PARAMETRISIERTES LOGGING mit {} ---------------------------------
        // Die {} sind Platzhalter, die der Reihe nach gefuellt werden.
        final int x = 42;
        final String name = "Saldo";
        LOGGER.info("{} ist {}", name, x);   // -> "Saldo ist 42"

        // WHY {} statt String-Konkatenation ("... " + x)?
        // Bei "..." + x wird der String IMMER gebaut, auch wenn das Level aus ist.
        // Mit {} baut SLF4J den String NUR, wenn wirklich geloggt wird.
        // -> spart Rechenzeit bei abgeschalteten Levels (z.B. teures x.toString()).
        LOGGER.debug("Teure Berechnung erst bei Bedarf: {}", x);  // bei INFO: kein String!

        // --- EXCEPTION RICHTIG LOGGEN ----------------------------------------
        try {
            riskanteAktion();
        } catch (final IllegalStateException e) {
            // WICHTIG: Exception als LETZTES Argument (OHNE {}) -> Stacktrace wird geloggt.
            LOGGER.error("Aktion fehlgeschlagen", e);

            // FALSCH waere: LOGGER.error("Fehler: " + e.getMessage());
            //   -> verliert den kompletten Stacktrace (Wo? Welche Ursache?).
        }

        // --- LOGGER in einer Methode/Objekt verwenden ------------------------
        final Konto konto = new Konto();
        konto.einzahlen(100.0);   // INFO erscheint
        konto.einzahlen(-5.0);    // WARN erscheint
    }

    /**
     * Wirft absichtlich eine Exception, um das Exception-Logging zu zeigen.
     */
    private static void riskanteAktion() {
        throw new IllegalStateException("Etwas ist schiefgelaufen");
    }
}
