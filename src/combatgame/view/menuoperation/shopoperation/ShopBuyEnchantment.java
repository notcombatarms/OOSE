package combatgame.view.menuoperation.shopoperation;

import combatgame.controller.PlayerController;
import combatgame.controller.ShopController;
import combatgame.controller.exception.ShopControllerException;
import combatgame.model.GameStatus;
import combatgame.model.item.Item;
import combatgame.view.GameIO;
import combatgame.view.InventoryView;
import combatgame.view.exception.InvalidDisplayWidthException;
import combatgame.view.menudisplayer.Label;
import combatgame.view.menudisplayer.MenuDisplayer;
import combatgame.view.menudisplayer.Segment;
import combatgame.view.menudisplayer.SelectedOption;
import combatgame.view.menuoperation.MenuOperationStrat;

public class ShopBuyEnchantment implements MenuOperationStrat
{
    private InventoryView inventoryView;
    private Segment segment;
    private MenuDisplayer menuDisplayer;
    private ShopController shopController;
    private GameIO io;
    private PlayerController playerController;

    public ShopBuyEnchantment(ShopController shopController, GameIO io, PlayerController playerController, InventoryView inventoryView)
    {
        this.shopController = shopController;
        this.io = io;
        this.playerController = playerController;
        this.inventoryView = inventoryView;

        menuDisplayer = new MenuDisplayer(io, "Buy Enchantments");
        segment = new Segment(4, "PlayerStat");
        segment.addLabel(new Label("Gold", ""));
        menuDisplayer.addSegment(segment.getName(), segment);
    }

    /**
     * update the gold to be displayed by menuDisplayer
     */
    private void updatePlayerGold()
    {
        segment.getLabel("Gold").setText(Integer.toString(playerController.getGold()));
    }

    public void updateEnchantments()
    {
        menuDisplayer.setOptions(shopController.getWeaponEnchantmentsDescs());
    }

    @Override
    public void operate(GameStatus status)
    {
        Item item;
        String enchantmentName;
        SelectedOption selected, selectedIventory;


        updatePlayerGold();
        //updateEnchantments();

        try
        {
            if ((selected = menuDisplayer.displayMenuAndIn()) != null)
            {
                enchantmentName = selected.getChoiceName().split(",")[0];

                if ((selectedIventory = inventoryView.displayAndIn()) != null)
                {
                    item = shopController.givePlayerEnchantment(selectedIventory.getChoice(), enchantmentName);
                    io.outputln(String.format("Successfully enchanted (%s) with enchantment (%s).",
                                             item.getName(), enchantmentName));
                }
            }
        }
        catch (InvalidDisplayWidthException ide)
        {
            io.outputErr("Error: " + ide.getMessage());
        }
        catch (ShopControllerException sce)
        {
            io.outputErr("Error: " + sce.getMessage());
        }

    }
}