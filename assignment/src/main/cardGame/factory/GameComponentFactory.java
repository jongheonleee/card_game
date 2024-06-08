package main.cardGame.factory;

import java.util.HashMap;
import java.util.Map;
import main.cardGame.model.Dealer;
import main.cardGame.model.Deck;
import main.framework.GameComponent;

public class GameComponentFactory {

    private Map<String, GameComponent> pool = new HashMap<>();
    private static final GameComponentFactory singleton = new GameComponentFactory();

    private GameComponentFactory() {}


    public synchronized Deck getDeck() {
        GameComponent target = pool.get("deck");
        if (target == null) {
            target = new Deck();
            pool.put("deck", target);
        }

        return (Deck) target;
    }

    public synchronized Dealer getDealer() {
        GameComponent target = pool.get("dealer");
        if (target == null) {
            target = new Dealer(null, null);
            pool.put("dealer", target);
        }

        return (Dealer) target;
    }
    public static GameComponentFactory getInstance() {
        return singleton;
    }
}
