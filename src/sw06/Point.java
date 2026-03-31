package sw06;

/**
 * Übung SW06, Aufgabe 1.a) bis 1.d)
 * Kopie aus SW03, erweitert um moveRelative-Überladungen und Copy-Constructor.
 * Demonstriert Überladen (Overloading) von Methoden und Konstruktoren.
 *
 * @author Elias Schwegler
 * @version 2.0
 */
public class Point {

    private int x;
    private int y;

    /**
     * Erstellt einen Punkt an den gegebenen Koordinaten.
     *
     * @param x X-Koordinate.
     * @param y Y-Koordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Aufgabe 1.d) Copy-Constructor.
     * Erstellt eine Kopie eines bestehenden Punktes.
     * Delegiert an den Haupt-Konstruktor, um Coderedundanz zu vermeiden.
     *
     * @param point Der zu kopierende Punkt.
     */
    public Point(Point point) {
        this(point.x, point.y);
    }

    /**
     * Aufgabe 1.a) moveRelative mit x- und y-Abschnitten.
     * Verschiebt den Punkt relativ zur aktuellen Position.
     *
     * @param dx Verschiebung in X-Richtung.
     * @param dy Verschiebung in Y-Richtung.
     */
    public void moveRelative(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Aufgabe 1.b) moveRelative mit einem Point als Vektor (Überladung).
     * Ein Point kann auch als Vektor interpretiert werden.
     *
     * @param vector Der Verschiebungsvektor als Point.
     */
    public void moveRelative(Point vector) {
        moveRelative(vector.x, vector.y);
    }

    /**
     * Aufgabe 1.c) moveRelative mit Polarkoordinaten (Überladung).
     * Verschiebt den Punkt anhand eines Winkels und eines Betrags.
     *
     * Diskussion: Diese Überladung ist technisch moeglich, aber NICHT ideal,
     * weil die Signatur moveRelative(double, int) leicht mit
     * moveRelative(int, int) verwechselt werden kann. Bessere Alternative:
     * Eine eigene Methode mit sprechendem Namen, z.B. moveRelativePolar().
     *
     * @param angleRadians Winkel in Radiant.
     * @param magnitude    Betrag (Laenge) des Vektors.
     */
    public void moveRelative(double angleRadians, int magnitude) {
        this.x += (int) (Math.cos(angleRadians) * magnitude);
        this.y += (int) (Math.sin(angleRadians) * magnitude);
    }

    /**
     * Liefert den Quadranten (1 bis 4), in dem sich der Punkt befindet.
     * Liegt der Punkt auf einer Achse oder im Ursprung, wird 0 zurueckgegeben.
     *
     * @return Quadrant (1-4) oder 0.
     */
    public int getQuadrant() {
        if (this.x > 0 && this.y > 0) {
            return 1;
        } else if (this.x < 0 && this.y > 0) {
            return 2;
        } else if (this.x < 0 && this.y < 0) {
            return 3;
        } else if (this.x > 0 && this.y < 0) {
            return 4;
        } else {
            return 0;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}
