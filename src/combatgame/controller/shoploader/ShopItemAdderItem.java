package combatgame.controller.shoploader;

import combatgame.model.item.Item;
import combatgame.model.shop.Shop;

public class ShopItemAdderItem implements ShopItemAdder
{
    private ItemConstructor callBack;

    public interface ItemConstructor
    {
        public Item construct(String[] itemInfo);
    }

    public ShopItemAdderItem(ItemConstructor callBack)
    {
        this.callBack = callBack;
    }

    @Override
    public void addToShop(Shop shop, String[] data)
    {
        shop.addShopItem(callBack.construct(data));
    }
}