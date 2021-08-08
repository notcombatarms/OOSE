package combatgame.view.menuoperation;

import combatgame.controller.PlayerController;
import combatgame.controller.exception.PlayerControllerException;
import combatgame.model.GameStatus;
import combatgame.model.item.Item;
import combatgame.view.GameIO;
import combatgame.view.InventoryView;
import combatgame.view.exception.InvalidDisplayWidthException;
import combatgame.view.menudisplayer.SelectedOption;

public class ChooseArmor implements MenuOperationStrat
{
    InventoryView inventoryView;
    PlayerController playerController;
    GameIO io;

    public ChooseArmor(GameIO io, PlayerController playerController, InventoryView inventoryView)
    {
        this.inventoryView = inventoryView;
        this.playerController = playerController;
        this.io = io;   
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
                item = playerController.equipeArmor(selected.getChoice());
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
