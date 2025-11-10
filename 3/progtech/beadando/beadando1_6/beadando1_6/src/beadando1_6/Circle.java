/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beadando1_6;

/**
 *
 * @author matte
 */
public class Circle extends Shape {

    /**
     * Konstruktor
     *
     * @param center
     * @param length
     */
    public Circle(Point center, double length) {
        super(center, length);
    }

    /**
     * Metódus, amit eldönti, hogy az adott pont benne van-e a körben.
     *
     * @param p
     * @return benne van a pont?
     */
    @Override
    public boolean contains(Point p) {
        double dx = p.getX() - center.getX();
        double dy = p.getY() - center.getY();

        return Math.pow(dx, 2) + Math.pow(dy, 2) <= Math.pow(getRadius(), 2);
    }

    /**
     * Metódus, ami kiszámolja a kör sugarát.
     */
    @Override
    public void calculateRadius() {
        this.radius = length / 2;
    }
}
