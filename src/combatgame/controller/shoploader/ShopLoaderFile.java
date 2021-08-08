package combatgame.controller.shoploader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import combatgame.controller.exception.ShopLoadingException;

public class ShopLoaderFile extends ShopLoader
{
    private BufferedReader br;

    public ShopLoaderFile()
    {
    }

    @Override
    public String[] readItem() throws ShopLoadingException
    {
        String info = null;
        String[] splitInfo = null;

        try
        {
            info = br.readLine();

            if (info != null)
            {
                splitInfo = info.split(delimiter);
            }
        }
        catch (IOException ioe)
        {
            throw new ShopLoadingException(String.format("error reading file <%s>, (%s) ", fileName, ioe.getMessage()), ioe);
        }

        return splitInfo;
    }

    @Override
    public void openFile() throws ShopLoadingException
    {
        try
        {
            br = new BufferedReader(new FileReader(fileName));
        }
        catch (FileNotFoundException fnfe)
        {
            throw new ShopLoadingException(String.format("file <%s> not found", fileName), fnfe);
        }
        /*
        catch (IOException ioe)
        {
            throw new ShopLoadingException(String.format("failed to open file <%s>, (%s) ", fileName, ioe.getMessage()), ioe);
        }*/
    }

    @Override
    public void closeFile()
    {
       if (br != null)
       {
            try
           {
               br.close();
           }
           catch (IOException ioe)
           {};
       }
    }
}