package sw06;

/**
 * Kopie aus SW05 fuer Aufgabe 1.h)-1.k).
 * Abstrakte Basisklasse fuer geometrische Formen.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public abstract class Shape {

    private int x;
    private int y;

    protected Shape(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public final void move(final int newX, final int newY) {
        this.x = newX;
        this.y = newY;
    }

    public final int getX() { return this.x; }
    public final int getY() { return this.y; }

    public abstract int getPerimeter();
    public abstract int getArea();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " an Position (" + this.x + ", " + this.y + ")";
    }
}
