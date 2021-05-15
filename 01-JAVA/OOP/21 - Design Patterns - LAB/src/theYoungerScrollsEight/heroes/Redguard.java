package theYoungerScrollsEight.heroes;

import theYoungerScrollsEight.heroes.base.BaseHero;
import theYoungerScrollsEight.heroes.base.Hero;

public class Redguard extends BaseHero {
    public Redguard(String name, int magicka, int fatique, int health) {
        super(name, health, 0, 0, 0, "REDGUARD");
    }

    @Override
    public void attack(Hero hero) {

    }

    @Override
    public void receiveDamage(double amount) {

    }
}
