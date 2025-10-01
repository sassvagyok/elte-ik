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
    protected String type;

    /**
     * 
     * @param center
     * @param length
     * @param type 
     */
    public Shape(Point center, double length, String type) {
        this.center = center;
        this.length = length;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public abstract boolean contains(Point p);
    
    public abstract void calculateRadius();
}
