package sw05;

/**
 * Übung SW05, Aufgabe 1.a)
 * Abstrakte Basisklasse für geometrische Formen.
 * Definiert Position (x, y) und erzwingt Berechnung von Umfang und Fläche.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public abstract class Shape {

    private int x;
    private int y;

    /**
     * Konstruktor – protected, damit nur Subklassen ihn aufrufen können.
     *
     * @param x X-Koordinate der Form.
     * @param y Y-Koordinate der Form.
     */
    protected Shape(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Verschiebt die Form an eine neue Position.
     * final: kann von Subklassen NICHT überschrieben werden.
     *
     * @param newX Neue X-Koordinate.
     * @param newY Neue Y-Koordinate.
     */
    public final void move(final int newX, final int newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Liefert die X-Koordinate.
     * @return X-Koordinate.
     */
    public final int getX() {
        return this.x;
    }

    /**
     * Liefert die Y-Koordinate.
     * @return Y-Koordinate.
     */
    public final int getY() {
        return this.y;
    }

    /**
     * Berechnet den Umfang der Form.
     * Abstrakt: MUSS von jeder Subklasse implementiert werden.
     *
     * @return Umfang der Form.
     */
    public abstract int getPerimeter();

    /**
     * Berechnet die Fläche der Form.
     * Abstrakt: MUSS von jeder Subklasse implementiert werden.
     *
     * @return Fläche der Form.
     */
    public abstract int getArea();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " an Position (" + this.x + ", " + this.y + ")";
    }
}
