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
import combatgame.controller.exception.ShopLoadingException;

/**
 * The ShopBuy object is what displays the buying menu to the screen. It represent
 * the shop that player buys item from.
 * 
 * @author Tao Hu
 * @since 25/05/2020
 */

public class ShopBuy implements MenuOperationStrat
{
    /**
     * segment is used to contain the label to be displayed along the shop items
     * menuDisplay is the object that will display all the shop objects
     * shop the shop model that contains the items that can be bought
     */
    private Segment segment;
    private MenuDisplayer menuDisplayer;
    private ShopController shopController;
    private GameIO io;
    private PlayerController playerController;

    /**
     * Construct A ShopBuy object, that is used as callback of a menu item.
     * 
     * @param shopController the controller used to modify the shop and player
     * it is called when player buys item from shop
     * @param io used to output shop items to screen and take user input
     * @param shop the shop that is displayed
     * @param player the player of the game, who buys from shop
     */
    public ShopBuy(ShopController shopController, GameIO io, PlayerController playerController)
    {
        this.shopController = shopController;
        this.io = io;
        this.playerController = playerController;

        menuDisplayer = new MenuDisplayer(io, "Shop Buy");
        segment = new Segment(4, "PlayerStat");
        segment.addLabel(new Label("Gold", ""));
        menuDisplayer.addSegment("PlayerStat", segment);
    }

    /**
     * update the gold to be displayed by menuDisplayer
     */
    private void updatePlayerGold()
    {
        segment.getLabel("Gold").setText(Integer.toString(playerController.getGold()));
    }

    /**
     * update the shop items to be displayed by menuDisplayer
     * @throws ShopLoadingException shop.loadFrom method throws this exception
     * when it fails to load the shop items
     */
    public void updateShopItems()
    {
        /*
        List<String> itemDescs = new ArrayList<>();

        shop.loadFrom(Vars.DEFAULT_SHOP_NAME);

        for (String itemIter : shop.getItems().keySet())
        {
            itemDescs.add(shop.getItem(itemIter).getDescription());
        }*/

        //shopController.loadFrom(Vars.DEFAULT_SHOP_NAME);
        menuDisplayer.setOptions(shopController.getItemsDescs());
    }

    /**
     * Displays the gold player has and all shop items to the user. Takes input
     * from user, selecting a item to buy or to leave the shop. If item is to be
     * bought then the controller is called to do the rest.
     */
    @Override
    public void operate(GameStatus status)
    {
        Item item;
        int gold;
        SelectedOption selected;

        gold = playerController.getGold();

        updatePlayerGold();

        try
        {
            //updateShopItems();

            if ((selected = menuDisplayer.displayMenuAndIn()) != null)
            {
                item = shopController.givePlayer(selected.getChoiceName().split(",")[0]);
                //item = shop.getItem(itemNames.get(Integer.parseInt(choice) - 1));
                io.outputln("Successfully bought (" + item.getName() +") with (" + (gold - playerController.getGold()) + ") gold");
            }
        }
        catch (InvalidDisplayWidthException ide)
        {
            io.outputErr("Error: " + ide.getMessage());
        }
        /*
        catch (ShopLoadingException sle)
        {
            io.outputErr("Error occurred when loading shop: " + sle.getMessage());
        }*/
        catch (ShopControllerException sce)
        {
            io.outputErr("Error: " + sce.getMessage());
        }
    }
}