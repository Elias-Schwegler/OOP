package sw06;

// Hinweis: In einem echten Maven-Projekt wuerden diese Tests unter src/test/java liegen.

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Übung SW06, Aufgabe 3.d)
 * JUnit-Testcases fuer das Calculator-Interface (Test-First-Ansatz).
 * Verwendet polymorphe Referenz (Interface-Typ) fuer das Calculator-Objekt.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
class CalculatorTest {

    // Polymorphie: Variable vom Interface-Typ, Objekt der konkreten Klasse
    private final Calculator calculator = new CalculatorImpl();

    /**
     * Einfache Addition zweier positiver Zahlen.
     */
    @Test
    void testAdditionPositive() {
        assertEquals(5, calculator.addition(2, 3));
    }

    /**
     * Addition mit Null (neutrales Element).
     */
    @Test
    void testAdditionWithZero() {
        assertEquals(7, calculator.addition(7, 0));
        assertEquals(7, calculator.addition(0, 7));
    }

    /**
     * Addition zweier Nullen.
     */
    @Test
    void testAdditionZeroPlusZero() {
        assertEquals(0, calculator.addition(0, 0));
    }

    /**
     * Addition mit negativen Zahlen.
     */
    @Test
    void testAdditionNegative() {
        assertEquals(-5, calculator.addition(-2, -3));
    }

    /**
     * Addition einer positiven und einer negativen Zahl.
     */
    @Test
    void testAdditionMixed() {
        assertEquals(2, calculator.addition(5, -3));
        assertEquals(-2, calculator.addition(-5, 3));
    }

    /**
     * Kommutativgesetz: a + b == b + a.
     */
    @Test
    void testAdditionCommutative() {
        assertEquals(calculator.addition(3, 7), calculator.addition(7, 3));
    }

    /**
     * Aufgabe 3.g) Ueberraschung: Integer-Overflow!
     * Integer.MAX_VALUE + 1 wird negativ (Overflow).
     * Dieser Test dokumentiert das Verhalten – keine Exception, sondern stiller Overflow.
     */
    @Test
    void testAdditionOverflow() {
        // Integer.MAX_VALUE = 2_147_483_647
        // 2_147_483_647 + 1 = -2_147_483_648 (Overflow!)
        int result = calculator.addition(Integer.MAX_VALUE, 1);
        assertEquals(Integer.MIN_VALUE, result);
        // Das ist die "Ueberraschung" aus Aufgabe 3.g)!
    }
}
