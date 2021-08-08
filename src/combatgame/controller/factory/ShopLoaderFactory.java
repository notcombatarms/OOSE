package combatgame.controller.factory;

import combatgame.controller.shoploader.ShopItemAdder;
import combatgame.controller.shoploader.ShopItemAdderEnchantment;
import combatgame.controller.shoploader.ShopItemAdderItem;
import combatgame.controller.shoploader.ShopLoader;
import combatgame.controller.shoploader.ShopLoaderFile;
import combatgame.model.item.Armor;
import combatgame.model.item.Potion;
import combatgame.model.item.weapon.Weapon;
import combatgame.model.item.weapon.enchantment.DamageByFactor;
import combatgame.model.item.weapon.enchantment.ExtraDamage;
import combatgame.model.item.weapon.enchantment.ExtraRandomDamage;
import combatgame.model.shop.Shop;

public class ShopLoaderFactory
{
    public static ShopLoader createShopLoader(String type)
    {
        ShopLoader shopLoader = null;

        if (type.equals("txt"))
        {
            shopLoader = new ShopLoaderFile();
        }

        if (shopLoader != null)
        {
            addShopItemAdders(shopLoader);
        }

        return shopLoader;
    }

    public static void addShopItemAdders(ShopLoader shopLoader)
    {
        /*
        shopLoader.addStopItemAdder("W", new ShopItemAdderItem(
            (info) -> new Weapon(info[1], Integer.parseInt(info[2]),
            Integer.parseInt(info[3]), Integer.parseInt(info[4]), 
            info[5], info[6])));*/
    
        shopLoader.addStopItemAdder("W", new ShopItemAdder(){
        
            @Override
            public void addToShop(Shop shop, String[] info) {
                Weapon weapon = new Weapon(info[1], Integer.parseInt(info[2]),
                Integer.parseInt(info[3]), Integer.parseInt(info[4]), 
                info[5], info[6]);

                System.out.println("did weapon");
                if (shop.getCheapestWeapon() != null)
                {
                    if (weapon.getCost() <= shop.getCheapestWeapon().getCost())
                    {
                        System.out.println("did weapon cheap");
                        shop.setCheapestWeapon(weapon);
                    }
                }
                else
                {
                    shop.setCheapestWeapon(weapon);
                }
                shop.addShopItem(weapon);
            }
        });
        /*
        shopLoader.addStopItemAdder("A", new ShopItemAdderItem(
            (info) -> new Armor(info[1], Integer.parseInt(info[2]),
            Integer.parseInt(info[3]), Integer.parseInt(info[4]), 
            info[5])));*/

        shopLoader.addStopItemAdder("A", new ShopItemAdder(){
        
            @Override
            public void addToShop(Shop shop, String[] info) {
                Armor armor = new Armor(info[1], Integer.parseInt(info[2]),
                Integer.parseInt(info[3]), Integer.parseInt(info[4]), 
                info[5]);

                System.out.println("did");
                if (shop.getCheapestArmor() != null)
                {
                    if (armor.getCost() <= shop.getCheapestArmor().getCost())
                    {
                        System.out.println("did cheap");
                        shop.setCheapestArmor(armor);
                    }
                }
                else
                {
                    shop.setCheapestArmor(armor);
                }
                shop.addShopItem(armor);
            }
        });
        shopLoader.addStopItemAdder("P", new ShopItemAdderItem(
            (info) -> new Potion(info[1], Integer.parseInt(info[2]),
            Integer.parseInt(info[3]), Integer.parseInt(info[4]), 
            info[5])));

        //Enchantments
        shopLoader.addStopItemAdder("ED", new ShopItemAdderEnchantment(
            (info) -> new ExtraDamage(info[1], Integer.parseInt(info[2]), 
            Integer.parseInt(info[3])
            )));
        shopLoader.addStopItemAdder("ERD", new ShopItemAdderEnchantment(
            (info) -> new ExtraRandomDamage(info[1], Integer.parseInt(info[2]), 
            Integer.parseInt(info[3]), Integer.parseInt(info[4])
            )));
        shopLoader.addStopItemAdder("DBF", new ShopItemAdderEnchantment(
            (info) -> new DamageByFactor(info[1], Integer.parseInt(info[2]), 
            Double.parseDouble(info[3])
            )));
    }
}