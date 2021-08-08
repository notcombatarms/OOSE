package combatgame.view;

import java.util.List;

import combatgame.model.GameStatus;
import combatgame.view.exception.InvalidDisplayWidthException;
import combatgame.view.menudisplayer.MenuDisplayer;
import combatgame.view.menudisplayer.SelectedOption;

import java.util.ArrayList;

public class Menu extends MenuEntry 
{
    protected String menuName;
    protected List<MenuEntry> entries;
    protected MenuDisplayer displayer;
    protected GameIO io;
    protected MenuCallBack callBack;

    public Menu(String name, String description, MenuDisplayer displayer, GameIO io)
    {
        super(description);
        menuName = name;
        entries = new ArrayList<>();
        this.displayer = displayer;
        this.io = io;
        callBack = null;
    }

    public void setCallBack(MenuCallBack callBack)
    {
        this.callBack = callBack;
    }

    public MenuDisplayer getDisplayer()
    {
        return displayer;
    }

    public void addEntry(MenuEntry entry)
    {
        entries.add(entry);
        displayer.addOption(entry.getDescription());
    }

    public String getMenuName()
    {
        return menuName;
    }

    /*
    public List<MenuEntry> getEntries()
    {
        return Collections.unmodifiableList(entries);
    }*/

    @Override
    public boolean doOperation(GameStatus status)
    {
        SelectedOption selected = new SelectedOption(-1, "");

        try
        {
        	while (!status.isEnded() && selected != null)
        	{
        		if (callBack != null)
        		{
        			callBack.execute();
        		}

        		selected = displayer.displayMenuAndIn();

        		if (selected != null)
        		{
        			entries.get(selected.getChoice()).doOperation(status);
        		}
        	}

            /*
            while (!status.isEnded() && (selected = displayer.displayMenuAndIn()) != null)
            {
                entries.get(selected.getChoice()).doOperation(status);

                if (callBack != null)
                {
                    callBack.execute();
                }
            }*/
            //while (!entries.get(displayer.displayMenuAndIn(menuName, entryNames).getChoice()).doOperation(game));
            /*
            do
            {
                displayer.displayMenu(this);
            } while(!entries.get(io.inputIntegerBet("Enter a integer to select: ", 1, entries.size())).doOperation(game));*/
        }
        catch(InvalidDisplayWidthException idwe)
        {
            io.outputErr("Error: " + idwe.getMessage());
        }

        return exitMenu;
    }
}
