package combatgame.controller;

import java.util.ArrayList;
import java.util.List;

import combatgame.model.Battle;
import combatgame.model.livingentity.Player;
import combatgame.model.shop.Shop;
import combatgame.view.GameIO;

public class Game
{
    private Player player;
    private GameIO io;
    private boolean ended;
    private PlayerController playerController;
    private ShopController shopController;
    private Shop shop;
    private List<Battle> battles;

    public Game(Player player, GameIO io, PlayerController playerController, ShopController shopController, Shop shop)
    {
        this.player = player;
        this.io = io;
        this.ended = false;
        this.playerController = playerController;
        this.shopController = shopController;
        this.shop = shop;

        battles = new ArrayList<>();
    }

    public Player getPlayer() {
        return this.player;
    }

    public GameIO getIo() {
        return this.io;
    }

    public void setEnded(boolean ended)
    {
        this.ended = ended;
    }

    public boolean getEnded() {
        return this.ended;
    }

    public PlayerController getPlayerController() {
        return this.playerController;
    }

    public void addBattle(Battle battle)
    {
        battles.add(battle);
    }

    public List<Battle> getBattles() {
        return this.battles;
    }

    public Shop getShop()
    {
        return shop;
    }

    public ShopController getShopController()
    {
        return shopController;
    }
    
}