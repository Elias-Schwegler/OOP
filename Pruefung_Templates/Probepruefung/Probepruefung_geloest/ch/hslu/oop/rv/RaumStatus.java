package ch.hslu.oop.rv;

/**
 * Status, in dem sich ein {@link Raum} befinden kann.
 *
 * <p>WHY Enum? Es gibt nur eine feste, abgeschlossene Menge gueltiger Zustaende.
 * Ein Enum macht diese Menge typsicher (man kann keinen ungueltigen Status
 * setzen) und ist viel klarer als z.B. int-Konstanten oder Strings.</p>
 *
 * <p>WHY in eigener Datei? Konvention: jede public top-level Klasse/Enum
 * gehoert in eine eigene Datei mit gleichem Namen.</p>
 */
public enum RaumStatus {

    /** Raum ist frei und kann reserviert werden. */
    FREI,

    /** Raum ist aktuell belegt (reserviert). */
    BELEGT,

    /** Raum ist gesperrt (z.B. Wartung) und darf nicht reserviert werden. */
    GESPERRT
}
