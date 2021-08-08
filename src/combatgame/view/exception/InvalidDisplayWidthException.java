package combatgame.view.exception;

@SuppressWarnings("serial")
public class InvalidDisplayWidthException extends Exception
{
    public InvalidDisplayWidthException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidDisplayWidthException(String message)
    {
        super(message);
    }
}