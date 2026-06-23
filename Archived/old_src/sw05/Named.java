package sw05;

/**
 * Übung SW05, Aufgabe 1.h)
 * Interface für Objekte, die einen Namen haben können.
 *
 * Design-Diskussion (Aufgabe 1.h):
 * Verschiedene Möglichkeiten, Named in die Switchable-Hierarchie einzubauen:
 *
 * 1) Motor implements CountingSwitchable, Named → Motor hat beides direkt
 * 2) CountingSwitchable extends Switchable, Named → Jedes CountingSwitchable hat einen Namen
 * 3) Neues Interface: NamedCountingSwitchable extends CountingSwitchable, Named
 *
 * Empfehlung: Variante 1 (pro Klasse entscheiden) ist am flexibelsten,
 * da nicht jedes schaltbare Objekt zwingend einen Namen braucht.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public interface Named {

    /**
     * Setzt den Namen des Objekts.
     * @param name Der Name.
     */
    void setName(final String name);

    /**
     * Liefert den Namen des Objekts.
     * @return Der Name.
     */
    String getName();
}
