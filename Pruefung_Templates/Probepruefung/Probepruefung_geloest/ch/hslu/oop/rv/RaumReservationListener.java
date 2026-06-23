package ch.hslu.oop.rv;

import java.util.EventListener;

/**
 * Listener, der ueber Reservationen und Freigaben informiert wird.
 *
 * <h2>WHY @FunctionalInterface?</h2>
 * <p>Das Interface hat genau EINE abstrakte Methode. Die Annotation
 * {@code @FunctionalInterface} dokumentiert das und laesst den Compiler
 * pruefen, dass es so bleibt. Dadurch kann der Listener bequem als
 * Lambda-Ausdruck registriert werden, z.B.:</p>
 * <pre>{@code
 * verwaltung.addListener(event ->
 *     LOG.info("Raum {} betroffen", event.getRaum()));
 * }</pre>
 *
 * <h2>WHY extends EventListener?</h2>
 * <p>{@link java.util.EventListener} ist das Marker-Interface des
 * Java-Event-Modells. Es signalisiert, dass es sich um einen Event-Listener
 * handelt (Konvention, passend zu {@link java.util.EventObject}).</p>
 */
@FunctionalInterface
public interface RaumReservationListener extends EventListener {

    /**
     * Wird aufgerufen, wenn ein Raum reserviert oder freigegeben wurde.
     *
     * @param event das ausloesende Event mit Raum und Anzahl Plaetze.
     */
    void onReservation(RaumReservationEvent event);
}
