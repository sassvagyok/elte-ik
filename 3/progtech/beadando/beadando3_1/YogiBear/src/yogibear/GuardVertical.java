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
public class GuardVertical extends Guard {

    public GuardVertical(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }

    /**
     * Mozgás függőlegesen
     */
    public void move() {
        y += getVel();
        if (y + height >= 600 || y <= 0) {
            invertVel();
        }
    }

}
