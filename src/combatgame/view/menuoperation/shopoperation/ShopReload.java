package combatgame.view.menuoperation.shopoperation;

import java.util.ArrayList;
import java.util.List;

import combatgame.controller.ShopController;
import combatgame.controller.Vars;
import combatgame.controller.exception.ShopLoadingException;
import combatgame.model.GameStatus;
import combatgame.view.GameIO;
import combatgame.view.menuoperation.MenuOperationStrat;

public class ShopReload implements MenuOperationStrat
{
    GameIO io;
    ShopController shopController;
    List<ShopLoadObserver> observers;

    public ShopReload(GameIO io, ShopController shopController)
    {
        this.io = io;
        this.shopController = shopController;
        observers = new ArrayList<>();
    }

    public void addObserver(ShopLoadObserver observer)
    {
        if (observer == null)
        {
            throw new IllegalArgumentException("null observer.");
        }
        observers.add(observer);
    }

    private void notifyAllObservers()
    {
        for (ShopLoadObserver observer : observers)
        {
            observer.update();
        }
    }

    @Override
    public void operate(GameStatus status)
    {
        try
        {
            shopController.loadFrom(Vars.DEFAULT_SHOP_NAME);
        }
        catch(ShopLoadingException sle)
        {
            io.outputln("Error occured while loading shop: " + sle.getMessage());
        }
        
        notifyAllObservers();
    }
}