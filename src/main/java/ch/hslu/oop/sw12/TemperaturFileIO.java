package ch.hslu.oop.sw12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SW12 - IO-Datenstroeme: Schreiben &amp; Lesen von Temperaturwerten (U11 / O13).
 *
 * Demonstriert:
 *  - BINARY  (DataOutputStream / DataInputStream) -&gt; kompakt, NICHT human readable
 *  - TEXT    (BufferedWriter   / BufferedReader)  -&gt; lesbar (CSV)
 *  - try-with-resources (Stream wird automatisch geschlossen, auch bei Exception)
 *  - Dekorator-Pattern: BufferedXxx wraps FileXxx wraps physische Datei
 *
 * Wichtige Regeln aus O13:
 *  - Streams sind RESSOURCEN -&gt; muessen IMMER geschlossen werden
 *  - try-with-resources ist die EINZIGE richtige Variante seit Java 7
 *  - Buffered* IMMER um File*-Streams wickeln (Performance!)
 *  - Binary != Text! Eine .txt-Datei mit Binaerdaten ist nicht lesbar
 */
public final class TemperaturFileIO {

    private TemperaturFileIO() { /* Utility */ }

    // =====================================================================
    // BINARY: DataOutputStream / DataInputStream
    // =====================================================================

    /**
     * Schreibt Liste von Kelvin-Werten als BINARY (4 Byte float pro Wert).
     * Format: [int count][float v1][float v2]...[float vn]
     *
     * @param path Pfad zur Datei
     * @param werte Liste von Kelvin-Temperaturen
     * @throws IOException bei IO-Fehler
     */
    public static void writeBinary(final String path, final List<Float> werte) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new java.io.BufferedOutputStream(new FileOutputStream(path)))) {
            dos.writeInt(werte.size());
            for (final Float k : werte) {
                dos.writeFloat(k);
            }
        }
    }

    /**
     * Liest Kelvin-Werte aus BINARY-Datei (Format wie {@link #writeBinary}).
     */
    public static List<Float> readBinary(final String path) throws IOException {
        final List<Float> result = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(
                new java.io.BufferedInputStream(new FileInputStream(path)))) {
            final int count = dis.readInt();
            for (int i = 0; i < count; i++) {
                result.add(dis.readFloat());
            }
        }
        return result;
    }

    // =====================================================================
    // TEXT (CSV): BufferedWriter / BufferedReader
    // =====================================================================

    /**
     * Schreibt Werte als CSV: eine Zeile pro Wert.
     * Bei Strings im CSV: Kommas / Anfuehrungszeichen escapen!
     */
    public static void writeCsv(final String path, final List<Float> werte) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("kelvin");
            bw.newLine();
            for (final Float k : werte) {
                bw.write(String.valueOf(k));
                bw.newLine();
            }
        }
    }

    /**
     * Liest CSV-Datei mit Spalte "kelvin" zeilenweise ein.
     * Erste Zeile = Header (wird uebersprungen).
     */
    public static List<Float> readCsv(final String path) throws IOException {
        final List<Float> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                if (line.isBlank()) continue;
                result.add(Float.parseFloat(line.trim()));
            }
        }
        return result;
    }
}
