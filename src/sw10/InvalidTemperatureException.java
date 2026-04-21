package sw10;

/**
 * Eigene CHECKED Exception fuer ungueltige Temperaturwerte.
 *
 * Aufgabe 2l) aus U09: Spezialisierung von Exception
 * -> Behandlung ist PFLICHT (Compiler erzwingt try/catch oder throws)
 *
 * Normalerweise ist fuer Temperatur-Validation IllegalArgumentException (unchecked)
 * besser. Das hier ist nur ein Uebungsbeispiel, um die Mechanik zu zeigen.
 */
public class InvalidTemperatureException extends Exception {

    // Alle 4 Throwable-Konstruktoren ueberschreiben (Best Practice!)

    public InvalidTemperatureException() {
        super();
    }

    public InvalidTemperatureException(final String message) {
        super(message);
    }

    public InvalidTemperatureException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidTemperatureException(final Throwable cause) {
        super(cause);
    }
}
