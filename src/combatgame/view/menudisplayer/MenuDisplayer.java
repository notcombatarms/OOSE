package combatgame.view.menudisplayer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import combatgame.view.GameIO;
import combatgame.view.exception.InvalidDisplayWidthException;

public class MenuDisplayer
{
    private String title;
    private String wall;
    private int maxWidth;
    private String indicator;
    private String back;
    private GameIO io;
    private Map<String, Segment> segments;
    private List<String> options;

    public MenuDisplayer(GameIO io, String title)
    {
        this.title = title;
        this.maxWidth = 50;
        this.wall = "-";
        this.indicator = ">";
        this.io = io;
        this.back = "back";
        segments = new LinkedHashMap<>();
        options = new ArrayList<>();
    }

    public MenuDisplayer(int maxwidth, String wall, String indicator, GameIO io, String title)
    {
        this.title = title;
        this.wall = wall;
        this.indicator =indicator;
        this.maxWidth = maxwidth;
        this.io = io;
        segments = new LinkedHashMap<>();
        options = new ArrayList<>();
    }

    public void setBack(String back)
    {
        this.back = back;
    }

    public void setOptions(List<String> options)
    {
        this.options = options;
    }

    public void addOption(String option)
    {
        options.add(option);
    }

    public void addSegment(String name, Segment segment)
    {
        segments.put(name, segment);
    }

    public Segment getSegment(String key)
    {
        return segments.get(key);
    }

    public void repeatPrint(String text, int times)
    {
        for (int i = 0; i < times; i++)
        {
            io.output(text);
        }
    }

    public void printWall(int times)
    {
        repeatPrint(wall, times);
    }

    public void printSegements()
    {
        Segment segment;
        int hDis;
        Map<String, Label> labels;

        for (String key : segments.keySet())
        {
            segment = segments.get(key);
            hDis = segment.getHDistance();
            io.outputln(indicator + " " + segment.getName());
            labels = segment.getLabels();
            for (String labelKey : labels.keySet())
            {
                repeatPrint(" ", hDis);
                io.outputln("+ " + labels.get(labelKey).toString());
            }
        }
    }

    public void printCover(String title) throws InvalidDisplayWidthException
    {
        int width, len;

        len = title.length();
        if (len > maxWidth)
        {
            throw new InvalidDisplayWidthException("menu title's length <" + len +"> is grater than max width <" + maxWidth + "> displayed.");
        }
        width = (maxWidth - len - 2) / 2;
        io.outputln("");
        printWall(width);
        io.output(" " + title + " ");
        printWall(maxWidth - width - len - 2);
        io.outputln("");
    }

    public void displayMenu() throws InvalidDisplayWidthException
    {
        printCover(title);
        printSegements();
        for (int i = 0; i < options.size(); i++)
        {
            io.outputln(String.format("%s%d. %s", indicator, (i+1), options.get(i)));
        }
        io.outputln(String.format("%s%d. %s", indicator, (options.size()+1), back));
        printWall(maxWidth);
        io.outputln("");
    }

    public SelectedOption displayMenuAndIn() throws InvalidDisplayWidthException
    {
        SelectedOption selected = null;
        int choice;

        displayMenu();
        choice = io.inputIntegerBet(String.format("Enter a integer between 1  and %s to select: ", options.size()+1), 1, options.size() + 1) - 1;

        if (choice < options.size())
        {
            selected = new SelectedOption(choice, options.get(choice));
        }

        return selected;
    }
}
