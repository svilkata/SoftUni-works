package rpg_lab.interfaces;



public interface Weapon {
    public int getAttackPoints();

    public int getDurabilityPoints();

    public void attack(Target target);
}
