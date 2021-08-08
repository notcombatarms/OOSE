package combatgame.controller.exception;

@SuppressWarnings("serial")
public class ShopControllerException extends Exception
{
    public ShopControllerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ShopControllerException(String message)
    {
        super(message);
    }
}