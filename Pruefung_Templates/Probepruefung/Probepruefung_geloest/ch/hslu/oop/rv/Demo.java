package ch.hslu.oop.rv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo-Programm, das die Raumverwaltung exemplarisch durchspielt.
 *
 * <p>WHY SLF4J statt System.out? In professionellem Code wird ueber einen
 * Logger ausgegeben (Log-Level, Format, Ziele konfigurierbar). Hier nutzen wir
 * den SLF4J-Logger; das Backend (LogBack) liegt auf dem Classpath.</p>
 */
public final class Demo {

    private static final Logger LOG = LoggerFactory.getLogger(Demo.class);

    // WHY private Konstruktor: Demo ist eine reine "main-Klasse" und soll nicht
    // instanziiert werden.
    private Demo() {
    }

    /**
     * Einstiegspunkt der Demo.
     *
     * @param args werden nicht verwendet.
     */
    public static void main(final String[] args) {
        final RaumVerwaltung verwaltung = new RaumVerwaltung();

        // WHY Lambda: Dank @FunctionalInterface koennen wir den Listener mit
        // minimalem Aufwand registrieren. Er loggt jedes Event auf INFO-Level.
        verwaltung.addListener(event ->
                LOG.info("Event: Raum {} fuer {} Plaetze",
                        event.getRaum().getRaumnummer(), event.getAnzahlPlaetze()));

        // Reservationen fuer 11, 6 und 17 Personen.
        // Erwartung (kleinster passender freier Raum):
        //   11 -> Raum 603 (Kap 12)
        //    6 -> Raum 602 (Kap 6)
        //   17 -> Raum 600 (Kap 18)
        reserviere(verwaltung, 11);
        reserviere(verwaltung, 6);
        reserviere(verwaltung, 17);

        // Alle Raeume ausgeben.
        LOG.info("--- Aktueller Stand aller Raeume ---");
        for (final Raum raum : verwaltung.getAlleRaeume()) {
            LOG.info("{}", raum);
        }

        // Pruefen, ob genau 3 Raeume belegt sind.
        final long belegte = verwaltung.getAlleRaeume().stream()
                .filter(r -> r.getStatus() == RaumStatus.BELEGT)
                .count();
        LOG.info("Anzahl belegter Raeume: {} (erwartet: 3) -> {}",
                belegte, belegte == 3 ? "OK" : "FEHLER");
    }

    /**
     * Kleiner Helfer, der eine Reservation durchfuehrt und das Ergebnis loggt.
     */
    private static void reserviere(final RaumVerwaltung verwaltung, final int personen) {
        final Raum raum = verwaltung.reserviere(personen);
        LOG.info("Reserviert fuer {} Personen: {}", personen, raum);
    }
}
