package main.cardGame.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.cardGame.model.Dealer;
import main.cardGame.model.Deck;
import main.cardGame.model.Players;
import main.framework.GameComponent;
import main.framework.Player;

public class GameComponentFactory {

    private Map<String, GameComponent> pool = new HashMap<>();
    private static final GameComponentFactory singleton = new GameComponentFactory();

    private GameComponentFactory() {}


    public synchronized Deck createDeck() {
        GameComponent target = pool.get("deck");
        if (target == null) {
            target = new Deck();
            pool.put("deck", target);
        }

        return (Deck) target;
    }

    public synchronized Dealer createDealer() {
        GameComponent target = pool.get("dealer");
        if (target == null) {
            target = new Dealer();
            pool.put("dealer", target);
        }

        return (Dealer) target;
    }

    public synchronized Players createPlayers(List<Player> players) {
        GameComponent target = pool.get("players");
        if (target == null) {
            target = new Players(players);
            pool.put("players", target);
        }

        return (Players) target;
    }
    public static GameComponentFactory getInstance() {
        return singleton;
    }
}
