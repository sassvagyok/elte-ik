/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgame;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author matte
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Character> characters = new ArrayList<>();
        
        characters.add(new MainCharacter("MC", 100, 10, 5));
        characters.add(new Berserker("B1", 50, 15));
        characters.add(new Shielded("S1", 50, 5));
        characters.add(new BlackDragon("BD1", 200, 20));
        characters.add(new RedDragon("RD1", 100, 30));
        
        while (characters.stream().filter(x -> x.isAlive()).count() == 1) {
            Random rand = new Random();
            int attacker = rand.nextInt(6) + 1;
            int defender;
            
            do {
                defender = rand.nextInt(6) + 1;
            } while (defender == attacker);
        }
    }
}
