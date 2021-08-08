package combatgame.view;

import combatgame.model.GameStatus;
import combatgame.view.MenuEntry;
import combatgame.view.menuoperation.MenuOperationStrat;

public class MenuOperation extends MenuEntry
{
    MenuOperationStrat callback;

    public MenuOperation(String description, MenuOperationStrat callback)
    {
        super(description);
        this.callback = callback;
    }

    public MenuOperation(String description, MenuOperationStrat callback, boolean exitMenu)
    {
        super(description);
        this.callback = callback;
        this.exitMenu = exitMenu;
    }

    @Override
    public boolean doOperation(GameStatus status)
    {
        if (callback != null)
        {
            callback.operate(status);
        }

        return exitMenu;
    }
}