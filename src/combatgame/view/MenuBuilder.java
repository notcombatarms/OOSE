package combatgame.view;

import java.util.Scanner;

import combatgame.controller.BattleController;
import combatgame.controller.MonsterSpawner;
import combatgame.controller.PlayerController;
import combatgame.controller.ShopController;
import combatgame.controller.factory.MonsterFactory;
import combatgame.controller.factory.ShopLoaderFactory;
import combatgame.model.GameStatus;
import combatgame.model.livingentity.Player;
import combatgame.model.shop.Shop;
import combatgame.controller.Vars;
import combatgame.view.menudisplayer.MenuDisplayer;
import combatgame.view.menuoperation.*;
import combatgame.view.menuoperation.shopoperation.*;


public class MenuBuilder
{
    public static MainMenu buildCLI()
    {
        InventoryView inventoryView;
        MainMenu mainMenu;
        Menu shopMenu;
        ShopController shopController;
        PlayerController playerController;
        BattleController battleController;
        Player player;
        Shop shop;
        GameIO gameIO;
        MonsterSpawner monsterSpawner;
        GameStatus status;
        ShopReload reloader;
        ShopBuy shopBuy;
        ShopBuyEnchantment shopBuyEnchantment;
        
        status = new GameStatus();
        gameIO = new CLIIO(new Scanner(System.in));
        shop = new Shop(ShopLoaderFactory.createShopLoader((Vars.DEFAULT_SHOP_NAME.split(Vars.FILE_FORMAT_REGEX))[1]));
        player = new Player("Player2020");

        playerController = new PlayerController(player);
        shopController = new ShopController(shop, playerController);
        inventoryView = new InventoryView(playerController, new MenuDisplayer(gameIO, "Inventory"));

        shopMenu = new Menu("shop Menu", "Go To Shop", new MenuDisplayer(gameIO, " Shop "), gameIO);

        shopBuy = new ShopBuy(shopController, gameIO, playerController);
        shopBuyEnchantment = new ShopBuyEnchantment(shopController, gameIO, playerController, inventoryView);

        reloader = new ShopReload(gameIO, shopController);
        reloader.addObserver(shopBuy::updateShopItems);
        reloader.addObserver(shopBuyEnchantment::updateEnchantments);
        reloader.operate(status);

        System.out.println(shop.getCheapestArmor());
        player.getInventory().addItem((shop.getCheapestArmor() != null) ? shop.getCheapestArmor().clone() : Vars.STARTER_ARMOR.clone());
        player.setEquippedArmor(0);
        player.getInventory().addItem((shop.getCheapestWeapon() != null) ? shop.getCheapestWeapon().clone() : Vars.STARTER_WEAPON.clone());
        player.setEquippedWeapon(1);

        shopMenu.addEntry(new MenuOperation("Buy Items", shopBuy));
        shopMenu.addEntry(new MenuOperation("Buy Enchantment", shopBuyEnchantment));
        shopMenu.addEntry(new MenuOperation("Sell", new ShopSell(shopController, gameIO, playerController)));
        shopMenu.addEntry(new MenuOperation("Reload Shop", reloader));
        
        monsterSpawner = new MonsterSpawner(new MonsterFactory());
        monsterSpawner.addNewMonster(Vars.SLIME, 5, 50, -5);
        monsterSpawner.addNewMonster(Vars.GOBLIN, 5, 30, -5);
        monsterSpawner.addNewMonster(Vars.OGRE, 5, 20, -5);
        monsterSpawner.addNewMonster(Vars.FINAL_BOSS, 5, 0, 15);

        battleController = new BattleController(playerController, monsterSpawner, status);

        mainMenu = new MainMenu(playerController, gameIO, status);
        mainMenu.getDisplayer().setBack("Exit Game");

        mainMenu.addEntry(shopMenu);
        mainMenu.addEntry(new MenuOperation("Choose Character Name", new ChooseCharacterName(gameIO, playerController)));
        mainMenu.addEntry(new MenuOperation("Choose Weapon", new ChooseWeapon(gameIO, playerController, inventoryView)));
        mainMenu.addEntry(new MenuOperation("Choose Armor", new ChooseArmor(gameIO, playerController, inventoryView)));
        mainMenu.addEntry(new BattleMenu(gameIO, battleController, playerController, inventoryView));

        return mainMenu;
    }
}