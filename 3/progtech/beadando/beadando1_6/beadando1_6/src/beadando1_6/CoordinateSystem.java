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

    /**
     * Konstruktor
     */
    public CoordinateSystem() {
        shapes = new ArrayList<>();
    }

    /**
     * Megadott input fájlból való beolvasás, síkidomok példányosítása és
     * sugaruk kiszámolása, majd hozzáadása a shapes ArrayListhez.
     *
     * @param filename
     * @throws FileNotFoundException
     */
    public void read(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numShapes = sc.nextInt();

        for (int i = 1; i <= numShapes; i++) {
            Shape shape = null;
            switch (sc.next()) {
                case "circle" ->
                    shape = new Circle(new Point(sc.nextDouble(), sc.nextDouble()), sc.nextDouble());
                case "triangle" ->
                    shape = new Triangle(new Point(sc.nextDouble(), sc.nextDouble()), sc.nextDouble());
                case "square" ->
                    shape = new Square(new Point(sc.nextDouble(), sc.nextDouble()), sc.nextDouble());
                case "hexagon" ->
                    shape = new Hexagon(new Point(sc.nextDouble(), sc.nextDouble()), sc.nextDouble());
            }
            shape.calculateRadius();
            shapes.add(shape);
        }
    }

    /**
     * A síkidomok contains() metódusainak meghívása és a pontot tartalmazó
     * síkidomok megszámlálása.
     *
     * @param p
     * @return hány síkidom tartalmazza a pontot
     */
    public int getResults(Point p) {
        int count = 0;
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).contains(p)) {
                count++;
            }
        }

        return count;
    }
}
