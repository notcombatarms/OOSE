package combatgame.view.menudisplayer;

public class SelectedOption
{
    private int choice;
    private String choiceName;
    
    public SelectedOption(int choice, String choiceName)
    {
        this.choice = choice;
        this.choiceName = choiceName;
    }

    public int getChoice()
    {
        return choice;
    }

    public String getChoiceName()
    {
        return choiceName;
    }
}