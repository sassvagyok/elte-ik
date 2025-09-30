/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beadando1_6;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author matte
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Shape> shapes = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt")))
        {
            String line = br.readLine();

            while (line != null) {
                if ("triangle".equals(line.split(",")[0])) {
                    shapes.add(new Triangle(new Point(Integer.parseInt(line.split(",")[1]), Integer.parseInt(line.split(",")[2])), Double.parseDouble(line.split(",")[3])));
                }
                if ("circle".equals(line.split(",")[0])) {
                    shapes.add(new Circle(new Point(Integer.parseInt(line.split(",")[1]), Integer.parseInt(line.split(",")[2])), Double.parseDouble(line.split(",")[3])));
                }
                if ("square".equals(line.split(",")[0])) {
                    shapes.add(new Square(new Point(Integer.parseInt(line.split(",")[1]), Integer.parseInt(line.split(",")[2])), Double.parseDouble(line.split(",")[3])));
                }
                if ("hexagon".equals(line.split(",")[0])) {
                    shapes.add(new Hexagon(new Point(Integer.parseInt(line.split(",")[1]), Integer.parseInt(line.split(",")[2])), Double.parseDouble(line.split(",")[3])));
                }
                
                System.out.println(line.split(",")[0]);
                line = br.readLine();
            }
        } catch(FileNotFoundException e) {
            System.err.println("File is not present");
            throw e;
        } catch(IOException e) {
            System.err.println("Bad file");
            throw e;
        } finally {
            System.out.println("File handling ended.");
        }
        
        System.out.println(shapes.get(0).radius);
    }
}
