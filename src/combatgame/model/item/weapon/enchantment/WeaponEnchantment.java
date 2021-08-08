package combatgame.model.item.weapon.enchantment;

public abstract class WeaponEnchantment
{
    protected String name;
    protected int cost;
    protected String effect;

    public WeaponEnchantment(String name, int cost, String effect)
    {
        this.name = name;
        this.cost = cost;
        this.effect = effect;
    }

    public String getName()
    {
        return name;
    }

    public int getCost() {
        return this.cost;
    }

    public String getDescription()
    {
        return String.format("%s, %s, (%d) gold", name, effect, cost);
    }

    public abstract int getEnchantmentEffect(int damage);

    public abstract WeaponEnchantment clone();
}