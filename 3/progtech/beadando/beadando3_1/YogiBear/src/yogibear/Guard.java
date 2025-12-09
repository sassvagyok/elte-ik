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
abstract class Guard extends Sprite {

    private double vel;

    public Guard(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        vel = 1;
    }

    abstract void move();

    public void invertVel() {
        vel = -vel;
    }

    public double getVel() {
        return vel;
    }

    public void setVel(double vel) {
        this.vel = vel;
    }
    
}
