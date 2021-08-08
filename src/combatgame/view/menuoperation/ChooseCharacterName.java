package combatgame.view.menuoperation;

import combatgame.controller.PlayerController;
import combatgame.controller.exception.PlayerControllerException;
import combatgame.model.GameStatus;
import combatgame.view.GameIO;

public class ChooseCharacterName implements MenuOperationStrat
{
    private GameIO io;
    private PlayerController playerController;

    public ChooseCharacterName(GameIO io, PlayerController playerController)
    {
        this.io = io;
        this.playerController = playerController;
    }
    
    @Override
    public void operate(GameStatus status)
    {
        String oldname, name;

        oldname = playerController.getName();

        //get new name
        name = io.inputString("Enter the new Character name: ");

        try
        {

            playerController.setName(name);
            io.outputln(String.format("Successfully changed player's name from (%s) to (%s)"
                                    , oldname, name));
        }
        catch (PlayerControllerException pce)
        {
            io.outputErr("Error in name: " + pce.getMessage());
        }
    }
}
