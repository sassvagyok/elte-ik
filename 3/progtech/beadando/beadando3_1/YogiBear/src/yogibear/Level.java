/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author matep
 */
public class Level {

    private final int SPRITE_WIDTH = 30;
    private final int SPRITE_HEIGHT = 30;
    ArrayList<Tree> trees;
    ArrayList<Basket> baskets;
    ArrayList<Guard> guards;

    public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }

    /**
     * Pálya betöltése
     * @param levelPath
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        trees = new ArrayList<>();
        baskets = new ArrayList<>();
        guards = new ArrayList<>();
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (char type : line.toCharArray()) {
                if (Character.isDigit(type)) {
                    Image image = new ImageIcon("assets/sprites/" + type + ".png").getImage();
                    
                    if (type == '0') {
                        baskets.add(new Basket(x * SPRITE_WIDTH, y *SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, image));
                    }
                    
                    if (type == '1') {
                        trees.add(new Tree(x * SPRITE_WIDTH, y *SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, image));
                    }
                    
                    if (type == '2') {
                        guards.add(new GuardVertical(x * SPRITE_WIDTH, y *SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, image));
                    }
                    
                    if (type == '3') {
                        guards.add(new GuardHorizontal(x * SPRITE_WIDTH, y *SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, image));
                    }
                }
                x++;
            }
            y++;
        }
    }

    /**
     * Ütközés őrrel
     * @param yogi
     * @return true, ha ütközött, false ha nem
     */
    public boolean collidesWithGuard(Yogi yogi) {
        Guard collidedWith = null;
        for (Guard guard : guards) {
            if (yogi.collides(guard)) {
                collidedWith = guard;
                break;
            }
        }
        if (collidedWith != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Ütközés fával
     * @param yogi
     * @return true, ha ütközött, false ha nem
     */
    public boolean collidesWithTree(Yogi yogi) {
        for (Tree tree : trees) {
            if (yogi.collides(tree)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Kosár felvétele
     * @param yogi
     * @return 1 ha felvette, 0 ha nem
     */
    public int picksUp(Yogi yogi) {
        Basket pickedUpBasket = null;
        for (Basket basket : baskets) {
            if (yogi.collides(basket)) {
                pickedUpBasket = basket;
                break;
            }
        }
        if (pickedUpBasket != null) {
            baskets.remove(pickedUpBasket);
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * Őrök mozgatása
     */
    public void moveGuards() {
        for (Guard guard : guards) {
            int oldX = guard.getX();
            int oldY = guard.getY();

            guard.move();

            for (Tree tree : trees) {
                if (guard.collides(tree)) {
                    guard.setX(oldX);
                    guard.setY(oldY);
                    guard.invertVel();
                    break;
                }
            }
        }
    }
    
    public boolean isOver() {
        return baskets.isEmpty();
    }

    public void draw(Graphics g) {
        for (Basket basket : baskets) {
            basket.draw(g);
        }
        
        for (Tree tree : trees) {
            tree.draw(g);
        }
        
        for (Guard guard : guards) {
            guard.draw(g);
        }
    }

}
