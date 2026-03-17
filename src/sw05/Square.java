package sw05;

/**
 * Übung SW05, Aufgabe 1.c) & 1.d)
 * Quadrat als Spezialisierung von Rectangle.
 *
 * Design-Diskussion (Aufgabe 1.c):
 * - Variante A: Square extends Rectangle (width = height) ← HIER GEWÄHLT
 *   Vorteil:  Wiederverwendung von Perimeter/Area, wenig Code
 *   Nachteil: Ist-ein-Test umstritten (ist ein Quadrat wirklich ein Rechteck?)
 *             → Quadrat könnte width ≠ height verbieten müssen
 *
 * - Variante B: Square extends Shape (eigene Seitenlänge)
 *   Vorteil:  Sauberer Is-a-Test, keine geerbten Width/Height
 *   Nachteil: Code-Duplizierung mit Rectangle
 *
 * - Variante C: Rectangle extends Square (Quadrat ist allgemeiner?)
 *   Argument: Quadrat hat 1 Attribut, Rechteck 2 → Rechteck ist Spezialisierung
 *   Nachteil: Mathematisch fragwürdig
 *
 * @author Elias Schwegler
 * @version 1.0
 */
public final class Square extends Rectangle {

    /**
     * Erstellt ein Quadrat an der gegebenen Position.
     * Übergibt Seitenlänge als width UND height an Rectangle.
     *
     * @param x    X-Koordinate.
     * @param y    Y-Koordinate.
     * @param side Seitenlänge des Quadrats.
     */
    public Square(final int x, final int y, final int side) {
        super(x, y, side, side); // Quadrat = Rechteck mit gleicher Breite und Höhe
    }

    /**
     * Liefert die Seitenlänge.
     * @return Seitenlänge des Quadrats.
     */
    public int getSide() {
        return getWidth(); // width == height bei einem Quadrat
    }

    @Override
    public String toString() {
        return "Square [s=" + getSide() + "] an Position (" + getX() + ", " + getY() + ")"
                + " | Umfang=" + getPerimeter() + ", Fläche=" + getArea();
    }
}
