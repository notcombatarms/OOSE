package combatgame.view;

import java.util.List;

import combatgame.controller.BattleController;
import combatgame.controller.PlayerController;
import combatgame.controller.exception.BattleControllerException;
import combatgame.model.GameStatus;
import combatgame.model.abilities.Ability;
import combatgame.model.item.Potion;
import combatgame.model.livingentity.Monster;
import combatgame.controller.Vars;
import combatgame.view.exception.InvalidDisplayWidthException;
import combatgame.view.menudisplayer.Label;
import combatgame.view.menudisplayer.MenuDisplayer;
import combatgame.view.menudisplayer.Segment;
import combatgame.view.menudisplayer.SelectedOption;


public class BattleMenu extends Menu
{
    Monster enemy;
    PlayerController playerController;
    Segment playerStat, enemyStat;
    InventoryView inventoryView;
    BattleController battleController;

    public BattleMenu(GameIO io, BattleController battleController, PlayerController playerController, InventoryView inventoryView)
    {
        super("Battle", "Go To Battle", new MenuDisplayer(io, "Battle"), io);
        this.battleController = battleController;
        this.inventoryView = inventoryView;
        this.playerController = playerController;

        displayer.setBack("Leave This Battle");

        playerStat = new Segment(4, "Player Stat");
        displayer.addSegment("Player Stat", playerStat);
        playerStat.addLabel(new Label("Name", ""));
        playerStat.addLabel(new Label("Health", ""));
        playerStat.addLabel(new Label("Gold", ""));
        playerStat.addLabel(new Label("Attack", ""));
        playerStat.addLabel(new Label("Defense", ""));
        playerStat.addLabel(new Label("Equipped Weapon", ""));
        playerStat.addLabel(new Label("Equipped Armor", ""));

        enemyStat = new Segment(4, "Enemy Stat");
        displayer.addSegment("Enemy Stat", enemyStat);
        enemyStat.addLabel(new Label("Species", ""));
        enemyStat.addLabel(new Label("Health", ""));
        enemyStat.addLabel(new Label("Attack", ""));
        enemyStat.addLabel(new Label("Defense", ""));
        enemyStat.addLabel(new Label("Reward", ""));
        enemyStat.addLabel(new Label("Abilities", ""));

        addEntry(new MenuOperation("Attack", this::attackMonster));
        addEntry(new MenuOperation("Use Potion", this::usePotion));

        callBack = () -> updateBattleStat();
    }

    private void updatePlayerStat()
    {
        playerStat.getLabel("Name").setText(playerController.getName());
        playerStat.getLabel("Health").setText(Integer.toString(playerController.getCurrentHealth()));
        playerStat.getLabel("Gold").setText(Integer.toString(playerController.getGold()));
        playerStat.getLabel("Attack").setText(String.format("%d-%d", playerController.getMinAttack(), playerController.getMaxAttack()));
        playerStat.getLabel("Defense").setText(String.format("%d-%d", playerController.getMinDefense(), playerController.getMaxDefense()));
        playerStat.getLabel("Equipped Weapon").setText(playerController.getEquippedWeaponName());
        playerStat.getLabel("Equipped Armor").setText(playerController.getEquippedArmorName());
    }

    private void updateEnemyStat()
    {
        String abilityNames = "";
    	enemyStat.getLabel("Species").setText(enemy.getSpecies());
    	enemyStat.getLabel("Health").setText(Integer.toString(enemy.getCurrentHealth()));
    	enemyStat.getLabel("Attack").setText(String.format("%d-%d", enemy.getMinAttack(), enemy.getMaxAttack()));
    	enemyStat.getLabel("Defense").setText(String.format("%d-%d", enemy.getMinDefense(), enemy.getMaxDefense()));
        enemyStat.getLabel("Reward").setText(Integer.toString(enemy.getRewardGold()));
        for (Ability ability : enemy.getAbilities())
        {
            abilityNames += ability.getName() + ", ";
        }
        enemyStat.getLabel("Abilities").setText(abilityNames);
    }

    private void updateBattleStat()
    {
        enemy = battleController.getCurrentBattle().getMonster();
        updatePlayerStat();
        updateEnemyStat();
    }

    private void attackMonster(GameStatus status)
    {
        List<Ability> abilities;
        int playerHealth, monsterHealth;
        Monster monster = battleController.getCurrentBattle().getMonster();

        try
        {
            playerHealth = playerController.getCurrentHealth();
            monsterHealth = monster.getCurrentHealth();
            battleController.attackMonster();
            io.outputln(String.format("You attacked (%s) and dealt (%d) damage.",
                                 monster.getSpecies(), (monsterHealth - monster.getCurrentHealth())));

            if (!battleController.getCurrentBattle().getMonster().isDead())
            {
                abilities = battleController.attackPlayer();

                for (Ability ability : abilities)
                {
                    io.outputln(String.format("(%s) have activated ability : (%s)."
                                    , monster.getSpecies(), ability.getName()));
                }

                io.outputln(String.format("(%s) dealt (%d) damage to you."
                                    , monster.getSpecies(), (playerHealth - playerController.getCurrentHealth())));
            }
        }
        catch (BattleControllerException bce)
        {
            io.outputErr("Error: " + bce.getMessage());
        }
    }

    private void usePotion(GameStatus status)
    {
        int monsterHealth, playerHealth;
        Potion potion;
        SelectedOption selected;
        Monster monster = battleController.getCurrentBattle().getMonster();

        monsterHealth = monster.getCurrentHealth();
        playerHealth = playerController.getCurrentHealth();

        try
        {
            if ((selected = inventoryView.displayAndIn()) != null)
            {
                potion = battleController.usePotion(selected.getChoice());

                if (potion.getType().equals(Vars.POTION_TYPE_DAMAGE))
                {
                    io.outputln(String.format("You have used (%s) and dealt (%d) damage to (%s)."
                                , potion.getName(), (monsterHealth - monster.getCurrentHealth()), monster.getSpecies()));
                }
                else if (potion.getType().equals(Vars.POTION_TYPE_HEALING))
                {
                    io.outputln(String.format("You have used (%s) and healed (%d) health."
                                , potion.getName(), (playerController.getCurrentHealth() - playerHealth)));
                }
            }
        }
        catch (InvalidDisplayWidthException ide)
        {
            io.outputErr("Error: " + ide.getMessage());
        }
        catch (BattleControllerException bce)
        {
            io.outputErr("Error: " + bce.getMessage());
        }
    }

    private void outputBattleResult()
    {
        Monster monster = battleController.getCurrentBattle().getMonster();

        if (monster.isDead())
        {
            io.outputln(String.format("You have defeated (%s)."
                                        , monster.getSpecies()));
            io.outputln(String.format("You have being rewarded (%d) gold"
                                        , monster.getRewardGold()));
            if (monster.getSpecies().equals(Vars.FINAL_BOSS))
            {
                io.outputln("You have won the game.");
            }
        }
        else if (playerController.isDead())
        {
            io.output(String.format("You have being defeated by (%s), Game Over."
                                    , monster.getSpecies()));
        }
        
    }

    @Override
    public boolean doOperation(GameStatus status)
    {
        SelectedOption selected = new SelectedOption(-1,"");

        battleController.newBattle();

        try
        {
        	while (!battleController.getCurrentBattle().isEnded() && !status.isEnded())
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
                else
                {
                    io.outputln("Nope, you cannot escape battle.");
                }
            }
        }
        catch(InvalidDisplayWidthException idwe)
        {
            io.outputErr("Error: " + idwe.getMessage());
        }

        outputBattleResult();

        return exitMenu;
    }

}