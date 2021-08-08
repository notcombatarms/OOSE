package combatgame.model;

import combatgame.model.livingentity.Monster;
import combatgame.model.livingentity.Player;

public class Battle
{
    private Player player;
    private Monster monster;
    private boolean ended;
    private int moves;

    public Battle(Player player, Monster monster)
    {
        this.player = player;
        this.monster = monster;
        this.ended = false;
        this.moves = 0;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Monster getMonster()
    {
        return monster;
    }

    public int getMoves()
    {
        return moves;
    }

    public void setMoves(int moves)
    {
        this.moves = moves;
    }

    public boolean isEnded()
    {
        return ended;
    }

    public void setEnded(boolean ended)
    {
        this.ended = ended;
    }
    
}