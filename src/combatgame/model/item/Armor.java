package combatgame.model.item;

public class Armor extends Item
{
    protected final String material;

    public Armor(String name, int minDefense, int maxDefense, int cost, String material)
    {
        super(name, minDefense, maxDefense, cost);
        this.material = material;
    }

    public int getDefense()
    {
        return getRandEffect();
    }

    @Override
    public String getDescription()
    {
        return String.format("%s, defense (%d-%d), material (%s), (%d) gold",
                                name, minEffect, maxEffect, material, cost);
    }

    @Override
    public Armor clone()
    {   
        return new Armor(name, minEffect, maxEffect, cost, material);
    }
}