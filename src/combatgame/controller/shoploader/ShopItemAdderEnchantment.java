package combatgame.controller.shoploader;

import combatgame.model.item.weapon.enchantment.WeaponEnchantment;
import combatgame.model.shop.Shop;

public class ShopItemAdderEnchantment implements ShopItemAdder
{
    private EnchantmentConstructor callBack;

    public interface EnchantmentConstructor
    {
        public WeaponEnchantment construct(String[] itemInfo);
    }

    public ShopItemAdderEnchantment(EnchantmentConstructor callBack)
    {
        this.callBack = callBack;
    }

    @Override
    public void addToShop(Shop shop, String[] data)
    {
        shop.addWeaponEnchant(callBack.construct(data));
    }
}