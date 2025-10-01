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

    public Circle(Point center, double length) {
        super(center, length, "Circle");
    }

    @Override
    public boolean contains(Point p) {
        double dx = p.getX() - center.getX();
        double dy = p.getY() - center.getY();
        
        return dx * dx + dy * dy <= getRadius() * getRadius();
    }

    @Override
    public void calculateRadius() {
        this.radius = length / 2;
    }
    
}
