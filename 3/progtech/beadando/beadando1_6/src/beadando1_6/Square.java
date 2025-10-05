/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beadando1_6;

/**
 *
 * @author matte
 */
public class Square extends Shape {

    /**
     * Konstruktor
     *
     * @param center
     * @param length
     */
    public Square(Point center, double length) {
        super(center, length);
    }

    /**
     * Metódus, amit eldönti, hogy az adott pont benne van-e a négyzetben.
     *
     * @param p
     * @return benne van a pont?
     */
    @Override
    public boolean contains(Point p) {
        double halfOfLength = getLength() / 2;

        return p.getX() >= center.getX() - halfOfLength
                && p.getX() <= center.getX() + halfOfLength
                && p.getY() >= center.getY() - halfOfLength
                && p.getY() <= center.getY() + halfOfLength;
    }

    /**
     * Metódus, ami kiszámolja a négyzet sugarát.
     */
    @Override
    public void calculateRadius() {
        this.radius = length / Math.sqrt(2);
    }
}
