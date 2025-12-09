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
public class GuardHorizontal extends Guard {

    public GuardHorizontal(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }

    /**
     * Mozgás vízszintesen
     */
    public void move() {
        x += getVel();
        if (x + width >= 600 || x <= 0) {
            invertVel();
        }
    }

}
