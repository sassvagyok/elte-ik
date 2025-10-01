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

    public Square(Point center, double length) {
        super(center, length, "Square");
    }

    @Override
    public boolean contains(Point p) {
        double half = getLength() / 2;
        
        return p.getX() >= center.getX() - half &&
                p.getX() <= center.getX() + half &&
                p.getY() >= center.getY() - half &&
                p.getY() <= center.getY() + half;
    }
    
    @Override
    public void calculateRadius() {
        this.radius = length / Math.sqrt(2);
    }
    
}
