package pruefungsvorbereitung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * TEMPLATE 04 – Comparable vs. Comparator.
 *
 * Comparable<T>:
 *   - Eine "natuerliche Ordnung" fuer die Klasse
 *   - Implementiert IN der Klasse selbst
 *   - Methode: compareTo(T other)
 *   - Nur EINE Ordnung moeglich
 *
 * Comparator<T>:
 *   - Alternative Ordnung, EXTERN definiert
 *   - Als eigene Klasse, anonyme Klasse oder Lambda
 *   - Methode: compare(T a, T b)
 *   - Beliebig viele Ordnungen moeglich
 *
 * Rueckgabewert von compareTo / compare:
 *   < 0  : this kommt vor other
 *   0    : gleich (bezueglich Sortierung)
 *   > 0  : this kommt nach other
 *
 * WICHTIG: NIE subtrahieren (return a - b;) -> Integer-Overflow!
 *          Stattdessen: Integer.compare(a, b)
 */
public final class _04_Comparable_Comparator
        implements Comparable<_04_Comparable_Comparator> {

    private final String name;
    private final int alter;
    private final double note;

    public _04_Comparable_Comparator(final String name, final int alter, final double note) {
        this.name = name;
        this.alter = alter;
        this.note = note;
    }

    public String getName() { return name; }
    public int getAlter() { return alter; }
    public double getNote() { return note; }

    /**
     * Natuerliche Ordnung: nach Name, dann nach Alter.
     */
    @Override
    public int compareTo(final _04_Comparable_Comparator other) {
        int result = this.name.compareTo(other.name);
        if (result != 0) {
            return result;
        }
        // Bei gleichem Namen: nach Alter
        return Integer.compare(this.alter, other.alter);
    }

    @Override
    public String toString() {
        return String.format("%s(%d, %.1f)", name, alter, note);
    }

    // ======================================================
    // DEMO: 4 verschiedene Sortierungen mit Comparator
    // ======================================================

    public static void main(String[] args) {
        List<_04_Comparable_Comparator> liste = new ArrayList<>();
        liste.add(new _04_Comparable_Comparator("Charlie", 25, 5.2));
        liste.add(new _04_Comparable_Comparator("Alice", 30, 4.8));
        liste.add(new _04_Comparable_Comparator("Bob", 22, 5.5));
        liste.add(new _04_Comparable_Comparator("Alice", 28, 5.0));  // gleicher Name

        // 1. Natuerliche Ordnung (Comparable: Name + Alter)
        Collections.sort(liste);
        System.out.println("Natuerlich:    " + liste);

        // 2. Comparator als eigene Klasse
        Collections.sort(liste, new NachNoteAbsteigend());
        System.out.println("Note abstg.:   " + liste);

        // 3. Comparator als Lambda (modern, ab Java 8)
        Collections.sort(liste, (a, b) -> Integer.compare(a.alter, b.alter));
        System.out.println("Alter aufstg.: " + liste);

        // 4. Comparator mit Methoden-Referenz (noch kuerzer)
        liste.sort(Comparator.comparingDouble(_04_Comparable_Comparator::getNote));
        System.out.println("Note aufstg.:  " + liste);

        // 5. Mehrstufig sortieren (thenComparing)
        liste.sort(Comparator
                .comparing(_04_Comparable_Comparator::getName)
                .thenComparingInt(_04_Comparable_Comparator::getAlter));
        System.out.println("Name+Alter:    " + liste);
    }

    /**
     * Beispiel fuer eigenen Comparator als separate Klasse.
     */
    private static final class NachNoteAbsteigend
            implements Comparator<_04_Comparable_Comparator> {
        @Override
        public int compare(final _04_Comparable_Comparator a,
                           final _04_Comparable_Comparator b) {
            return Double.compare(b.note, a.note);  // Achtung: b vor a -> absteigend
        }
    }
}
