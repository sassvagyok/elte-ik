/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Image;

/**
 *
 * @author matep
 */
public class Yogi extends Sprite {

    private double velx;
    private double vely;

    public Yogi(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        velx = 0;
        vely = 0;
    }

    /**
     * Mozgás vízszintesen
     */
    public void moveX() {
        x += velx;
        if (x < 0) {
            x = 0;
        }
        
        if (x + width > 600) {
            x = 600 - width;
        }
    }

    /**
     * Mozgás függőlegesen
     */
    public void moveY() {
        y += vely;
        if (y < 0) {
            y = 0;
        }
        
        if (y + height > 600) {
            y = 600 - height;
        }
    }

    public double getVelx() {
        return velx;
    }

    public void setVelx(double velx) {
        this.velx = velx;
    }

    public double getVely() {
        return vely;
    }

    public void setVely(double vely) {
        this.vely = vely;
    }

}
