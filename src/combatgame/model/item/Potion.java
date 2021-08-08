package combatgame.model.item;

public class Potion extends Item
{
    private final String type;

    public Potion(String name, int minEffect, int maxEffect, int cost, String type)
    {
        super(name, minEffect, maxEffect, cost);
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    
    @Override
    public Potion clone()
    {   
        return new Potion(name, minEffect, maxEffect, cost, type);
    }
}