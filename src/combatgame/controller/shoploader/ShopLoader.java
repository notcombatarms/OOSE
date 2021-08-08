package combatgame.controller.shoploader;

import java.util.HashMap;
import java.util.Map;

import combatgame.model.item.Armor;
import combatgame.model.item.Potion;
import combatgame.model.item.weapon.Weapon;
import combatgame.model.item.weapon.enchantment.DamageByFactor;
import combatgame.model.item.weapon.enchantment.ExtraDamage;
import combatgame.model.item.weapon.enchantment.ExtraRandomDamage;
import combatgame.model.shop.Shop;
import combatgame.controller.exception.ShopLoadingException;

public abstract class ShopLoader
{
    protected Map<String, ShopItemAdder> shopItemAdders;
    protected String delimiter;
    protected String fileName;

    public ShopLoader()
    {
        delimiter = ", ";
        shopItemAdders = new HashMap<>();

        shopItemAdders.put("W", new ShopItemAdderItem(
            (info) -> new Weapon(info[1], Integer.parseInt(info[2]),
            Integer.parseInt(info[3]), Integer.parseInt(info[4]), 
            info[5], info[6])));
        shopItemAdders.put("A", new ShopItemAdderItem(
            (info) -> new Armor(info[1], Integer.parseInt(info[2]),
            Integer.parseInt(info[3]), Integer.parseInt(info[4]), 
            info[5])));
        shopItemAdders.put("P", new ShopItemAdderItem(
            (info) -> new Potion(info[1], Integer.parseInt(info[2]),
            Integer.parseInt(info[3]), Integer.parseInt(info[4]), 
            info[5])));
        shopItemAdders.put("ED", new ShopItemAdderEnchantment(
            (info) -> new ExtraDamage(info[1], Integer.parseInt(info[2]), 
            Integer.parseInt(info[3])
        )));
        shopItemAdders.put("ERD", new ShopItemAdderEnchantment(
            (info) -> new ExtraRandomDamage(info[1], Integer.parseInt(info[2]), 
            Integer.parseInt(info[3]), Integer.parseInt(info[4])
        )));
        shopItemAdders.put("DBF", new ShopItemAdderEnchantment(
            (info) -> new DamageByFactor(info[1], Integer.parseInt(info[2]), 
            Double.parseDouble(info[3])
        )));
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void load(Shop shop) throws ShopLoadingException
    {
        ShopItemAdder shopItemAdder;
        String[] readItem;

        try
        {
            openFile();
            while ((readItem = readItem()) != null)
            {
                shopItemAdder = shopItemAdders.get(readItem[0]);

                if (shopItemAdder != null)
                {
                    shopItemAdder.addToShop(shop, readItem);
                }
            }
            closeFile();
        }
        catch (NumberFormatException nfe)
        {
            throw new ShopLoadingException("integers expected but string found", nfe);
        }
    }

    public void setDelimiter(String delimiter)
    {
        if (delimiter == null || delimiter.equals(""))
        {
            throw new IllegalArgumentException("delimiter cannot be null nor empty");
        }
        this.delimiter = delimiter;
    }

    public void addStopItemAdder(String key, ShopItemAdder itemAdder)
    {
        if (itemAdder == null)
        {
            throw new IllegalArgumentException("null item adder.");
        }
        shopItemAdders.put(key, itemAdder);
    }

    public abstract String[] readItem() throws ShopLoadingException;
    public abstract void openFile() throws ShopLoadingException;
    public abstract void closeFile();
}