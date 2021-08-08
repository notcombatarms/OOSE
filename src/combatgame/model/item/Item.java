package combatgame.model.item;
import java.util.Random;

public abstract class Item
{
    protected String name;
    protected int cost;
    protected int minEffect;
    protected int maxEffect;
    protected static final Random rand = new Random();

    public Item(String name, int minEffect, int maxEffect, int cost)
    {
        this.name = name;
        this.cost = cost;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
    }

    public int getMinEffect()
    {
        return this.minEffect;
    }

    public int getMaxEffect()
    {
        return this.maxEffect;
    }

    public int getRandEffect()
    {
        return rand.nextInt(maxEffect - minEffect + 1) + minEffect;
    }

    public boolean equal(Object item)
    {
        Item other;
        boolean isEqual = false;

        if (item != null && item instanceof Item)
        {
            other = (Item) item;
            isEqual = (name.equals(other.name)) && (minEffect == other.minEffect) &&
             (maxEffect == other.maxEffect) && (cost == other.cost);
        }

        return isEqual;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return String.format("%s, effect (%d-%d), (%d) gold", name, minEffect, maxEffect, cost); 
    }

    public int getCost() {
        return this.cost;
    }

    @Override
    public abstract Item clone();
}
