package rpg_lab;

import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hero {
    private String name;
    private int experience;
    private Weapon weapon;
    private List<Weapon> inventory;

    public Hero(String name,  int experience, Weapon axe) {
        this.name = name;
        this.experience = experience;
        this.weapon = axe;
        this.inventory = new ArrayList<Weapon>();
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void attack(Target target) {
        this.weapon.attack(target);

        if (target.isDead()) {
            this.experience += target.giveExperience();
            this.inventory.add(target.getLoot());
        }
    }

    public List<Weapon> getInventory() {
        return this.inventory;
    }
}
