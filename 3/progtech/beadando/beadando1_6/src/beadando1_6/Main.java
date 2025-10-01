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
        CoordinateSystem cs = new CoordinateSystem();
        
        try {
            cs.read("input1.txt");
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(-1);
        }
        
        cs.print();
    }
}
