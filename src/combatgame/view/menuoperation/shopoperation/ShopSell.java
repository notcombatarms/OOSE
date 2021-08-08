package combatgame.view.menuoperation.shopoperation;

import combatgame.controller.PlayerController;
import combatgame.controller.ShopController;
import combatgame.controller.exception.ShopControllerException;
import combatgame.model.GameStatus;
import combatgame.model.item.Item;
import combatgame.view.GameIO;
import combatgame.view.exception.InvalidDisplayWidthException;
import combatgame.view.menudisplayer.Label;
import combatgame.view.menudisplayer.MenuDisplayer;
import combatgame.view.menudisplayer.Segment;
import combatgame.view.menudisplayer.SelectedOption;
import combatgame.view.menuoperation.MenuOperationStrat;

/**
 * The ShopBuy object is what displays the selling menu to the screen. Player
 * sell their inventort items here to the shop.
 * 
 * @author Tao Hu
 * @since 25/05/2020
 */

public class ShopSell implements MenuOperationStrat
{
    /**
     * segement contains the label used to display player's gold
     * menuDisplayer is what displays the players current gold and all items in
     * the players inventory
     */
    private GameIO io;
    private PlayerController playerController;
    private Segment segment;
    private MenuDisplayer menuDisplayer;
    private ShopController shopController;

    /**
     * construct a ShopSell object that is used to display the sell shop to the player
     * and allowing player to sell their invetory items
     * 
     * @param shopController the controller used to modify the player
     * it is called when player sells item to the shop
     * @param io used to output player's inventory to the screen and inputs
     * @param player the player who is going to sell items
     */
    public ShopSell(ShopController shopController, GameIO io, PlayerController playerController)
    {
        this.shopController = shopController;
        this.io = io;
        this.playerController = playerController;

        menuDisplayer = new MenuDisplayer(io, "Shop Sell");
        segment = new Segment(4, "PlayerStat");
        segment.addLabel(new Label("Gold", ""));
        menuDisplayer.addSegment("PlayerStat", segment);
    }

    /**
     * update the players current gold to the displayer
     */
    private void updatePlayerGold()
    {
        segment.getLabel("Gold").setText(Integer.toString(playerController.getGold()));
    }

    /**
     * update the players current inventory items to the displayer
     */
    private void updatePlayerIventory()
    {
        menuDisplayer.setOptions(playerController.getInventory().getItemsNames());
    }

    /**
     * displays all items from the player's invetory to the scree and ask
     * input from player, whether to sell and item or to leave the shop.
     */
    @Override
    public void operate(GameStatus status)
    {
        /**
         * the gold player had before buying item
         * item item the player sold 
         * selected the players decison made on the menu
         */
        int gold;
        Item item;
        SelectedOption selected;

        gold = playerController.getGold();

        updatePlayerGold();
        updatePlayerIventory();

        try
        {
            if ((selected = menuDisplayer.displayMenuAndIn()) != null)
            {
                item = shopController.sellItem(selected.getChoice());
                io.outputln("Successfully sold (" + item.getName() + ") for (" + (playerController.getGold() - gold) + ") gold.");
            }
        }
        catch (InvalidDisplayWidthException idw)
        {
            io.outputErr("Error: " + idw.getMessage());
        }
        catch (ShopControllerException sce)
        {
            io.outputErr("Error: " + sce.getMessage());
        }
    }
}