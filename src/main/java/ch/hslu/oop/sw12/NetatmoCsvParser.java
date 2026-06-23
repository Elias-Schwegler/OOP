package ch.hslu.oop.sw12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SW12 - Realistischer CSV-Parser fuer Netatmo-Export-Daten (U11 Bonus).
 *
 * Beispiel-Datei: "netatmo-export-202501-202504.csv"
 *
 * Lessons hier:
 *  - CSV-Parsing in Java per BufferedReader + split(",")
 *  - NULL-/Format-Validation
 *  - Encapsulation: Innere {@code Messung}-Klasse als Datentraeger
 *  - In echten Projekten: nutze OpenCSV / Apache Commons CSV statt selbst zu parsen!
 */
public final class NetatmoCsvParser {

    private NetatmoCsvParser() { /* Utility */ }

    /**
     * Eine einzelne Messung. Public, damit Aufrufer mit den Datentraegern arbeiten kann.
     */
    public static final class Messung {
        private final String timestamp;
        private final float temperatur;
        private final float luftfeuchtigkeit;

        public Messung(final String timestamp, final float temperatur, final float luftfeuchtigkeit) {
            this.timestamp = timestamp;
            this.temperatur = temperatur;
            this.luftfeuchtigkeit = luftfeuchtigkeit;
        }

        public String getTimestamp()       { return timestamp; }
        public float  getTemperatur()      { return temperatur; }
        public float  getLuftfeuchtigkeit(){ return luftfeuchtigkeit; }

        @Override
        public String toString() {
            return String.format("Messung[%s, %.1f C, %.0f%% rH]",
                    timestamp, temperatur, luftfeuchtigkeit);
        }
    }

    /**
     * Liest eine Netatmo-CSV. Erwartet Format (3 Spalten):
     * <pre>Timestamp,Temperature,Humidity</pre>
     *
     * Erste Zeile ist Header und wird uebersprungen.
     * Defekte Zeilen werden geloggt (auf Stderr) und uebersprungen.
     *
     * @param path Pfad zur CSV-Datei
     * @return Liste der gueltigen Messungen
     * @throws IOException bei Lese-Fehlern
     */
    public static List<Messung> parse(final String path) throws IOException {
        final List<Messung> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
                lineNo++;
                if (lineNo == 1 || line.isBlank()) continue; // Header skippen

                final String[] parts = line.split(",");
                if (parts.length < 3) {
                    System.err.printf("[Zeile %d] zu wenige Spalten: %s%n", lineNo, line);
                    continue;
                }
                try {
                    final String ts = parts[0].trim();
                    final float t   = Float.parseFloat(parts[1].trim());
                    final float h   = Float.parseFloat(parts[2].trim());
                    result.add(new Messung(ts, t, h));
                } catch (final NumberFormatException nfe) {
                    System.err.printf("[Zeile %d] Parse-Fehler: %s%n", lineNo, nfe.getMessage());
                }
            }
        }
        return result;
    }
}
