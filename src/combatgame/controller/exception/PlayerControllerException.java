package combatgame.controller.exception;

@SuppressWarnings("serial")
public class PlayerControllerException extends Exception
{
    public PlayerControllerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PlayerControllerException(String message)
    {
        super(message);
    }
}    