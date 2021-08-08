package combatgame.view.menudisplayer;

public class Label
{
    private String name;
    private String text;

    public Label(String name, String text)
    {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return name + ": " + text;
    }

}