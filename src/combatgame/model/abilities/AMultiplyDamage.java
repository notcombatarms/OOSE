package combatgame.model.abilities;

import combatgame.model.livingentity.Monster;

public class AMultiplyDamage implements AbilityStrat
{
    private double multiplier;

    public AMultiplyDamage(double multiplier)
    {
        this.multiplier = multiplier;
    }

    @Override
    public void effect(Monster monster)
    {
        monster.setEnhancedAttack(monster.getEnhancedAttack() * multiplier);
    }
}