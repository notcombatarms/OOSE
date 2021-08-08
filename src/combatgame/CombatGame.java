package combatgame;

import combatgame.view.MenuBuilder;

public class CombatGame
{
    public static void main(String[] args)
    {
        MenuBuilder.buildCLI().start();

        /*
        try
        {
            MenuBuilder.buildCLI().start();
        }
        catch (Exception e)
        {
            System.out.println("Unknow Fatal Error: " + e.getMessage());
        }*/
    }
}