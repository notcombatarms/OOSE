package combatgame.controller;

import combatgame.model.abilities.*;
import combatgame.model.item.Armor;
import combatgame.model.item.weapon.Weapon;

public class Vars
{
    //Shop
    public static final String DEFAULT_SHOP_NAME = "Shop.txt";
    public static final int DEFAULT_SELL_RATIO = 2;
    public static final String FILE_FORMAT_REGEX = "\\.";

    //weapon
    public static final Weapon STARTER_WEAPON = new Weapon("Worst Sword", 1, 2, 0, "slashing", "Sword");
    public static final Armor STARTER_ARMOR = new Armor("Worst Armor", 1, 2, 0, "Leaves");

    //Monster
    public static final String SLIME = "Slime";
    public static final String GOBLIN = "Goblin";
    public static final String OGRE = "Ogre";
    public static final String FINAL_BOSS = "Dragon";
    public static final String POTION_TYPE_HEALING = "H";
    public static final String POTION_TYPE_DAMAGE = "D";

    //Ability
    public static final Ability EXTRA_DAMAGE_3 = new Ability("Extra 3 Damage", 0.5d, "deal 3 extra damage", new AExtraDamage(3.0d));
    public static final Ability NO_DAMAGE = new Ability("No Damage", 0.2d, "deal no damage", new AExtraDamage((double) Integer.MIN_VALUE));
    public static final Ability REPEAT_ATTACK_2 = new Ability("Double Attack", 0.2d, "attack twice", new AExtraAttack(2));
    public static final Ability DAMAGE_TIMES_2 = new Ability("Double Damage", 0.25d, "deal double damage", new AMultiplyDamage(2));
    public static final Ability HEALING_10 = new Ability("Small Regeneration", 0.1d, "regenerate 10 health", new AHealing(10));

    //Player
    public static final int MIN_PLAYER_NAME_LEN = 4;
    public static final int PLAYER_DEFAULT_GOLD = 100;

    //battle
    public static final double HEALTH_INCREASE_AFTER_BATTLE = 0.5d;
} 