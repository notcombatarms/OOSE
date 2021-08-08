package combatgame.view;

import combatgame.controller.PlayerController;
import combatgame.view.exception.InvalidDisplayWidthException;
import combatgame.view.menudisplayer.Label;
import combatgame.view.menudisplayer.MenuDisplayer;
import combatgame.view.menudisplayer.Segment;
import combatgame.view.menudisplayer.SelectedOption;

public class InventoryView
{
    private Segment segment;
    private Label equippedWeapon, equippedArmor;
    private PlayerController playerController;
    private MenuDisplayer menuDisplayer;

    public InventoryView(PlayerController playerController, MenuDisplayer menuDisplayer)
    {
        this.playerController = playerController;
        this.menuDisplayer = menuDisplayer;

        segment = new Segment(4, "Equipped Items");

        equippedWeapon = new Label("Equipped Weapon", "");
        equippedArmor = new Label("Equipped Armor", "");

        segment.addLabel(equippedWeapon);
        segment.addLabel(equippedArmor);

        menuDisplayer.addSegment(segment.getName(), segment);
    }

    private void updateIventoryStats()
    {
        equippedWeapon.setText(playerController.getEquippedWeaponName());
        equippedArmor.setText(playerController.getEquippedArmorName());
        menuDisplayer.setOptions(playerController.getInventory().getItemsNames());
    }

    public SelectedOption displayAndIn() throws InvalidDisplayWidthException
    {
        updateIventoryStats();

        return menuDisplayer.displayMenuAndIn();
    }
}