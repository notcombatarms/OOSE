package combatgame.model.abilities;

import combatgame.model.livingentity.Monster;

public class Ability
{
    private final String name;
    private final double probability;
    private final String description;
    private final AbilityStrat callBack;

    public Ability(String name, double probability, String description, AbilityStrat callBack)
    {
        this.name = name;
        this.probability = probability;
        this.description = description;
        this.callBack = callBack;
    }

    public String getName() 
    {
        return this.name;
    }

    public double getProbability() 
    {
        return this.probability;
    }

    public String getDescription() 
    {
        return this.description;
    }
    
    public void effect(Monster monster)
    {
        callBack.effect(monster);
    }
}