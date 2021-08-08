package combatgame.model.abilities;

import combatgame.model.livingentity.Monster;

public class AExtraAttack implements AbilityStrat
{
    private int times;

    public AExtraAttack(int times)
    {
        this.times = times;
    }

    @Override
    public void effect (Monster monster)
    {
        for (int i = 0; i < times; i++)
        {
            monster.setEnhancedAttack(monster.getEnhancedAttack() + monster.getAttack());
        }
    }
}