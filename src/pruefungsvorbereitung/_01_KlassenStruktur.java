package pruefungsvorbereitung;

/**
 * TEMPLATE 01 – Korrekte Klassenstruktur und Konventionen.
 *
 * Was dieses Template zeigt:
 *  - Korrekte Reihenfolge: Attribute, Konstruktoren, Methoden
 *  - JavaDoc-Standard (@author, @version, @param, @return)
 *  - Naming-Konventionen (camelCase fuer Attribute, PascalCase fuer Klassen)
 *  - Package-Struktur (vollqualifizierter Name: ch.hslu.oop.pruefungsvorbereitung.Person)
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public class _01_KlassenStruktur {

    // ======================================================
    // 1. ATTRIBUTE (zuerst, private, am Anfang)
    // ======================================================

    // Klassenattribut (static) – gehoert zur Klasse, nicht zur Instanz
    private static int instanzZaehler = 0;

    // Konstante (static final, UPPERCASE)
    public static final int MAX_ALTER = 150;

    // Instanz-Attribute (private, camelCase)
    private final String name;      // final = einmal zuweisbar (im Konstruktor)
    private int alter;
    private String email;

    // ======================================================
    // 2. KONSTRUKTOREN
    // ======================================================

    /**
     * Standard-Konstruktor.
     * @param name Name der Person (nicht null)
     * @param alter Alter (zwischen 0 und MAX_ALTER)
     */
    public _01_KlassenStruktur(final String name, final int alter) {
        this.name = name;
        this.alter = alter;
        this.email = null;
        instanzZaehler++;
    }

    /**
     * Ueberladener Konstruktor mit Email.
     * Ruft den anderen Konstruktor mit this(...) auf!
     */
    public _01_KlassenStruktur(final String name, final int alter, final String email) {
        this(name, alter);  // DELEGIERT an den anderen Konstruktor
        this.email = email;
    }

    // ======================================================
    // 3. OEFFENTLICHE METHODEN (public)
    // ======================================================

    // Getter (Konvention: get + AttributName)
    public String getName() {
        return this.name;
    }

    public int getAlter() {
        return this.alter;
    }

    // Boolean-Getter: is + AttributName
    public boolean isErwachsen() {
        return this.alter >= 18;
    }

    // Setter (Konvention: set + AttributName, returnt void)
    public void setAlter(final int alter) {
        if (alter < 0 || alter > MAX_ALTER) {
            throw new IllegalArgumentException("Alter ungueltig: " + alter);
        }
        this.alter = alter;
    }

    /**
     * Erhoeht das Alter um ein Jahr (Geburtstag).
     */
    public void geburtstagFeiern() {
        this.alter++;
    }

    // ======================================================
    // 4. UEBRIGE METHODEN (protected, package)
    // ======================================================

    protected void internalUpdate() {
        // Nur fuer Subklassen + Package
    }

    // ======================================================
    // 5. PRIVATE METHODEN (zuletzt)
    // ======================================================

    private boolean emailValidieren(final String email) {
        return email != null && email.contains("@");
    }

    // ======================================================
    // STATIC MAIN (nur fuer Demo, bei Prod-Code NICHT in Datenklasse!)
    // ======================================================

    public static void main(String[] args) {
        _01_KlassenStruktur p1 = new _01_KlassenStruktur("Elias", 22);
        _01_KlassenStruktur p2 = new _01_KlassenStruktur("Alice", 25, "alice@hslu.ch");

        System.out.println(p1.getName() + " ist " + p1.getAlter() + " Jahre alt.");
        System.out.println("Erwachsen? " + p1.isErwachsen());
        System.out.println("Instanzen insgesamt: " + instanzZaehler);
    }
}
