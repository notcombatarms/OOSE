package combatgame.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import combatgame.model.item.Item;

public class Inventory
{
    private int count;
    private int maxSize;
    private List<Item> items;

    public Inventory(int size)
    {
        this.maxSize = size;
        this.count = 0;
        items = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            items.add(null);
        }
    }

    public void setMaxSize(int size)
    {
        if (size < maxSize)
        {
            throw new IllegalArgumentException("not supported.");
        }

        if (size > maxSize)
        {
            for (int i = maxSize; i < size; i++)
            {
                items.set(i, null);
            }
        }
        this.maxSize = size;
    }

    public Item getItem(int slot)
    {
        Item item = null;

        if (slot < maxSize && slot >= 0)
        {
            item = items.get(slot);
        }

        return item;
    }

    public List<Item> getItems()
    {
        return Collections.unmodifiableList(items);
    }

    public List<String> getItemsNames()
    {
        List<String> itemNames = new ArrayList<>();

        for (Item itemIter : items)
        {
            if (itemIter != null)
            {
                itemNames.add(itemIter.getDescription());
            }
            else
            {
                itemNames.add("empty");
            }
        }

        return itemNames;
    }

    public void removeItem(int slot)
    {
        if (slot < maxSize && slot >= 0)
        {
            items.set(slot, null);
            count--;
        }
    }

    public void removeItem(Item itemIn)
    {
        int i = 0;

        for (Item item : items)
        {
            if (item != null && item.equals(itemIn))
            {
                items.set(i, null);
            }
            i++;
        }
    }

    public boolean hasItem(Item item)
    {
        return items.contains(item);
    }

    public boolean isFull()
    {
        return count == maxSize;
    }

    public void addItem(Item item)
    {
        boolean added = false;

        if (item == null)
        {
            throw new IllegalArgumentException("null item.");
        }

        if (isFull())
        {
            throw new IllegalArgumentException("full inventory.");
        }
        
        for (int i = 0; i < maxSize && !added; i++)
        {
            if (items.get(i) == null)
            {
                items.set(i, item);
                added = true;
            }
        }
    }
}