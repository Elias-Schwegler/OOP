package pruefungsvorbereitung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TEMPLATE 02 – Immutable Class + Defensive Kopien.
 *
 * Was dieses Template zeigt:
 *  - final class (nicht spezialisierbar)
 *  - Alle Attribute final (nur im Konstruktor setzbar)
 *  - KEINE Setter
 *  - Bei Rueckgabe von Objekt-Referenzen: DEFENSIVE KOPIE
 *  - "Wither" statt Setter: setFoo() gibt ein NEUES Objekt zurueck
 *
 * Warum immutable?
 *  - Thread-sicher
 *  - Kann bedenkenlos als Map-Key verwendet werden
 *  - Keine Seiteneffekte
 *  - Information Hiding maximal durchgezogen
 */
public final class _02_Immutable {

    // Alle Attribute final
    private final String name;
    private final int alter;
    private final List<String> hobbies;  // Mutable Collection -> besonders aufpassen!

    /**
     * Konstruktor: Eingehende Mutable-Objekte defensiv kopieren!
     */
    public _02_Immutable(final String name, final int alter, final List<String> hobbies) {
        this.name = name;
        this.alter = alter;
        // WICHTIG: defensive Kopie beim Einlesen
        this.hobbies = new ArrayList<>(hobbies);
    }

    public String getName() {
        return this.name;
    }

    public int getAlter() {
        return this.alter;
    }

    /**
     * Defensive Kopie beim Ausgeben!
     * Sonst koennte der Aufrufer unsere interne Liste modifizieren.
     *
     * Alternativen:
     *  - return new ArrayList<>(hobbies);
     *  - return List.copyOf(hobbies);  // ab Java 10, garantiert immutable
     *  - return Collections.unmodifiableList(hobbies);  // Wrapper
     */
    public List<String> getHobbies() {
        return Collections.unmodifiableList(this.hobbies);
    }

    /**
     * "Wither" statt Setter: Gibt ein NEUES Objekt mit modifiziertem Attribut zurueck.
     * Das alte Objekt bleibt unveraendert (immutable!).
     */
    public _02_Immutable withAlter(final int neuesAlter) {
        return new _02_Immutable(this.name, neuesAlter, this.hobbies);
    }

    @Override
    public String toString() {
        return name + "(" + alter + ")";
    }

    // ======================================================
    // DEMO: Warum immutable so maechtig ist
    // ======================================================

    public static void main(String[] args) {
        List<String> meineHobbies = new ArrayList<>();
        meineHobbies.add("Code");
        meineHobbies.add("Sport");

        _02_Immutable elias = new _02_Immutable("Elias", 22, meineHobbies);

        // Hacker-Versuch 1: Input-Liste modifizieren
        meineHobbies.add("Hacken");  // externer Versuch!
        System.out.println("Elias' Hobbies: " + elias.getHobbies());
        // -> Nur [Code, Sport], weil Konstruktor defensive Kopie macht!

        // Hacker-Versuch 2: Getter-Liste modifizieren
        try {
            elias.getHobbies().add("Versuch2");
        } catch (UnsupportedOperationException e) {
            System.out.println("Versuch 2 geblockt: " + e.getClass().getSimpleName());
        }

        // Wither: Altes Objekt bleibt gleich, neues hat anderes Alter
        _02_Immutable eliasNeu = elias.withAlter(23);
        System.out.println("Alt: " + elias + ", Neu: " + eliasNeu);
    }
}
