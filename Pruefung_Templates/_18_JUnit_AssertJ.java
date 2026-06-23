
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

// JUnit-Assertions (statisch importiert -> assertEquals(...) statt Assertions.assertEquals(...))
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// AssertJ-Einstieg (fluent API: assertThat(...).isEqualTo(...))
import static org.assertj.core.api.Assertions.assertThat;

/**
 * TEMPLATE 18 – JUnit 6 + AssertJ (Test-Referenz).
 *
 * ACHTUNG / WICHTIG:
 *  - Diese Datei gehoert nach src/test/java (Maven-Test-Ordner), NICHT nach src/main.
 *  - Sie braucht JUnit 6 + AssertJ (im Projekt via Maven vorhanden).
 *  - Kompiliert NICHT mit reinem javac ohne diese Libs -> nur als Lese-/Spickzettel-Referenz.
 *  - In der echten Pruefung kaeme der Code unter src/test/java/ch/hslu/oop/...
 *
 * TEST-FIRST-HINWEIS (TDD):
 *  Erst den Test schreiben (rot), dann die Implementierung (gruen), dann aufraeumen (refactor).
 *  So definiert der Test das gewuenschte Verhalten, BEVOR man codet.
 *
 * Eigentlich braucht eine Testklasse keine top-level public class und kein main().
 * Fuer dieses Lern-Template kapseln wir alles in EINE Klasse, damit der Stil
 * der anderen Templates passt (eine public class = Dateiname).
 */
public class _18_JUnit_AssertJ {

    // ======================================================
    // SUBJECT UNDER TEST (SUT) – die zu testende Klasse
    // ======================================================
    public static class Konto {
        private int saldo; // Kontostand in CHF (vereinfacht ohne Rappen)

        public Konto(int startSaldo) {
            this.saldo = startSaldo;
        }

        public int getSaldo() {
            return saldo;
        }

        public void einzahlen(int betrag) {
            // WHY: Guard-Clause -> negative Einzahlung macht fachlich keinen Sinn.
            if (betrag <= 0) {
                throw new IllegalArgumentException("Betrag muss positiv sein");
            }
            saldo += betrag;
        }

        public void abheben(int betrag) {
            // WHY: Ueberzug verhindern -> wir werfen eine Exception statt still zu erlauben.
            if (betrag > saldo) {
                throw new IllegalStateException("Ueberzug nicht erlaubt");
            }
            saldo -= betrag;
        }
    }

    // ======================================================
    // TESTKLASSE – Struktur & Lifecycle
    // ======================================================
    // WHY: package-private (kein public) reicht fuer Tests; JUnit findet sie trotzdem.
    static class KontoTest {

        private Konto konto; // frisch pro Test (siehe @BeforeEach)

        // Laeuft VOR JEDEM @Test -> garantiert sauberen, unabhaengigen Startzustand.
        @BeforeEach
        void setUp() {
            konto = new Konto(100);
        }

        // Laeuft NACH JEDEM @Test -> hier z.B. Ressourcen schliessen (Files, DB...).
        @AfterEach
        void tearDown() {
            konto = null;
        }

        // --- JUnit-Assertions ---
        @Test
        @DisplayName("Einzahlen erhoeht den Saldo")
        void einzahlenErhoehtSaldo() {
            konto.einzahlen(50);
            assertEquals(150, konto.getSaldo());     // erwartet, tatsaechlich
            assertTrue(konto.getSaldo() > 100);      // Bedingung muss wahr sein
            assertFalse(konto.getSaldo() == 100);    // Bedingung muss falsch sein
        }

        // assertThrows: prueft, DASS eine Exception fliegt (Lambda = der riskante Code).
        @Test
        @DisplayName("Ueberzug wirft IllegalStateException")
        void abhebenUeberzugWirftException() {
            var ex = assertThrows(
                    IllegalStateException.class,
                    () -> konto.abheben(999) // mehr als Saldo -> muss knallen
            );
            assertEquals("Ueberzug nicht erlaubt", ex.getMessage());
        }

        // --- AssertJ (fluent / gut lesbar) ---
        @Test
        @DisplayName("AssertJ: lesbare fluent Assertions")
        void assertJStil() {
            konto.einzahlen(25);

            assertThat(konto.getSaldo()).isEqualTo(125); // Wertgleichheit
            assertThat(konto.getSaldo()).isPositive();    // > 0
            assertThat(konto).isInstanceOf(Konto.class);  // Typpruefung

            List<String> namen = List.of("Anna", "Beat", "Cleo");
            assertThat(namen).hasSize(3)         // Groesse der Collection
                             .contains("Beat");  // Element enthalten?
        }

        // --- Parametrisierter Test: gleicher Test, viele Eingaben ---
        // WHY: spart copy-paste; jede Zeile aus @ValueSource ist ein eigener Testlauf.
        @ParameterizedTest
        @ValueSource(ints = {1, 10, 50, 100})
        @DisplayName("Einzahlen gueltiger Betraege bleibt positiv")
        void einzahlenGueltig(int betrag) {
            konto.einzahlen(betrag);
            assertThat(konto.getSaldo()).isPositive();
        }
    }
}
