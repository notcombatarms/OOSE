package combatgame.view;

import combatgame.model.GameStatus;

public abstract class MenuEntry
{
    protected boolean exitMenu;
    protected String description;
    
    public MenuEntry(String desc)
    {
        this.exitMenu = false;
        this.description = desc;
    }


    public String getDescription()
    {
        return description;
    }

    public abstract boolean doOperation(GameStatus status);
}