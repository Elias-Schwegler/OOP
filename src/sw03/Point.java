package sw03;

/**
 * Übung SW03, Aufgabe 1.3 g)
 * Repräsentiert einen einfachen, zweidimensionalen Punkt.
 */
public class Point {
    
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Übung SW03, Aufgabe 1.3 h) & i)
     * Liefert den Quadranten (1 bis 4), in dem sich der Punkt befindet.
     * Wenn der Punkt auf einer Achse oder dem Ursprung liegt, wird 0 zurückgegeben (Lösung zu Aufgabe i).
     */
    public int getQuadrant() {
        if (this.x > 0 && this.y > 0) {
            return 1; // Oben rechts
        } else if (this.x < 0 && this.y > 0) {
            return 2; // Oben links
        } else if (this.x < 0 && this.y < 0) {
            return 3; // Unten links
        } else if (this.x > 0 && this.y < 0) {
            return 4; // Unten rechts
        } else {
            // Punkt liegt auf einer Achse (x=0 oder y=0) oder genau auf dem Ursprung (0,0)
            return 0; 
        }
    }

    // Standard Getter/Setter (für SW04 nützlich)
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    
    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}
