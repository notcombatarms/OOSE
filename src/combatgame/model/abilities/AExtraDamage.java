package combatgame.model.abilities;

import combatgame.model.livingentity.Monster;

public class AExtraDamage implements AbilityStrat
{
    private double extraDamage;

    public AExtraDamage(double extraDamage)
    {
        this.extraDamage = extraDamage;
    }

    @Override
    public void effect(Monster monster)
    {
        monster.setEnhancedAttack(monster.getEnhancedAttack() + extraDamage);
    }
}