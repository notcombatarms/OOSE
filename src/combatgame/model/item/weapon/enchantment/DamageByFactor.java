package combatgame.model.item.weapon.enchantment;

public class DamageByFactor extends WeaponEnchantment
{
    private double factor;

    public DamageByFactor(String name, int cost, double factor)
    {
        super(name, cost, String.format("Multiplies attack damage by %.2f", factor));
        this.factor = factor;
    }

    @Override
    public int getEnchantmentEffect(int damage)
    {
        return (int)((double) damage * factor);
    }

    @Override
    public DamageByFactor clone()
    {
        return new DamageByFactor(name, cost, factor);
    }
}