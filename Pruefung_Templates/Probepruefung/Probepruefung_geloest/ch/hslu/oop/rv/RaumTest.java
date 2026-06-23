package ch.hslu.oop.rv;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests fuer {@link Raum}.
 *
 * <p>WHY in gleichem Package? Der {@link Raum}-Konstruktor und der
 * status-Setter sind package-private. Damit der Test sie aufrufen kann, liegt
 * er im selben Package {@code ch.hslu.oop.rv}.</p>
 */
class RaumTest {

    @Test
    @DisplayName("Neuer Raum ist immer FREI")
    void neuerRaumIstFrei() {
        final Raum raum = new Raum(605, 24);
        assertThat(raum.istFrei()).isTrue();
        assertThat(raum.getStatus()).isEqualTo(RaumStatus.FREI);
    }

    @Test
    @DisplayName("Getter liefern die Konstruktor-Werte")
    void getterLiefernWerte() {
        final Raum raum = new Raum(610, 12);
        assertThat(raum.getRaumnummer()).isEqualTo(610);
        assertThat(raum.getKapazitaet()).isEqualTo(12);
    }

    @Test
    @DisplayName("Raumnummer unter 100 wird abgelehnt")
    void raumnummerZuKlein() {
        assertThatThrownBy(() -> new Raum(99, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Raumnummer");
    }

    @Test
    @DisplayName("Raumnummer ueber 999 wird abgelehnt")
    void raumnummerZuGross() {
        assertThatThrownBy(() -> new Raum(1000, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Raumnummer");
    }

    @Test
    @DisplayName("Kapazitaet von 2 oder weniger wird abgelehnt")
    void kapazitaetZuKlein() {
        assertThatThrownBy(() -> new Raum(600, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Kapazitaet");
    }

    @Test
    @DisplayName("Status laesst sich umschalten")
    void statusUmschalten() {
        final Raum raum = new Raum(600, 18);
        raum.setStatus(RaumStatus.BELEGT);
        assertThat(raum.istFrei()).isFalse();
        assertThat(raum.getStatus()).isEqualTo(RaumStatus.BELEGT);
    }

    @Test
    @DisplayName("equals: gleiche Raumnummer = gleicher Raum (Status egal)")
    void equalsNurUeberRaumnummer() {
        final Raum a = new Raum(600, 18);
        final Raum b = new Raum(600, 99); // andere Kapazitaet, gleiche Nummer
        b.setStatus(RaumStatus.BELEGT);    // anderer Status
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    @DisplayName("equals/hashCode-Contract (EqualsVerifier)")
    void equalsContract() {
        // WHY EqualsVerifier: prueft den equals/hashCode-Contract sehr gruendlich.
        // - Wir vergleichen NUR ueber raumnummer -> nur dieses Feld ist relevant.
        // - Die Klasse ist final -> kein Subklassen-Problem.
        // - Warnung "nonfinal_fields" unterdrueckt, da 'status' bewusst
        //   veraenderlich, aber NICHT Teil von equals ist.
        EqualsVerifier.forClass(Raum.class)
                .withOnlyTheseFields("raumnummer")
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }
}
