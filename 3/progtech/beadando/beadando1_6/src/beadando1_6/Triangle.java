/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beadando1_6;

/**
 *
 * @author matte
 */
public class Triangle extends Shape {

    public Triangle(Point center, double length) {
        super(center, length, "Triangle");
    }
    
    @Override
    public boolean contains(Point p) {
        return false;
    }
    
    @Override
    public void calculateRadius() {
        this.radius = length / Math.sqrt(3);
    }
    
}
