package sw04;

import sw03.Point;

/**
 * Übung SW04, Aufgabe 3
 * Repräsentiert eine zweidimensionale Linie basierend auf zwei Punkten.
 * Demonstriert Datenkapselung (Attribute private) und Information Hiding.
 */
public class Line {

    // Die interne Darstellung verwendet Point-Objekte
    // "So verschlossen wie möglich": private Modifier
    private Point start;
    private Point end;

    /**
     * Übung SW04, Aufgabe 3.a) & 3.b) Konstruktor
     * Erstellt eine Linie mit Start- und End-Koordinaten.
     * 
     * @param x1 X-Koordinate Start
     * @param y1 Y-Koordinate Start
     * @param x2 X-Koordinate Ende
     * @param y2 Y-Koordinate Ende
     */
    public Line(int x1, int y1, int x2, int y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Übung SW04, Aufgabe 3.c), 3.f), 3.g) & 3.h)
     * Liefert den Startpunkt der Linie.
     * 
     * WICHTIG (Defensive Kopie - Lösung zu 3.f/3.h): Wir geben ein NEUES Point-Objekt zurück, 
     * nicht das interne. Ansonsten könnte der Aufrufer das interne Objekt 
     * verändern (Kapselung wäre durchbrochen!).
     * 
     * @return Ein neues Point-Objekt mit den Koordinaten des Startpunkts.
     */
    public Point getStartPoint() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Liefert den Endpunkt der Linie.
     * (Ebenfalls als Defensive Kopie)
     * 
     * @return Ein neues Point-Objekt mit den Koordinaten des Endpunkts.
     */
    public Point getEndPoint() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Übung SW04, Aufgabe 3.d) & 3.e) (Information Hiding)
     * Verändert den Endpunkt der Linie auf eine neue Position.
     * 
     * @param newEnd Der neue Endpunkt.
     */
    public void setEndPoint(Point newEnd) {
        // Hier ebenfalls defensive Kopie bei der Eingabe, damit Veränderungen
        // am originalen newEnd-Objekt ausserhalb der Klasse sich nicht auf
        // die Line-Klasse auswirken.
        this.end = new Point(newEnd.getX(), newEnd.getY());
    }
    
    @Override
    public String toString() {
        return "Line von " + start + " zu " + end;
    }
}
