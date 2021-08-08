package combatgame.model;

public class GameStatus
{
    private boolean ended;

    public GameStatus()
    {
        ended = false;
    }

    public boolean isEnded() 
    {
        return this.ended;
    }

    public void setEnded(boolean ended) 
    {
        this.ended = ended;
    }

}