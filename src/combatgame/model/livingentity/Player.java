package combatgame.model.livingentity;

import combatgame.model.Inventory;
import combatgame.model.item.Armor;
import combatgame.model.item.Item;
import combatgame.model.item.weapon.Weapon;
import combatgame.controller.Vars;

public class Player extends LivingEntity
{
    private String name;
    private Inventory inventory;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int gold;

    public Player(String name)
    {
        super(100, 0, 0, 0, 0);
        this.name = name;
        inventory = new Inventory(10);
        gold = Vars.PLAYER_DEFAULT_GOLD;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int getMaxDefense()
    {
        return (equippedArmor!=null) ? equippedArmor.getMaxEffect() + maxDefense : maxDefense;
    }

    @Override
    public int getMinDefense()
    {
        return (equippedArmor!=null) ? equippedArmor.getMinEffect() + minDefense : minDefense;
    }

    @Override
    public int getDefense()
    {
        return (equippedArmor!=null) ? equippedArmor.getDefense() + super.getDefense() : super.getDefense();
    }

    @Override
    public int getMinAttack()
    {
        return (equippedWeapon!=null) ? equippedWeapon.getMinEffect() + minAttack : minAttack;
    }

    @Override
    public int getMaxAttack()
    {
        return (equippedWeapon!=null) ? equippedWeapon.getMaxEffect() + maxAttack : maxAttack;
    }

    @Override
    public int getAttack()
    {
        return (equippedWeapon!=null) ? equippedWeapon.getDamage() + super.getAttack() : super.getAttack();
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    public Weapon getEquippedWeapon()
    {
        return this.equippedWeapon;
    }

    public void setEquippedWeapon(int slot)
    {
        Item item = inventory.getItem(slot);
        if (item == null)
        {
            throw new IllegalArgumentException("empty slot.");
        }
        if (!(item instanceof Weapon))
        {
            throw new IllegalArgumentException("not a weapon.");
        }
        this.equippedWeapon = (Weapon) item;
    }

    public void setEquippedArmor(Armor armor)
    {
        if (armor != null && !inventory.hasItem(armor))
        {
           throw new IllegalArgumentException("you dont have this armor."); 
        }
        this.equippedArmor = armor;
    }

    public void setEquippedWeapon(Weapon weapon)
    {
        if (weapon != null && !inventory.hasItem(weapon))
        {
            throw new IllegalArgumentException("you dont have this weapon."); 
        }
        this.equippedWeapon = weapon;
    }

    public Item getEquippedArmor()
    {
        return this.equippedArmor;
    }

    public void setEquippedArmor(int slot)
    {
        Item item = inventory.getItem(slot);
        if (item == null)
        {
            throw new IllegalArgumentException("empty slot.");
        }
        if (!(item instanceof Armor))
        {
            throw new IllegalArgumentException("not a armor.");
        }
        this.equippedArmor = (Armor) item;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

}