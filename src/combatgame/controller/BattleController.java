package combatgame.controller;

import java.util.ArrayList;
import java.util.List;

import combatgame.controller.exception.BattleControllerException;
import combatgame.controller.exception.PlayerControllerException;
import combatgame.model.Battle;
import combatgame.model.GameStatus;
import combatgame.model.abilities.Ability;
import combatgame.model.item.Potion;
import combatgame.model.livingentity.Monster;

public class BattleController 
{
    private PlayerController playerController;
    private List<Battle> battles;
    private Battle currentBattle;
    private GameStatus status;
    private MonsterSpawner monsterSpawner;

    public BattleController(PlayerController playerController, MonsterSpawner monsterSpawner, GameStatus status)
    {
        this.playerController = playerController;
        this.monsterSpawner = monsterSpawner;
        this.status = status;
        battles = new ArrayList<>();
    }

    public Battle getCurrentBattle()
    {
        return currentBattle;
    }

    public void newBattle()
    {
        currentBattle = new Battle(playerController.getPlayer(), monsterSpawner.nextMonster());
        battles.add(currentBattle);
    }

    public List<Ability> attackPlayer() throws BattleControllerException
    {
        List<Ability> activated = new ArrayList<>();
        Monster monster = currentBattle.getMonster();

        monster.setEnhancedAttack(monster.getAttack());

        for (Ability ability : monster.getAbilities())
        {
            if (Math.random() < ability.getProbability())
            {
                ability.effect(monster);
                activated.add(ability);
            }
        }

        playerController.takeDamage((int) Math.max(0, monster.getEnhancedAttack()));
        
        updateBattleStatusMonster();

        return activated;
    }

    public Potion usePotion(int slot) throws BattleControllerException
    {
        Potion potion;

        try
        {
            potion = playerController.usePotion(slot, currentBattle.getMonster());
        }
        catch (PlayerControllerException pce)
        {
            throw new BattleControllerException(pce.getMessage(), pce);
        }

        updateBattleStatusPlayer();

        return potion;
    }

    public Monster attackMonster() throws BattleControllerException
    {
        Monster monster = currentBattle.getMonster();

        try
        {
            playerController.attackMonster(monster);
        }
        catch (PlayerControllerException pce)
        {
            throw new BattleControllerException(pce.getMessage(), pce);
        }

        updateBattleStatusPlayer();

        return monster;
    }

    public void updateBattleStatusPlayer()
    {
        if (currentBattle.getMonster().isDead())
        {
            currentBattle.setEnded(true);
            status.setEnded(currentBattle.getMonster().getSpecies().equals(Vars.FINAL_BOSS));
            playerController.addGold(currentBattle.getMonster().getRewardGold());
            playerController.heal((int)((double) playerController.getCurrentHealth() * Vars.HEALTH_INCREASE_AFTER_BATTLE));
        }
    }

    public void updateBattleStatusMonster()
    {
        if (playerController.isDead())
        {
            currentBattle.setEnded(true);
            status.setEnded(true);         
        }
    }
}