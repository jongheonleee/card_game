package main.cardGame;

import java.util.ArrayList;
import java.util.List;
import main.cardGame.controller.GameController;
import main.cardGame.factory.CardGamePlayerFactory;
import main.cardGame.factory.GameComponentFactory;
import main.cardGame.model.Dealer;
import main.cardGame.model.Deck;
import main.cardGame.model.Players;
import main.framework.Player;
import main.cardGame.view.InputView;
import main.cardGame.view.OutputView;

public class Main {

    public static void main(String[] args) {
        // 애플리케이션에 필요한 객체를 생성
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();
        CardGamePlayerFactory playerFactory = CardGamePlayerFactory.getInstance();
        GameComponentFactory gameComponentFactory = GameComponentFactory.getInstance();
        GameController controller = new GameController(inputView, outputView, playerFactory, gameComponentFactory);
        controller.createGameComponent();
        controller.play();
        controller.end();
    }
}