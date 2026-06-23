package sw12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * SW12 - Konfiguration via {@link java.util.Properties}.
 *
 * Wann Properties statt CSV/JSON?
 *  - Einfache key=value Konfigurationen (z.B. Server-URL, max. Werte)
 *  - Eingebaut in Java -&gt; keine zusaetzliche Lib noetig
 *  - Standard fuer Internationalisierung (i18n) via ResourceBundle
 *
 * Format der .properties-Datei:
 * <pre>
 *   # Kommentar
 *   sensor.id=netatmo-001
 *   sensor.threshold.celsius=25.0
 *   logging.level=INFO
 * </pre>
 */
public final class TemperaturProperties {

    private TemperaturProperties() { /* Utility */ }

    /**
     * Speichert Konfiguration in eine .properties-Datei.
     */
    public static void save(final String path, final Properties props, final String comment) throws IOException {
        try (OutputStream os = new FileOutputStream(path)) {
            props.store(os, comment);
        }
    }

    /**
     * Laedt Konfiguration aus einer .properties-Datei.
     */
    public static Properties load(final String path) throws IOException {
        final Properties props = new Properties();
        try (InputStream is = new FileInputStream(path)) {
            props.load(is);
        }
        return props;
    }

    /**
     * Hilfsmethode: typsicher einen float-Wert laden mit Default-Fallback.
     */
    public static float getFloat(final Properties props, final String key, final float defaultValue) {
        final String value = props.getProperty(key);
        if (value == null) return defaultValue;
        try {
            return Float.parseFloat(value.trim());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }
}
