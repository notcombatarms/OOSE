package combatgame.model.livingentity;

public abstract class LivingEntity
{
    protected int currentHealth;
    protected int maxHealth;
    protected int minDefense;
    protected int maxDefense;
    protected int minAttack;
    protected int maxAttack;

    public LivingEntity(int maxHealth, int minDefense, int maxDefense, int minAttack, int maxAttack)
    {
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
        this.minDefense = minDefense;
        this.maxDefense = maxDefense;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
    }
    
    //getters and setters
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMinDefense() {
        return this.minDefense;
    }

    public void setMinDefense(int minDefense) {
        this.minDefense = minDefense;
    }

    public int getMaxDefense() {
        return this.maxDefense;
    }

    public void setMaxDefense(int maxDefense) {
        this.maxDefense = maxDefense;
    }

    public int getDefense()
    {
        return minDefense + (int) (Math.random() * (double) (maxDefense - minDefense));
    }

    public int getMinAttack() {
        return this.minAttack;
    }

    public void setMinAttack(int minAttack) {
        this.minAttack = minAttack;
    }

    public int getAttack()
    {
        return minAttack + (int) (Math.random() * (double) (maxAttack - minAttack + 1));
    }

    public int getMaxAttack() {
        return this.maxAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public boolean isDead()
    {
        return this.currentHealth <= 0;
    }
}