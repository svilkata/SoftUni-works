package rpg_lab;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {
    private static final int BASE_HEALTH = 10;
    private static final int BASE_EXPERIENCE = 10;
    private Dummy dummy;

    private void setupAliveDummy(){
        this.dummy = new Dummy(BASE_HEALTH, BASE_EXPERIENCE);
    }

    private void setupDeadDummy(){
        this.dummy = new Dummy(-BASE_HEALTH, BASE_EXPERIENCE);
    }

    @Test
    public void dummyShouldLoseHealthWhenAttacked(){
        setupAliveDummy();
        final int attack = 5;
        this.dummy.takeAttack(attack);
        assertEquals(BASE_HEALTH - attack, this.dummy.getHealth());
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenAttackingDeadDummy(){
        setupDeadDummy();
        this.dummy.takeAttack(10);
    }

    @Test
    public void dummyShouldGiveExperienceIFDead(){
        setupDeadDummy();
        int actualExperience = this.dummy.giveExperience();
        assertEquals("Base dummy gave wrong experience", BASE_EXPERIENCE, actualExperience);
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenGivingExperienceIfAlive(){
        setupAliveDummy();
        dummy.giveExperience();
    }
}
