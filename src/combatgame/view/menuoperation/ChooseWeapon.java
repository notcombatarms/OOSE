package combatgame.view.menuoperation;

import combatgame.controller.PlayerController;
import combatgame.controller.exception.PlayerControllerException;
import combatgame.model.GameStatus;
import combatgame.model.item.Item;
import combatgame.view.GameIO;
import combatgame.view.InventoryView;
import combatgame.view.exception.InvalidDisplayWidthException;
import combatgame.view.menudisplayer.SelectedOption;

public class ChooseWeapon implements MenuOperationStrat
{
    InventoryView inventoryView;
    PlayerController playerController;
    GameIO io;

    public ChooseWeapon(GameIO io, PlayerController playerController, InventoryView inventoryView)
    {
        this.playerController = playerController;
        this.io = io;
        this.inventoryView = inventoryView;
    }
    
    @Override
    public void operate(GameStatus status)
    {
        Item item;
        SelectedOption selected;

        try
        {
            if ((selected = inventoryView.displayAndIn()) != null)
            {
                item = playerController.equipeWeapon(selected.getChoice());
                io.outputln(String.format("Successfully equipped (%s)", item.getName()));
            }
        }
        catch (InvalidDisplayWidthException idw)
        {
            io.outputErr("Error: " + idw.getMessage());
        }
        catch (PlayerControllerException sce)
        {
            io.outputErr("Error: " + sce.getMessage());
        }
    }
}

