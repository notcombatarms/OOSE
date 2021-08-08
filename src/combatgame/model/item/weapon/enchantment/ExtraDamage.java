package combatgame.model.item.weapon.enchantment;

public class ExtraDamage extends WeaponEnchantment
{
    private int extraDamage;

    public ExtraDamage (String name, int cost, int extraDamage)
    {
        super(name, cost, String.format("Adds %d to attack damage", extraDamage));
        this.extraDamage = extraDamage;
    }
    
    @Override
    public int getEnchantmentEffect(int damage)
    {
        return damage + extraDamage;
    }

    @Override
    public ExtraDamage clone()
    {
        return new ExtraDamage(name, cost, extraDamage);
    }
}