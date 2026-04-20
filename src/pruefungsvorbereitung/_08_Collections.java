package pruefungsvorbereitung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * TEMPLATE 08 – Collections Framework.
 *
 * WAHL DER RICHTIGEN COLLECTION:
 *
 *  - List      : Geordnet, Duplikate erlaubt, Index-Zugriff
 *      -> ArrayList   : Schneller Index-Zugriff
 *      -> LinkedList  : Schneller Einfuegen/Loeschen am Anfang/Ende
 *
 *  - Set       : KEINE Duplikate (via equals/hashCode)
 *      -> HashSet     : Schnell, keine Reihenfolge
 *      -> TreeSet     : Sortiert (braucht Comparable oder Comparator)
 *
 *  - Map       : Schluessel-Wert-Paare, eindeutige Keys
 *      -> HashMap     : Schnell, keine Reihenfolge
 *      -> TreeMap     : Sortiert nach Keys
 *
 *  - Queue     : FIFO (Warteschlange)
 *      -> LinkedList  : Implementiert auch Queue
 *      -> ArrayDeque  : Schneller als LinkedList
 *
 * WICHTIG:
 *  - Interface-Typ als Variable: List<String> l = new ArrayList<>();
 *  - Sonst kannst du die Implementierung nicht einfach tauschen
 *  - Collections haben nur Objekte, keine primitiven Typen (Autoboxing hilft)
 *  - for-each darf die Collection NICHT modifizieren (ausser via Iterator.remove)
 */
public class _08_Collections {

    public static void main(String[] args) {
        demoList();
        demoSet();
        demoMap();
        demoIterator();
        demoSortMaxMin();
    }

    // ======================================================
    // LIST – geordnet, Duplikate erlaubt
    // ======================================================
    private static void demoList() {
        System.out.println("\n=== LIST ===");
        List<String> namen = new ArrayList<>();
        namen.add("Alice");
        namen.add("Bob");
        namen.add("Alice");  // Duplikat erlaubt!

        System.out.println("Liste: " + namen);
        System.out.println("Index 0: " + namen.get(0));
        System.out.println("Enthaelt 'Bob': " + namen.contains("Bob"));
        System.out.println("Size: " + namen.size());  // Methode, nicht Attribut!
    }

    // ======================================================
    // SET – keine Duplikate
    // ======================================================
    private static void demoSet() {
        System.out.println("\n=== SET ===");
        Set<String> farben = new HashSet<>();
        farben.add("Rot");
        boolean war_neu = farben.add("Rot");
        System.out.println("'Rot' war neu? " + war_neu);  // false -> war schon drin
        farben.add("Blau");

        System.out.println("Set: " + farben);
        System.out.println("Size: " + farben.size());  // 2

        // TreeSet: automatisch sortiert
        Set<String> sortiert = new TreeSet<>(farben);
        System.out.println("TreeSet sortiert: " + sortiert);
    }

    // ======================================================
    // MAP – Schluessel-Wert-Paare
    // ======================================================
    private static void demoMap() {
        System.out.println("\n=== MAP ===");
        Map<String, Integer> alter = new HashMap<>();
        alter.put("Alice", 25);
        alter.put("Bob", 30);
        alter.put("Alice", 26);  // Key-Kollision -> ueberschreibt Value

        System.out.println("Alice ist " + alter.get("Alice"));
        System.out.println("Enthaelt Bob? " + alter.containsKey("Bob"));

        // Iteration: entrySet() ist am effizientesten
        for (Map.Entry<String, Integer> entry : alter.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // TreeMap: sortiert nach Keys
        Map<String, Integer> sortiert = new TreeMap<>(alter);
        System.out.println("Sortiert: " + sortiert);
    }

    // ======================================================
    // ITERATOR – Elemente waehrend Iteration entfernen
    // ======================================================
    private static void demoIterator() {
        System.out.println("\n=== ITERATOR ===");
        List<String> namen = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));

        // FEHLER – for-each darf NICHT modifizieren (ConcurrentModificationException!)
        // for (String n : namen) { if (n.equals("Bob")) namen.remove(n); }

        // RICHTIG: explizit Iterator verwenden
        Iterator<String> it = namen.iterator();
        while (it.hasNext()) {
            String n = it.next();
            if (n.equals("Bob")) {
                it.remove();  // sicher!
            }
        }
        System.out.println("Nach remove: " + namen);

        // Noch kuerzer: removeIf (Java 8+)
        namen.removeIf(n -> n.startsWith("A"));
        System.out.println("Nach removeIf: " + namen);
    }

    // ======================================================
    // COLLECTIONS-UTILITIES: sort, max, min, frequency
    // ======================================================
    private static void demoSortMaxMin() {
        System.out.println("\n=== COLLECTIONS.x ===");
        List<Integer> zahlen = new ArrayList<>(List.of(5, 2, 8, 1, 9, 2, 5));

        Collections.sort(zahlen);
        System.out.println("Sortiert: " + zahlen);

        Collections.sort(zahlen, Collections.reverseOrder());
        System.out.println("Absteigend: " + zahlen);

        System.out.println("Max: " + Collections.max(zahlen));
        System.out.println("Min: " + Collections.min(zahlen));
        System.out.println("Haeufigkeit 5: " + Collections.frequency(zahlen, 5));
    }
}
