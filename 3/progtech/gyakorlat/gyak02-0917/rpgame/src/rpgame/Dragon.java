/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgame;

/**
 *
 * @author matte
 */
public class Dragon extends Character {
    private int atk_treshold;
    
    public Dragon(String name, int hp, int atk, int atk_treshold) {
        super(name, hp, atk);
        this.atk_treshold = atk_treshold;
    }

    public int getAtk_treshold() {
        return atk_treshold;
    }

    public void setAtk_treshold(int atk_treshold) {
        this.atk_treshold = atk_treshold;
    }
    
    @Override
    public void applyDamage(Character c) {
        if (c.getAtk() < this.getAtk_treshold()) return;
        
        int newHp = this.getHp() - c.getAtk();
        this.setHp(newHp);
    }
    
}
