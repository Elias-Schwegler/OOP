package sw06;

// Hinweis: In einem echten Maven-Projekt wuerden diese Tests unter src/test/java liegen.
// Da wir hier ohne Maven arbeiten, liegen sie im selben Package.
// Zum Ausfuehren braucht man JUnit 5 (Jupiter) auf dem Classpath.

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Übung SW06, Aufgabe 2.b) & 2.c)
 * JUnit-Testcases fuer die max(x, y)-Methode.
 * Drei wesentliche Faelle: a > b, a < b, a == b.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
class MaxUtilTest {

    /**
     * Testfall 1: Erster Parameter ist groesser.
     */
    @Test
    void testMaxFirstIsGreater() {
        assertEquals(5, MaxUtil.max(5, 3));
    }

    /**
     * Testfall 2: Zweiter Parameter ist groesser.
     */
    @Test
    void testMaxSecondIsGreater() {
        assertEquals(7, MaxUtil.max(4, 7));
    }

    /**
     * Testfall 3: Beide Parameter sind gleich.
     */
    @Test
    void testMaxBothEqual() {
        assertEquals(3, MaxUtil.max(3, 3));
    }

    /**
     * Zusaetzlich: Negative Zahlen.
     */
    @Test
    void testMaxWithNegativeNumbers() {
        assertEquals(-2, MaxUtil.max(-5, -2));
    }

    /**
     * Zusaetzlich: Null als Grenzfall.
     */
    @Test
    void testMaxWithZero() {
        assertEquals(0, MaxUtil.max(0, -1));
        assertEquals(1, MaxUtil.max(0, 1));
    }

    /**
     * Test fuer max(a, b, c) mit drei Parametern.
     */
    @Test
    void testMaxThreeParams() {
        assertEquals(9, MaxUtil.max(1, 9, 5));
        assertEquals(9, MaxUtil.max(9, 1, 5));
        assertEquals(9, MaxUtil.max(1, 5, 9));
    }
}
