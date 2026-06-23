package ch.hslu.oop.sw12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Demo zu SW12 - IO-Datenstroeme.
 *
 * Schreibt drei Dateien in den temporaeren Ordner und liest sie wieder ein:
 *  - temperaturen.bin   (binary)
 *  - temperaturen.csv   (text/csv)
 *  - sensor.properties  (key=value)
 *
 * Aufruf:
 *   javac sw12/*.java
 *   java sw12.Sw12Demo
 */
public final class Sw12Demo {

    private Sw12Demo() { /* Utility */ }

    public static void main(final String[] args) throws IOException {
        System.out.println("=== SW12 Demo: IO-Datenstroeme ===\n");

        final Path tmpDir = Files.createTempDirectory("oop-sw12-");
        System.out.println("Schreibe Demo-Dateien nach: " + tmpDir);

        final List<Float> werte = Arrays.asList(293.15f, 295.65f, 291.10f, 299.00f);

        // ----- BINARY -----
        final String binPath = tmpDir.resolve("temperaturen.bin").toString();
        TemperaturFileIO.writeBinary(binPath, werte);
        final List<Float> binBack = TemperaturFileIO.readBinary(binPath);
        System.out.println("\n[BINARY]");
        System.out.println("  Geschrieben: " + werte);
        System.out.println("  Gelesen:     " + binBack);
        System.out.println("  Datei-Groesse: " + Files.size(Path.of(binPath)) + " Bytes (4 Byte * n + 4 Byte count)");

        // ----- CSV -----
        final String csvPath = tmpDir.resolve("temperaturen.csv").toString();
        TemperaturFileIO.writeCsv(csvPath, werte);
        final List<Float> csvBack = TemperaturFileIO.readCsv(csvPath);
        System.out.println("\n[CSV]");
        System.out.println("  Geschrieben: " + werte);
        System.out.println("  Gelesen:     " + csvBack);
        System.out.println("  Datei-Groesse: " + Files.size(Path.of(csvPath)) + " Bytes (deutlich groesser, weil Text)");

        // ----- PROPERTIES -----
        final String propPath = tmpDir.resolve("sensor.properties").toString();
        final Properties props = new Properties();
        props.setProperty("sensor.id", "netatmo-001");
        props.setProperty("sensor.threshold.celsius", "25.0");
        props.setProperty("logging.level", "INFO");
        TemperaturProperties.save(propPath, props, "OOP SW12 Demo-Config");
        final Properties propsBack = TemperaturProperties.load(propPath);
        System.out.println("\n[PROPERTIES]");
        System.out.println("  Sensor-ID:        " + propsBack.getProperty("sensor.id"));
        System.out.println("  Threshold (C):    " + TemperaturProperties.getFloat(propsBack, "sensor.threshold.celsius", 0f));
        System.out.println("  Threshold-Wert mit Fallback (fehlt): "
                + TemperaturProperties.getFloat(propsBack, "doesnt.exist", 99f));

        System.out.println("\nFertig. Dateien bleiben in " + tmpDir + " liegen (manuell loeschen falls noetig).");
    }
}
