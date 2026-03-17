package sw05;

/**
 * Übung SW05, Aufgabe 1.a) & 1.b)
 * Rechteck als konkrete Spezialisierung von Shape.
 * final: kann NICHT weiter vererbt werden.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public class Rectangle extends Shape {

    private int width;
    private int height;

    /**
     * Erstellt ein Rechteck an der gegebenen Position.
     *
     * @param x      X-Koordinate.
     * @param y      Y-Koordinate.
     * @param width  Breite des Rechtecks.
     * @param height Höhe des Rechtecks.
     */
    public Rectangle(final int x, final int y, final int width, final int height) {
        super(x, y); // Superklassen-Konstruktor aufrufen
        this.width = width;
        this.height = height;
    }

    /**
     * Liefert die Breite.
     * @return Breite des Rechtecks.
     */
    public final int getWidth() {
        return this.width;
    }

    /**
     * Liefert die Höhe.
     * @return Höhe des Rechtecks.
     */
    public final int getHeight() {
        return this.height;
    }

    @Override
    public int getPerimeter() {
        return (2 * this.width) + (2 * this.height);
    }

    @Override
    public int getArea() {
        return this.width * this.height;
    }

    @Override
    public String toString() {
        return "Rectangle [" + this.width + "x" + this.height + "] " + super.toString()
                + " | Umfang=" + getPerimeter() + ", Fläche=" + getArea();
    }
}
