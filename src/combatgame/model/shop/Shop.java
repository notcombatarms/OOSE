package combatgame.model.shop;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import combatgame.model.item.Armor;
import combatgame.model.item.Item;
import combatgame.model.item.weapon.Weapon;
import combatgame.model.item.weapon.enchantment.WeaponEnchantment;
import combatgame.controller.exception.ShopLoadingException;
import combatgame.controller.shoploader.ShopLoader;

public class Shop
{
    private ShopLoader shopLoader;
    private Weapon cheapestWeapon;
    private Armor cheapestArmor;
    private Map<String, Item> items;
    private Map<String, WeaponEnchantment> weaponEnchantments;

    public Shop(ShopLoader shopLoader)
    {
        this.shopLoader = shopLoader;
        items = new HashMap<>();
        weaponEnchantments = new HashMap<>();
        cheapestWeapon = null;
        cheapestArmor = null;
    }

    public Map<String, WeaponEnchantment> getWeaponEnchantments()
    {
        return Collections.unmodifiableMap(weaponEnchantments);
    }

    public Map<String, Item> getItems()
    {
        return Collections.unmodifiableMap(items);
    }

    public Item getItem(String name)
    {
        return items.get(name);
    }

    public WeaponEnchantment getEnchantment(String key)
    {
        return weaponEnchantments.get(key);
    }

    public boolean hasEnchantment(String name)
    {
        return weaponEnchantments.get(name) != null;
    }

    public boolean hasItem(String name)
    {
        return items.get(name) != null;
    }

    public ShopLoader getShopLoader() {
        return this.shopLoader;
    }

    public void setShopLoader(ShopLoader shopLoader) {
        this.shopLoader = shopLoader;
    }

    public Weapon getCheapestWeapon()
    {
        return cheapestWeapon;
    }

    public void setCheapestWeapon(Weapon weapon)
    {
        this.cheapestWeapon = weapon;
    }

    public Armor getCheapestArmor()
    {
        return cheapestArmor;
    }

    public void setCheapestArmor(Armor armor)
    {
        this.cheapestArmor = armor;
    }

    public void addShopItem(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("null item");
        }
        items.put(item.getName(), item);
    }

    public void addWeaponEnchant(WeaponEnchantment enchant)
    {
        if (enchant == null)
        {
            throw new IllegalArgumentException("null enchantment");
        }
        weaponEnchantments.put(enchant.getName(), enchant);
    }

    public void loadFrom(String fileName) throws ShopLoadingException
    {
        shopLoader.setFileName(fileName);

        shopLoader.load(this);
    }
}