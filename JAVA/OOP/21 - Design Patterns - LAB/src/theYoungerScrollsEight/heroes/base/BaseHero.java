package theYoungerScrollsEight.heroes.base;

public abstract class BaseHero implements Hero {
    private String name;
    private int health;
    private double offense;
    private double defense;
    private double totalPoints;
    private String type;

    private double magica;
    private double fatique;

    public BaseHero(String name, int health, double offense, double defense, double totalPoints, String type) {
        this.name = name;
        this.health = health;
        this.offense = offense;
        this.defense = defense;
        this.totalPoints = totalPoints;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public double getOffense() {
        return offense;
    }

    public void setOffense(double offense) {
        this.offense = offense;
    }

    @Override
    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    @Override
    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public boolean isDead() {
        return this.getHealth() <= 0;
    }
}
