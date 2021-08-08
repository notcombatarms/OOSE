package combatgame.view.menudisplayer;

import java.util.LinkedHashMap;
import java.util.Map;


public class Segment
{
    private String name;
    private int hDistance;
    private Map<String, Label> labels;

    public Segment(int hDistance, String name)
    {
        this.name = name;
        this.hDistance = hDistance;
        labels = new LinkedHashMap<>();
    }

    public String getName()
    {
        return this.name;
    }

    public int getHDistance()
    {
        return this.hDistance;
    }

    public void setHDistance(int hDistance)
    {
        this.hDistance = hDistance;
    }

    public Map<String,Label> getLabels()
    {
        return this.labels;
    }

    public Label getLabel(String name)
    {
        return labels.get(name);
    }

    public void setLabels(Map<String,Label> labels)
    {
        this.labels = labels;
    }

    public void addLabel(Label label)
    {
        labels.put(label.getName(), label);
    }

}