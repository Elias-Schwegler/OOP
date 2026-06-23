package ch.hslu.oop.rv;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests fuer {@link RaumVerwaltung}.
 */
class RaumVerwaltungTest {

    private RaumVerwaltung verwaltung;

    @BeforeEach
    void setUp() {
        // WHY @BeforeEach: jeder Test startet mit einer frischen Verwaltung,
        // damit sich die Tests nicht gegenseitig beeinflussen (Isolation).
        verwaltung = new RaumVerwaltung();
    }

    @Test
    @DisplayName("Konstruktor legt 5 Raeume sortiert nach Nummer an")
    void konstruktorLegtRaeumeAn() {
        assertThat(verwaltung.getAlleRaeume())
                .extracting(Raum::getRaumnummer)
                .containsExactly(600, 602, 603, 605, 610);
    }

    @Test
    @DisplayName("getRaum liefert den korrekten Raum")
    void getRaumLiefertRaum() {
        assertThat(verwaltung.getRaum(605).getKapazitaet()).isEqualTo(24);
    }

    @Test
    @DisplayName("getRaum wirft bei unbekannter Nummer")
    void getRaumUnbekannt() {
        assertThatThrownBy(() -> verwaltung.getRaum(999))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("reserviere waehlt den kleinsten passenden freien Raum")
    void reserviereWaehltKleinstenPassenden() {
        // 11 Personen: passende freie Raeume sind 600(18), 603(12), 605(24), 610(12).
        // Kleinster passender ist 603 (Kap 12).
        final Raum raum = verwaltung.reserviere(11);
        assertThat(raum.getRaumnummer()).isEqualTo(603);
        assertThat(raum.getStatus()).isEqualTo(RaumStatus.BELEGT);
    }

    @Test
    @DisplayName("Mehrfach-Reservation 11/6/17 belegt genau 3 Raeume")
    void mehrfachReservation() {
        assertThat(verwaltung.reserviere(11).getRaumnummer()).isEqualTo(603);
        assertThat(verwaltung.reserviere(6).getRaumnummer()).isEqualTo(602);
        assertThat(verwaltung.reserviere(17).getRaumnummer()).isEqualTo(600);

        final long belegte = verwaltung.getAlleRaeume().stream()
                .filter(r -> r.getStatus() == RaumStatus.BELEGT)
                .count();
        assertThat(belegte).isEqualTo(3);
    }

    @Test
    @DisplayName("reserviere wirft, wenn kein Raum gross genug")
    void reserviereKeinPassenderRaum() {
        assertThatThrownBy(() -> verwaltung.reserviere(100))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("reserviere lehnt nicht-positive Personenzahl ab")
    void reserviereUngueltigeAnzahl() {
        assertThatThrownBy(() -> verwaltung.reserviere(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("gibFrei(Raum) gibt belegten Raum frei -> true")
    void gibFreiRaum() {
        final Raum raum = verwaltung.reserviere(11);
        assertThat(verwaltung.gibFrei(raum)).isTrue();
        assertThat(raum.istFrei()).isTrue();
    }

    @Test
    @DisplayName("gibFrei auf bereits freien Raum -> false")
    void gibFreiBereitsFrei() {
        final Raum raum = verwaltung.getRaum(605);
        assertThat(verwaltung.gibFrei(raum)).isFalse();
    }

    @Test
    @DisplayName("gibFrei(int) per Raumnummer (Ueberladung)")
    void gibFreiPerRaumnummer() {
        verwaltung.reserviere(11); // belegt Raum 603
        assertThat(verwaltung.gibFrei(603)).isTrue();
        assertThat(verwaltung.getRaum(603).istFrei()).isTrue();
    }

    @Test
    @DisplayName("Reservation loest ein Event aus")
    void reservationFeuertEvent() {
        // WHY AtomicReference/AtomicInteger: praktische "Mutable Container", um
        // im Lambda Werte nach aussen zu schreiben (effektiv-final-Problem).
        final AtomicInteger anzahlEvents = new AtomicInteger(0);
        final AtomicReference<RaumReservationEvent> letztesEvent = new AtomicReference<>();

        verwaltung.addListener(event -> {
            anzahlEvents.incrementAndGet();
            letztesEvent.set(event);
        });

        final Raum reserviert = verwaltung.reserviere(11);

        assertThat(anzahlEvents.get()).isEqualTo(1);
        assertThat(letztesEvent.get()).isNotNull();
        assertThat(letztesEvent.get().getRaum()).isEqualTo(reserviert);
        assertThat(letztesEvent.get().getAnzahlPlaetze()).isEqualTo(11);
        assertThat(letztesEvent.get().getSource()).isSameAs(verwaltung);
    }

    @Test
    @DisplayName("removeListener: entfernter Listener erhaelt keine Events mehr")
    void removeListener() {
        final AtomicInteger anzahlEvents = new AtomicInteger(0);
        final RaumReservationListener listener = event -> anzahlEvents.incrementAndGet();

        verwaltung.addListener(listener);
        verwaltung.removeListener(listener);
        verwaltung.reserviere(11);

        assertThat(anzahlEvents.get()).isZero();
    }
}
