package combatgame.model.abilities;

import combatgame.model.livingentity.Monster;

public class AHealing implements AbilityStrat
{
    private int heals;

    public AHealing(int heals)
    {
        this.heals = heals;
    }

    @Override
    public void effect(Monster monster)
    {
        monster.setCurrentHealth(monster.getCurrentHealth() + heals);
    }
}