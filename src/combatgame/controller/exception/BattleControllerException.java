package combatgame.controller.exception;

@SuppressWarnings("serial")
public class BattleControllerException extends Exception
{
    public BattleControllerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BattleControllerException(String message)
    {
        super(message);
    }
}