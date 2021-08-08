package combatgame.model.livingentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import combatgame.model.abilities.Ability;

public class Monster extends LivingEntity
{
    private String species;
    private int rewardGold;
    private double enhancedAttack;
    private List<Ability> abilities;

    public Monster(String species, int rewardGold, int maxHealth, int minDefense, int maxDefense, int minAttack, int maxAttack)
    {
        super(maxHealth, minDefense, maxDefense, minAttack, maxAttack);
        this.species = species;
        this.rewardGold = rewardGold;
        this.enhancedAttack = 0;
        this.abilities = new ArrayList<>();
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getRewardGold() {
        return this.rewardGold;
    }

    public void setRewardGold(int rewardGold) {
        this.rewardGold = rewardGold;
    }

    public double getEnhancedAttack()
    {
        return enhancedAttack;
    }

    public void setEnhancedAttack(double enhancedAttack)
    {
        this.enhancedAttack = enhancedAttack;
    }

    public void addAbilities(Ability ability)
    {
        if (ability == null)
        {
            throw new IllegalArgumentException("ability cannot be null");
        }
        abilities.add(ability);
    }

    public List<Ability> getAbilities()
    {
        return Collections.unmodifiableList(abilities);
    }
}