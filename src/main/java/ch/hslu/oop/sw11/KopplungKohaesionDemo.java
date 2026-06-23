package ch.hslu.oop.sw11;

import java.util.ArrayList;
import java.util.List;

/**
 * SW11 - Klassenentwurf: Kopplung &amp; Kohaesion (P02).
 *
 * Zeigt SCHLECHTES vs. GUTES Design am Beispiel "Bestellung".
 *
 * Kernaussagen aus P02:
 *  - HOHE  Kohaesion = jede Klasse macht GENAU EINE Sache (Single Responsibility)
 *  - GERINGE Kopplung = Klassen kennen sich moeglichst wenig
 *  - "responsibility-driven design": Wer ist VERANTWORTLICH? -&gt; dort gehoert die Methode hin
 *  - "code duplication is a bad smell" -&gt; Refactoring (auslagern)
 *  - "switch-on-type" ist ein bad smell -&gt; Polymorphie statt switch
 *
 * Refactoring-Tools im Editor:
 *  - Methode extrahieren (IntelliJ: Ctrl+Alt+M, VS Code: Ctrl+Shift+R)
 *  - Klasse extrahieren
 *  - Variable umbenennen (F2 in VS Code)
 *  - Inline (umgekehrtes Extrahieren)
 */
public final class KopplungKohaesionDemo {

    private KopplungKohaesionDemo() { /* Utility */ }

    public static void main(final String[] args) {
        System.out.println("=== SW11 Demo: Kopplung & Kohaesion ===\n");
        BestellungGut b = new BestellungGut("Buch 'Clean Code'");
        b.addPosition(new Position("Buch", 35.50));
        b.addPosition(new Position("Versand", 4.90));
        System.out.printf("Total: %.2f CHF (mit %d%% MwSt: %.2f CHF)%n",
                b.summe(), 8, b.summeMitMwSt(0.081));
    }

    // =========================================================
    // SCHLECHT: GOD-Klasse - macht alles, alles ist gekoppelt
    // =========================================================
    @SuppressWarnings("unused")
    static final class BestellungSchlecht {
        // Mischt: Daten, Berechnung, Formatierung, Persistenz, Mailversand...
        private final List<String> positionen = new ArrayList<>();
        private final List<Double> preise    = new ArrayList<>();

        // ❌ Parallele Listen: zwei Felder beschreiben EINEN Begriff -> bad smell
        public void addPosition(final String name, final double preis) {
            positionen.add(name);
            preise.add(preis);
        }

        // ❌ Mischt Berechnung + Formatierung
        public String formatiereRechnung() {
            double s = 0;
            for (Double p : preise) s += p;
            return "Rechnung: Total CHF " + s;
        }

        // ❌ Persistenz hat in dieser Klasse nichts verloren
        public void speichereInDatenbank() {
            // SQL-Geraffel...
        }

        // ❌ Email-Versand auch nicht
        public void sendeBestaetigungsmail() {
            // SMTP-Geraffel...
        }
    }

    // =========================================================
    // GUT: Verantwortlichkeiten getrennt - hohe Kohaesion
    // =========================================================
    static final class Position {
        private final String name;
        private final double preis;
        Position(final String name, final double preis) { this.name = name; this.preis = preis; }
        public String getName()  { return name; }
        public double getPreis() { return preis; }
    }

    static final class BestellungGut {
        private final String referenz;
        private final List<Position> positionen = new ArrayList<>();
        BestellungGut(final String referenz) { this.referenz = referenz; }
        public void addPosition(final Position p) { positionen.add(p); }
        public List<Position> getPositionen() { return List.copyOf(positionen); }
        public String getReferenz()           { return referenz; }

        // ✅ EINE Aufgabe: Summe berechnen
        public double summe() {
            double s = 0;
            for (Position p : positionen) s += p.getPreis();
            return s;
        }
        // ✅ Erweiterung der EINEN Aufgabe (gleiche Verantwortung: Geld)
        public double summeMitMwSt(final double satz) { return summe() * (1 + satz); }
    }

    // ✅ Formatierung in eigener Klasse - geringe Kopplung zu Bestellung
    static final class RechnungsFormatierer {
        public String format(final BestellungGut b) {
            return String.format("Rechnung %s: %.2f CHF", b.getReferenz(), b.summe());
        }
    }

    // ✅ Persistenz in eigener Klasse (Repository-Pattern)
    interface BestellungsRepository {
        void save(BestellungGut b);
    }

    // ✅ Mail-Versand in eigener Klasse
    interface MailService {
        void sendBestaetigung(String to, BestellungGut b);
    }
}
