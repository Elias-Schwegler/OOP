
/**
 * TEMPLATE 23 - Kopplung, Kohaesion & Designprinzipien (Woche 15).
 *
 * Zwei Qualitaetsmasse fuer gutes Klassendesign:
 *
 * KOHAESION ("wie fokussiert ist EINE Klasse?"):
 *  - HOCH = gut. Eine Klasse macht GENAU EINE Sache richtig.
 *  - Faustregel: Laesst sich die Aufgabe der Klasse in EINEM Satz ohne "und" sagen?
 *
 * KOPPLUNG ("wie stark haengen Klassen voneinander ab?"):
 *  - LOSE = gut. Klassen kennen sich moeglichst wenig.
 *  - Mittel: Abhaengig vom INTERFACE (Vertrag), nicht von der konkreten Klasse.
 *
 * MERKSATZ:  Hohe Kohaesion INNEN, lose Kopplung NACH AUSSEN.
 *
 * ---------------------------------------------------------------
 * Dieses Template zeigt jeweils ein SCHLECHTES und ein GUTES
 * Beispiel und listet die 10 fundamentalen Designprinzipien.
 */
public class _23_KopplungKohaesion {

    // ======================================================
    // KOHAESION - SCHLECHT: God-Klasse macht ZU VIEL
    // ======================================================
    // Eine Klasse mischt Berechnung + Formatierung + Versand.
    // Beschreibung braucht "und ... und ..." -> niedrige Kohaesion.
    public static class RechnungGod {
        private double betrag;

        public void berechne()  { betrag = 100 * 1.081; }   // Geschaeftslogik
        public String drucke()  { return "CHF " + betrag; } // Formatierung
        public void maileKunde() { /* SMTP-Geraffel */ }     // Versand
        public void speichere()  { /* SQL-Geraffel  */ }     // Persistenz
    }

    // ======================================================
    // KOHAESION - GUT: jede Klasse EINE Verantwortung
    // ======================================================
    // Aufteilung nach Verantwortlichkeit (Single Responsibility).
    public static class Rechnung {              // nur Geschaeftslogik/Daten
        private final double netto;
        public Rechnung(double netto) { this.netto = netto; }
        public double brutto() { return netto * 1.081; }
    }

    public static class RechnungDrucker {       // nur Formatierung
        public String format(Rechnung r) {
            return String.format("CHF %.2f", r.brutto());
        }
    }

    // ======================================================
    // KOPPLUNG - SCHLECHT: direkte Abhaengigkeit zur KONKRETEN Klasse
    // ======================================================
    // Notifier ist fest mit EmailSender verdrahtet. Will man spaeter
    // per SMS verschicken, muss man Notifier AENDERN -> enge Kopplung.
    public static class EmailSender {
        public void send(String msg) { System.out.println("E-Mail: " + msg); }
    }

    public static class NotifierEng {
        private final EmailSender sender = new EmailSender(); // new = hartcodiert!
        public void melde(String msg) { sender.send(msg); }
    }

    // ======================================================
    // KOPPLUNG - GUT: Abhaengigkeit nur zum INTERFACE (Vertrag)
    // ======================================================
    // "Program to an interface, not an implementation."
    public interface Sender {                    // der Vertrag
        void send(String msg);
    }

    public static class SmsSender implements Sender {
        public void send(String msg) { System.out.println("SMS: " + msg); }
    }

    public static class PushSender implements Sender {
        public void send(String msg) { System.out.println("Push: " + msg); }
    }

    public static class NotifierLose {
        private final Sender sender;             // kennt nur das Interface
        // Dependency Injection: Abhaengigkeit wird HEREINGEREICHT, nicht "ge-new-t".
        public NotifierLose(Sender sender) { this.sender = sender; }
        public void melde(String msg) { sender.send(msg); }
    }

    // ======================================================
    // ZIELKONFLIKT (Trade-off)
    // ======================================================
    // Mehr Kohaesion -> mehr kleine Klassen -> mehr Verbindungen
    // dazwischen -> tendenziell mehr Kopplung. Man balanciert beides:
    // Interfaces als "Stossdaempfer" halten die Kopplung trotzdem lose.

    // ======================================================
    // DIE 10 FUNDAMENTALEN DESIGNPRINZIPIEN
    // ======================================================
    //  1. DRY  - Don't Repeat Yourself: jede Info existiert NUR EINMAL.
    //  2. KISS - Keep It Simple, Stupid: einfachste Loesung, die funktioniert.
    //  3. YAGNI - You Aren't Gonna Need It: baue nichts "auf Vorrat".
    //  4. Separation of Concerns - trenne unabhaengige Belange (UI/Logik/Daten).
    //  5. Single Responsibility - eine Klasse, ein Grund zur Aenderung.
    //  6. Information Hiding - Implementierung kapseln (private), nur API zeigen.
    //  7. Favor Composition over Inheritance - "hat-ein" statt "ist-ein", flexibler.
    //  8. Program to Interface, not Implementation - vom Vertrag abhaengen.
    //  9. Low Coupling - moeglichst wenige/lose Abhaengigkeiten.
    // 10. High Cohesion - jede Einheit fokussiert auf EINE Aufgabe.

    // ======================================================
    // DEMO
    // ======================================================
    public static void main(String[] args) {
        // --- Hohe Kohaesion: jede Klasse ihr Job, sauber kombiniert ---
        Rechnung r = new Rechnung(100.0);
        RechnungDrucker drucker = new RechnungDrucker();
        System.out.println("Rechnung: " + drucker.format(r));

        // --- Lose Kopplung: derselbe Notifier, austauschbarer Sender ---
        // Genau HIER zahlt sich das Interface aus: ohne Codeaenderung
        // am Notifier wechseln wir die Versandart.
        Sender[] kanaele = { new SmsSender(), new PushSender() };
        for (Sender kanal : kanaele) {
            NotifierLose notifier = new NotifierLose(kanal); // injiziert
            notifier.melde("Bestellung versendet");
        }

        // Pattern Matching (ab Java 21/25) als kurzer Typcheck-Reminder
        Object obj = new SmsSender();
        String art = switch (obj) {
            case SmsSender s  -> "Versand per SMS";
            case PushSender p -> "Versand per Push";
            default           -> "unbekannt";
        };
        System.out.println(art);
    }
}
