package rpg_lab;


import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Dummy implements Target {
    private int health;
    private int experience;
    private List<Weapon> possibleLoot;

    public Dummy(int health, int experience) {
        this.health = health;
        this.experience = experience;
        this.possibleLoot = new ArrayList<Weapon>();
        this.possibleLoot.add(new Axe(10, 10));
    }

    public int getHealth() {
        return this.health;
    }

    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public Weapon getLoot() {
        if (this.isDead()) {
            return this.possibleLoot.get(0);
        }

        throw new IllegalStateException("Can not get loot from alive target");
    }
}
