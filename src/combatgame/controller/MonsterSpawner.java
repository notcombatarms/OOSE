package combatgame.controller;

import java.util.ArrayList;
import java.util.List;

import combatgame.controller.factory.MonsterFactory;
import combatgame.model.livingentity.Monster;

public class MonsterSpawner
{
    private MonsterFactory monsterFactory;
    private List<MonsterRatio> monsters;
    private int totalProbability;

    private class MonsterRatio
    {
        private String name;
        private int probability;
        private int minProbability;
        private int ratioChange;

        public MonsterRatio(String name, int minProbability, int probability, int ratioChange)
        {
            this.name = name;
            this.probability = probability;
            this.ratioChange = ratioChange;
            this.minProbability = minProbability;
        }

        public String getName()
        {
            return name;
        }

        public int getProbability()
        {
            return probability;
        }

        public int getRatioChange()
        {
            return ratioChange;
        }

        public int getMinPrabability()
        {
            return minProbability;
        }

        public void setProbability(int probability)
        {
            this.probability = probability;
        }
    }

    public MonsterSpawner(MonsterFactory monsterFactory)
    {
        this.monsterFactory = monsterFactory;
        totalProbability = 0;
        monsters = new ArrayList<>();
    }

    public void addNewMonster(String name, int minProbability, int probability, int ratioChange)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name cannot be null.");
        }

        if (totalProbability + probability > 100)
        {
            throw new IllegalArgumentException("ratio over 100.");
        }

        monsters.add(new MonsterRatio(name, minProbability, probability, ratioChange));
        totalProbability+= probability;
    }

    public Monster nextMonster()
    {
        String monsterName = null;
        int randomVal, currentProb, monsterProb;
        
        currentProb = 0;
        randomVal = (int) (Math.random() * 100.0d);
        
        for (MonsterRatio monsterData : monsters)
        {
            monsterProb = monsterData.getProbability();
            if (currentProb <= randomVal && randomVal < currentProb + monsterProb)
            {
                monsterName = monsterData.getName();
            }

            currentProb += monsterProb;
            monsterData.setProbability(Math.max(monsterData.getMinPrabability(), monsterProb + monsterData.getRatioChange()));
        }

        return monsterFactory.createMonster(monsterName);
    }
}