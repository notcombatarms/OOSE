package combatgame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import combatgame.controller.exception.PlayerControllerException;
import combatgame.controller.exception.ShopControllerException;
import combatgame.controller.exception.ShopLoadingException;
import combatgame.model.item.Item;
import combatgame.model.item.weapon.enchantment.WeaponEnchantment;
import combatgame.model.shop.Shop;

public class ShopController
{
    Shop shop;
    PlayerController playerController;

    public ShopController(Shop shop, PlayerController playerController)
    {
        this.shop = shop;
        this.playerController = playerController;
    }

    public List<String> getWeaponEnchantmentsDescs()
    {
        List<String> descs = new ArrayList<>();
        Map<String, WeaponEnchantment> weaponEnchantments = shop.getWeaponEnchantments();

        for (String key : weaponEnchantments.keySet())
        {
            descs.add(weaponEnchantments.get(key).getDescription());
        }

        return descs;
    }

    public Item givePlayer(String itemName) throws ShopControllerException
    {
        Item item;

        if (!shop.hasItem(itemName))
        {
            throw new ShopControllerException("item does not exist in shop.");
        }

        item = shop.getItem(itemName);

        if (!playerController.canAfford(item.getCost()))
        {
            throw new ShopControllerException("you cannot afford the item.");
        }

        try
        {
            playerController.givePlayerItem(item.clone());
            playerController.takeAwayGold(item.getCost());
        }
        catch (PlayerControllerException pce)
        {
            throw new ShopControllerException(pce.getMessage(), pce);
        }

        return item;
    }

    public Item sellItem(int slot) throws ShopControllerException
    {
        Item item;

        try
        {
            item = playerController.removeItem(slot);
            playerController.addGold(item.getCost() / Vars.DEFAULT_SELL_RATIO);
        }
        catch (PlayerControllerException pce)
        {
            throw new ShopControllerException(pce.getMessage(), pce);
        }

        return item;
    }

    public Item givePlayerEnchantment(int slot, String enchantmentName) throws ShopControllerException
    {
        WeaponEnchantment enchantment;
        Item item;

        enchantment = shop.getEnchantment(enchantmentName);

        if (enchantment == null)
        {
            throw new ShopControllerException("this enchantment does not exist in shop.");
        }

        if (!playerController.canAfford(enchantment.getCost()))
        {
            throw new ShopControllerException("you cannot afford this enchantment.");
        }

        try
        {
            item = playerController.enchantWeapon(slot, enchantment);
            playerController.takeAwayGold(enchantment.getCost());
        }
        catch (PlayerControllerException pce)
        {
            throw new ShopControllerException(pce.getMessage(), pce);
        }

        return item;
    }

    public List<String> getItemsDescs()
    {
        List<String> descs = new ArrayList<>();
        Map<String, Item> items = shop.getItems();

        for (String key : items.keySet())
        {
            descs.add(items.get(key).getDescription());
        }

        return descs;
    }

    public void loadFrom(String fileName) throws ShopLoadingException
    {
        shop.loadFrom(fileName);
    }
}