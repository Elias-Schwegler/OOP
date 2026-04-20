package pruefungsvorbereitung;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * TEMPLATE 03 – equals() und hashCode() korrekt implementieren.
 *
 * DIE GOLDENE REGEL:
 *   Wer equals() ueberschreibt, MUSS auch hashCode() ueberschreiben!
 *
 * Contract von equals():
 *   - Reflexiv:    x.equals(x) == true
 *   - Symmetrisch: x.equals(y) == y.equals(x)
 *   - Transitiv:   x.equals(y) && y.equals(z) -> x.equals(z)
 *   - Konsistent:  x.equals(y) liefert bei wiederholtem Aufruf dasselbe
 *   - Null-sicher: x.equals(null) == false
 *
 * Contract von hashCode():
 *   - equals() gleich -> hashCode() MUSS gleich sein
 *   - hashCode() gleich -> equals() MUSS NICHT gleich sein (Kollisionen erlaubt)
 */
public final class _03_EqualsHashCode {

    private final String name;
    private final int matrikelNr;

    public _03_EqualsHashCode(final String name, final int matrikelNr) {
        this.name = name;
        this.matrikelNr = matrikelNr;
    }

    public String getName() { return name; }
    public int getMatrikelNr() { return matrikelNr; }

    // ======================================================
    // equals() – Standard-Schema in 3 Schritten
    // ======================================================

    @Override
    public boolean equals(final Object obj) {
        // Schritt 1: Referenz-Vergleich (Performance + Reflexivitaet)
        if (this == obj) {
            return true;
        }

        // Schritt 2: Null-Check + Typ-Check
        // getClass() statt instanceof -> stabiler bei Vererbung
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        // Schritt 3: Cast + Feldvergleich
        _03_EqualsHashCode other = (_03_EqualsHashCode) obj;
        return this.matrikelNr == other.matrikelNr
            && Objects.equals(this.name, other.name);  // Objects.equals schuetzt vor null
    }

    // ======================================================
    // hashCode() – IMMER mit equals() zusammen implementieren!
    // ======================================================

    @Override
    public int hashCode() {
        // Einfachste Variante seit Java 7:
        return Objects.hash(name, matrikelNr);

        // Manuell (Effective Java):
        // int result = 17;
        // result = 31 * result + matrikelNr;
        // result = 31 * result + (name != null ? name.hashCode() : 0);
        // return result;
    }

    @Override
    public String toString() {
        return name + "(" + matrikelNr + ")";
    }

    // ======================================================
    // DEMO: Was passiert ohne korrektes hashCode()?
    // ======================================================

    public static void main(String[] args) {
        _03_EqualsHashCode s1 = new _03_EqualsHashCode("Elias", 12345);
        _03_EqualsHashCode s2 = new _03_EqualsHashCode("Elias", 12345);
        _03_EqualsHashCode s3 = new _03_EqualsHashCode("Alice", 12345);

        // Referenzvergleich
        System.out.println("s1 == s2:            " + (s1 == s2));            // false
        System.out.println("s1.equals(s2):       " + s1.equals(s2));         // true
        System.out.println("s1.equals(s3):       " + s1.equals(s3));         // false

        // hashCode muss konsistent sein
        System.out.println("s1.hashCode():       " + s1.hashCode());
        System.out.println("s2.hashCode():       " + s2.hashCode());
        System.out.println("Gleich? " + (s1.hashCode() == s2.hashCode()));  // true

        // HashSet braucht BEIDES: equals + hashCode
        Set<_03_EqualsHashCode> set = new HashSet<>();
        set.add(s1);
        System.out.println("Set enthaelt s2? " + set.contains(s2));  // true!
        // -> Wenn wir nur equals() und kein hashCode() haetten: FALSE!
    }
}
