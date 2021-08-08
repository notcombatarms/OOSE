package combatgame.view;

import combatgame.controller.PlayerController;
import combatgame.model.GameStatus;
import combatgame.view.menudisplayer.MenuDisplayer;
import combatgame.view.menudisplayer.Segment;
import combatgame.view.menudisplayer.Label;

public class MainMenu extends Menu
{
    private PlayerController playerController;
    private Segment playerStat;
    private GameStatus status;

    public MainMenu(PlayerController playerController, GameIO gameIO, GameStatus status)
    {
        super("Main Menu", "", new MenuDisplayer(gameIO, "Main Menu"), gameIO);
        this.playerController = playerController;
        this.status = status;

        callBack = () -> updatePlayerStat();

        playerStat = new Segment(4, "Player Stat");
        displayer.addSegment("Player Stat", playerStat);
        playerStat.addLabel(new Label("Name", ""));
        playerStat.addLabel(new Label("Health", ""));
        playerStat.addLabel(new Label("Gold", ""));
        playerStat.addLabel(new Label("Equipped Weapon", ""));
        playerStat.addLabel(new Label("Equipped Armor", ""));

        updatePlayerStat();
    }

    public boolean start()
    {
        return doOperation(status);
    }

    private void updatePlayerStat()
    {
        playerStat.getLabel("Name").setText(playerController.getName());
        playerStat.getLabel("Health").setText(Integer.toString(playerController.getCurrentHealth()));
        playerStat.getLabel("Gold").setText(Integer.toString(playerController.getGold()));
        playerStat.getLabel("Equipped Weapon").setText(playerController.getEquippedWeaponName());
        playerStat.getLabel("Equipped Armor").setText(playerController.getEquippedArmorName());
    }
}