package combatgame.view;

public interface GameIO
{
    public String inputString(String msg);
    public void waitInput();
    public int inputIntegerBet(String msg, int min, int max);
    public void output(String msg);
    public void outputln(String msg);
    public void outputErr(String msg);
}