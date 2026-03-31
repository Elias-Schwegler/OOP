package sw06;

/**
 * Rechteck als konkrete Spezialisierung von Shape.
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public class Rectangle extends Shape {

    private int width;
    private int height;

    public Rectangle(final int x, final int y, final int width, final int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public final int getWidth() { return this.width; }
    public final int getHeight() { return this.height; }

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
