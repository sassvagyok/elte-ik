/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgame;

/**
 *
 * @author matte
 */
public class Character {
    private String name;
    private int hp;
    private int atk;

    public Character(String name, int hp, int atk) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }
    
    public void attack(Character c) {
        c.hp -= this.atk;
    }
    
    public void applyDamage(Character c) {
        int newHp = this.getHp() - c.getAtk();
        this.setHp(newHp);
    }
    
    public boolean isAlive() {
        return this.getHp() >= 0;
    }
}
