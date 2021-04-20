package rpg_lab;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import static org.junit.Assert.assertEquals;

public class HeroTest {
    private static final int BASE_EXPERIENCE = 10;

    @Test
    public void shouldReceiveExperienceIfDummyDiesDuringAttack() {
        Weapon axe = new Weapon() {
            public int getAttackPoints() {
                return 0;
            }

            public int getDurabilityPoints() {
                return 10;
            }

            public void attack(Target target) {
                target.takeAttack(target.getHealth() + 1);
            }
        };
        Target target = new Dummy(10, 10);
        Hero hero = new Hero("asd", BASE_EXPERIENCE, axe);

        hero.attack(target);

        assertEquals(BASE_EXPERIENCE + 10, hero.getExperience());
    }

    @Test
    public void shouldReceiveLootAfterKillingTarget(){
        Axe mockAxe = Mockito.mock(Axe.class);
        Mockito.when(mockAxe.getAttackPoints()).thenReturn(10);

        Target mockTarget = Mockito.mock(Target.class);
        Mockito.when(mockTarget.getLoot()).thenReturn(mockAxe);
        Mockito.when(mockTarget.isDead()).thenReturn(true);

        Hero hero = new Hero("asd", 0, mockAxe);

        hero.attack(mockTarget);

        Assert.assertEquals(hero.getInventory().size(), 1);
        Assert.assertTrue(hero.getInventory().contains(mockAxe));


    }



}
