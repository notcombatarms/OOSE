package combatgame.model.item.weapon.enchantment;

public class ExtraRandomDamage extends WeaponEnchantment
{
    private int minDamage;
    private int maxDamage;

    public ExtraRandomDamage(String name, int cost, int minDamage, int maxDamage)
    {
        super(name, cost, String.format(
            "Adds between %d-%d (randomly) to attack damage"
            , minDamage, maxDamage));
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public int getEnchantmentEffect(int damage)
    {
        return damage + (minDamage + (int) (Math.random() * (double) (maxDamage - minDamage + 1)));
    }

    @Override
    public ExtraRandomDamage clone()
    {
        return new ExtraRandomDamage(name, cost, minDamage, maxDamage);
    }

}