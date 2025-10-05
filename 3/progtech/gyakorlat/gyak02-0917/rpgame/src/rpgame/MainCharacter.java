/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgame;

/**
 *
 * @author matte
 */
public class MainCharacter extends Character {
    private double dfn;
    public MainCharacter(String name, int hp, int atk, double dfn) {
        super(name, hp, atk);
        this.dfn = dfn;
    }

    public double getDfn() {
        return dfn;
    }

    public void setDfn(double dfn) {
        this.dfn = dfn;
    }
    
    @Override
    public void applyDamage(Character c) {
        int newHp = this.getHp() - (int)(c.getAtk() / this.getDfn());
        this.setHp(newHp);
    }
}
