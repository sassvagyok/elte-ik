/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beadando1_6;

/**
 *
 * @author matte
 */
public abstract class Shape {

    protected Point center;
    protected double length, radius;

    /**
     * Konstruktor
     *
     * @param center
     * @param length
     */
    public Shape(Point center, double length) {
        this.center = center;
        this.length = length;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Absztrakt metódus, amit eldönti, hogy az adott pont benne van-e a
     * síkidomban.
     *
     * @param p
     * @return benne van a pont?
     */
    public abstract boolean contains(Point p);

    /**
     * Absztrakt metódus, ami kiszámolja síkidom sugarát.
     */
    public abstract void calculateRadius();
}
