package theYoungerScrollsEight.heroes;

import theYoungerScrollsEight.heroes.base.BaseHero;
import theYoungerScrollsEight.heroes.base.Hero;

public class Khajiit extends BaseHero {
    public Khajiit(String name, int health, double offense, double defense, double totalPoints, String type) {
        super(name, health, offense, defense, totalPoints, "KHAJIIT");
    }

    @Override
    public void attack(Hero hero) {

    }

    @Override
    public void receiveDamage(double amount) {

    }
}
