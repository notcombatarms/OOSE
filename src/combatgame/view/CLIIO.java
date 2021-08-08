package combatgame.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLIIO implements GameIO
{
    private Scanner sc;

    public CLIIO(Scanner sc)
    {
        this.sc = sc;
    }

    @Override
    public int inputIntegerBet(String msg, int min, int max)
    {
        int value;

        value = min - 1;
        System.out.print(msg);

        while (value < min || value > max)
        {
            try
            {
                value = sc.nextInt();

                if (value < min)
                {
                    System.out.print("Error, value too small: ");
                }
                else if (value > max)
                {
                    System.out.print("Error, value too large: ");
                }
            }
            catch (InputMismatchException ime)
            {
                System.out.print("Error, please enter a integer: ");
            }
            sc.nextLine();
        }

        return value;
    }

    @Override
    public String inputString(String msg)
    {
        System.out.print(msg);
        return sc.nextLine();
    }

    @Override
    public void output(String msg)
    {
        System.out.print(msg);
    }

    @Override
    public void outputln(String msg)
    {
        output(msg + "\n");
    }

    @Override
    public void outputErr(String msg)
    {
        System.err.println(msg);
    }

    @Override
    public void waitInput()
    {
        sc.nextLine();
    }
}