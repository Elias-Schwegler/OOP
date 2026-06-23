package sw05;

/**
 * Übung SW05, Aufgabe 1.a) & 1.b)
 * Kreis als konkrete Spezialisierung von Shape.
 * final: kann NICHT weiter vererbt werden.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public final class Circle extends Shape {

    private int radius;

    /**
     * Erstellt einen Kreis an der gegebenen Position.
     *
     * @param x      X-Koordinate des Mittelpunkts.
     * @param y      Y-Koordinate des Mittelpunkts.
     * @param radius Radius des Kreises.
     */
    public Circle(final int x, final int y, final int radius) {
        super(x, y);
        this.radius = radius;
    }

    /**
     * Liefert den Radius.
     * @return Radius des Kreises.
     */
    public final int getRadius() {
        return this.radius;
    }

    @Override
    public int getPerimeter() {
        return (int) (2 * Math.PI * this.radius);
    }

    @Override
    public int getArea() {
        return (int) (Math.PI * this.radius * this.radius);
    }

    @Override
    public String toString() {
        return "Circle [r=" + this.radius + "] " + super.toString()
                + " | Umfang=" + getPerimeter() + ", Fläche=" + getArea();
    }
}
