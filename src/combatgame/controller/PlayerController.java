package combatgame.controller;

import combatgame.controller.exception.PlayerControllerException;
import combatgame.model.Inventory;
import combatgame.model.item.Armor;
import combatgame.model.item.Item;
import combatgame.model.item.Potion;
import combatgame.model.item.weapon.Weapon;
import combatgame.model.item.weapon.enchantment.WeaponEnchantment;
import combatgame.model.livingentity.Monster;
import combatgame.model.livingentity.Player;

public class PlayerController
{
    Player player;

    public PlayerController(Player player)
    {
        this.player = player;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Item equipeWeapon(int slot) throws PlayerControllerException
    {
        Item weapon = checkItem(slot);

        if (!(weapon instanceof Weapon))
        {
            throw new PlayerControllerException("item is not a weapon.");
        }

        player.setEquippedWeapon(slot);

        return weapon;
    }  
    
    public Item equipeArmor(int slot) throws PlayerControllerException
    {
        Item armor = checkItem(slot);

        if (!(armor instanceof Armor))
        {
            throw new PlayerControllerException("item is not a armor.");
        }

        player.setEquippedArmor(slot);

        return armor;
    }

    public Potion usePotion(int slot, Monster enemy) throws PlayerControllerException
    {
        int effect;
        Potion potion;
        Item item = checkItem(slot);

        if (!(item instanceof Potion))
        {
            throw new PlayerControllerException("item is not a potion.");
        }

        potion = (Potion) item;
        effect = potion.getRandEffect();

        if (potion.getType().equals(Vars.POTION_TYPE_HEALING))
        {
            player.setCurrentHealth(Math.min(player.getMaxHealth() ,player.getCurrentHealth() + effect));
        }
        else if (potion.getType().equals(Vars.POTION_TYPE_DAMAGE))
        {
            enemy.setCurrentHealth(enemy.getCurrentHealth() - Math.max(0, effect - enemy.getDefense()));
        }

        player.getInventory().removeItem(slot);
        
        return potion;
    }

    public void attackMonster(Monster monster) throws PlayerControllerException
    {
        int damage;

        if (player.isDead())
        {
            throw new PlayerControllerException("player is dead.");
        }

        if (monster.isDead())
        {
            throw new PlayerControllerException("monster is already dead.");
        }
        
        damage = player.getAttack();
        monster.setCurrentHealth(monster.getCurrentHealth() - Math.max(0, damage - monster.getDefense()));
    }

    public Item enchantWeapon(int slot, WeaponEnchantment enchantment) throws PlayerControllerException
    {
        Item item = checkItem(slot);

        if (!(item instanceof Weapon))
        {
            throw new PlayerControllerException("non weapon item cannot be enchanted.");
        }

        ((Weapon) item).addEnchantment(enchantment);
        
        return item;
    }

    public Item removeItem(int slot) throws PlayerControllerException
    {
        Item item = checkItem(slot);

        if (item.equals(player.getEquippedWeapon()))
        {
            player.setEquippedWeapon(null);
        }

        if (item.equals(player.getEquippedArmor()))
        {
            player.setEquippedArmor(null);
        }

        player.getInventory().removeItem(slot);

        return item;
    }

    public void givePlayerItem(Item item) throws PlayerControllerException
    {
        if (player.getInventory().isFull())
        {
            throw new PlayerControllerException("inventory is full.");
        }

        player.getInventory().addItem(item);
    }

    public int takeDamage(int damage)
    {
        int trueDamage;

        trueDamage = Math.max(0, damage - player.getDefense());
        player.setCurrentHealth(player.getCurrentHealth() - trueDamage);

        return trueDamage;
    }

    public Inventory getInventory()
    {
        return player.getInventory();
    }

    public Item getItem(int slot) throws PlayerControllerException
    {
        return checkItem(slot);
    }

    public void addGold(int gold)
    {
        player.setGold(player.getGold() + gold);
    }

    public void takeAwayGold(int gold)
    {
        player.setGold(Math.max(0, player.getGold() - gold));
    }

    public boolean isDead()
    {
        return player.isDead();
    }

    public boolean canAfford(int gold)
    {
        return player.getGold() >= gold;
    }

    public int getGold()
    {
        return player.getGold();
    }

    public String getName()
    {
        return player.getName();
    }

    public int getCurrentHealth()
    {
        return player.getCurrentHealth();
    }

    public int getMinAttack()
    {
        return player.getMinAttack();
    }

    public int getMaxAttack()
    {
        return player.getMaxAttack();
    }

    public int getMinDefense()
    {
        return player.getMinDefense();
    }

    public int getMaxDefense()
    {
        return player.getMaxDefense();
    }

    public String getEquippedWeaponName()
    {
        return (player.getEquippedWeapon() != null) ? player.getEquippedWeapon().getName() : "";
    }

    public String getEquippedArmorName()
    {
        return (player.getEquippedArmor() != null) ? player.getEquippedArmor().getName() : "";
    }

    public void heal(int health)
    {
        player.setCurrentHealth(Math.min(player.getMaxHealth(), player.getCurrentHealth() + health));
    }

    public void setName(String name) throws PlayerControllerException
    {
        if (name == null)
        {
            throw new PlayerControllerException("cannot change player's name to null");
        }

        if (name.length() < Vars.MIN_PLAYER_NAME_LEN)
        {
            throw new PlayerControllerException(String.format("minimum player length is (%d),", Vars.MIN_PLAYER_NAME_LEN));
        }

        player.setName(name);
    }

    private Item checkItem(int slot) throws PlayerControllerException
    {
        Item item;

        try
        {
            item = player.getInventory().getItem(slot);

            if (item == null)
            {
                throw new PlayerControllerException("invalid item.");
            }
        }
        catch (IllegalArgumentException iae)
        {
            throw new PlayerControllerException(iae.getMessage());
        }

        return item;
    }
}