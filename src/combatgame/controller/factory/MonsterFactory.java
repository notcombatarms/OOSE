package combatgame.controller.factory;

import combatgame.model.livingentity.Monster;
import combatgame.controller.Vars;

public class MonsterFactory {

    public Monster createMonster(String species)
    {
        Monster monster = null;

        if (species.equals(Vars.SLIME))
        {
            monster = new Monster(Vars.SLIME, 10, 10, 0, 2, 3, 5);
            monster.addAbilities(Vars.NO_DAMAGE);
        }
        else if (species.equals(Vars.GOBLIN))
        {
            monster = new Monster(Vars.GOBLIN, 20, 30, 4, 8, 3, 8);
            monster.addAbilities(Vars.EXTRA_DAMAGE_3);
        }
        else if (species.equals(Vars.OGRE))
        {
            monster = new Monster(Vars.OGRE, 40, 40, 6, 12, 5, 10);
            monster.addAbilities(Vars.REPEAT_ATTACK_2);
        }
        else if (species.equals(Vars.FINAL_BOSS))
        {
            monster = new Monster(Vars.FINAL_BOSS, 100, 100, 15, 20, 15, 30);
            monster.addAbilities(Vars.DAMAGE_TIMES_2);
            monster.addAbilities(Vars.HEALING_10);
        }

        return monster;
    }
}