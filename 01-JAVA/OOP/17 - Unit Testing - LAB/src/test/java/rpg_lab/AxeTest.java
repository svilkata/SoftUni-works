package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AxeTest {
    private static final int BASE_ATTACK = 50;
    private static final int BASE_DURABILITY = 1;
    private static final int BASE_HEALTH = 10;
    private static final int BASE_EXPERIENCE = 10;
    private Dummy dummy;
    private Axe axe;


    @Test
    public void shouldLoseDurabilityAfterAttack() {
        this.axe = new Axe(BASE_ATTACK,BASE_DURABILITY);
        this.dummy = new Dummy(BASE_HEALTH, BASE_EXPERIENCE);

        this.axe.attack(dummy);

        assertEquals(BASE_DURABILITY - 1, this.axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWithNegativeDurability() {
        this.axe = new Axe(BASE_ATTACK,1);
        this.dummy = new Dummy(BASE_HEALTH, BASE_EXPERIENCE);

        this.axe.attack(dummy);
        this.axe.attack(dummy);
    }


}
