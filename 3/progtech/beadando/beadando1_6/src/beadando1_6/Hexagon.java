/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beadando1_6;

/**
 *
 * @author matte
 */
public class Hexagon extends Shape {

    /**
     * Konstruktor
     *
     * @param center
     * @param length
     */
    public Hexagon(Point center, double length) {
        super(center, length);
    }

    /**
     * Megadott pontok vektoriális szorzatának kiszámítása a contains()
     * metódushoz.
     *
     * @param a
     * @param b
     * @param c
     * @return pontok vektoriális szorzata
     */
    public double cross(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    /**
     * Metódus, amit eldönti, hogy az adott pont benne van-e a hatszögben.
     *
     * @param p
     * @return benne van a pont?
     */
    @Override
    public boolean contains(Point p) {
        Point[] vertices = new Point[6];

        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(90 + i * 60);

            vertices[i] = new Point(center.getX() + radius * Math.cos(angle), center.getY() + radius * Math.sin(angle));
        }

        for (int i = 0; i < 6; i++) {
            Point a = vertices[i];
            Point b = vertices[(i + 1) % 6];

            if (cross(a, b, p) < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Metódus, ami kiszámolja a hatszög sugarát.
     */
    @Override
    public void calculateRadius() {
        this.radius = length;
    }
}
