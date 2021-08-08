package combatgame.controller.exception;

@SuppressWarnings("serial")
public class ShopLoadingException extends Exception
{
    public ShopLoadingException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ShopLoadingException(String message)
    {
        super(message);
    }
}