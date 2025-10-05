/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beadando1_6;

import java.io.FileNotFoundException;

/**
 *
 * @author matte
 */
public class Main {

    public static void main(String[] args) {
        Point[] points = {
            new Point(0, 0),
            new Point(0, 1),
            new Point(0, 6),
            new Point(-10, 7),
            new Point(6, 4),
            new Point(0, -20),
            new Point(7, -8),
            new Point(20, 20),
            new Point(0, 3),
            new Point(0, 10)
        };

        for (int i = 0; i < 10; i++) {
            CoordinateSystem cs = new CoordinateSystem();
            String filename = "input" + i + ".txt";
            Point currentPoint = points[i];

            try {
                cs.read(filename);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                System.exit(-1);
            }

            System.out.println(filename + ": A (" + currentPoint.getX() + "," + currentPoint.getY() + ") pontot " + cs.getResults(currentPoint) + " sikidom tartalmazza.");
        }
    }
}
