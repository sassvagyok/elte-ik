/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beadando1_6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matte
 */
public class CoordinateSystem {
    private final ArrayList<Shape> shapes;

    public CoordinateSystem() {
        shapes = new ArrayList<>();
    }
    
    /**
     * 
     * @param filename
     * @throws FileNotFoundException 
     */
    public void read(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numShapes= sc.nextInt();
        for (int i = 1; i <= numShapes; i++) {
            Shape shape = null;
            switch (sc.next()) {
                case "circle" -> shape = new Circle(new Point(sc.nextInt(), sc.nextInt()), sc.nextDouble());
                case "triangle" -> shape = new Triangle(new Point(sc.nextInt(), sc.nextInt()), sc.nextDouble());
                case "square" -> shape = new Square(new Point(sc.nextInt(), sc.nextInt()), sc.nextDouble());
                case "hexagon" -> shape = new Hexagon(new Point(sc.nextInt(), sc.nextInt()), sc.nextDouble());
            }
            shape.calculateRadius();
            shapes.add(shape);
        }
    }
    
    /**
     * 
     */
    public void print() {
        for (Shape s : shapes) {
            System.out.println(s.type + " (" + s.center.getX() + ";" + s.center.getY() + ") " + s.length + " " + s.radius);
        }
    }
}
