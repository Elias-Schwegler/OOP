package sw06;

/**
 * Kreis als konkrete Spezialisierung von Shape.
 * Aufgabe 1.j): getDiameter() fuer Cast-Demonstration.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public final class Circle extends Shape {

    private int radius;

    public Circle(final int x, final int y, final int radius) {
        super(x, y);
        this.radius = radius;
    }

    public final int getRadius() { return this.radius; }

    /**
     * Liefert den Durchmesser des Kreises.
     * Diese Methode ist nur ueber den statischen Typ Circle zugaenglich,
     * nicht ueber Shape (Aufgabe 1.j).
     *
     * @return Durchmesser.
     */
    public int getDiameter() {
        return 2 * this.radius;
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
        return "Circle [r=" + this.radius + ", d=" + getDiameter() + "] " + super.toString()
                + " | Umfang=" + getPerimeter() + ", Fläche=" + getArea();
    }
}
