/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgame;

/**
 *
 * @author matte
 */
public class Shielded extends Ork {
    public Shielded(String name, int hp, int atk) {
        super(name, hp, atk);
    }
    
    @Override
    public void applyDamage(Character c) {
        int newHp = this.getHp() - (int)(c.getAtk() / 2);
        this.setHp(newHp);
    }
    
}
