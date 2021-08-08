package combatgame.model.item.weapon;

import java.util.ArrayList;
import java.util.List;

import combatgame.model.item.Item;
import combatgame.model.item.weapon.enchantment.WeaponEnchantment;

public class Weapon extends Item
{
    private final String weaponType;
    private final String damageType;
    private List<WeaponEnchantment> enchantments;

    public Weapon(String name, int minDamage, int maxDamage, int cost, String damageType, String weaponType)
    {
        super(name, minDamage, maxDamage, cost);
        this.weaponType = weaponType;
        this.damageType = damageType;
        enchantments = new ArrayList<>();
    }

    public int getDamage()
    {
        int damage = getRandEffect();

        for (WeaponEnchantment enchantment : enchantments)
        {
            damage = enchantment.getEnchantmentEffect(damage);
        }

        return damage;
    }

    public void addEnchantment(WeaponEnchantment enchantment)
    {
        if (enchantment == null)
        {
            throw new IllegalArgumentException("null enchantment");
        }

        enchantments.add(enchantment);
        cost += enchantment.getCost();
    }

    @Override
    public Weapon clone()
    {
        return new Weapon(name, minEffect, maxEffect, cost, damageType, weaponType);
    }

    @Override
    public String getDescription()
    {
        String description = String.format("%s, effect (%d-%d), type (%s), damage (%s), (%d) gold",
                                            name, minEffect, maxEffect, weaponType, damageType, cost);
        if (enchantments.size() > 0)
        {
            description += ", \n\tEnchanted with: ";
            
            for (WeaponEnchantment enchantment : enchantments)
            {
                description += "\n\t\t" + enchantment.getName();
            }
        }

        return description;
    }

    @Override
    public boolean equal(Object weapon)
    {
        Weapon other;
        boolean isEqual = false;

        if (weapon != null && super.equals(weapon) && weapon instanceof Weapon)
        {
            other = (Weapon) weapon;
            isEqual = (weaponType.equals(other.weaponType)) && (damageType.equals(other.damageType));
        }

        return isEqual;
    }
}
