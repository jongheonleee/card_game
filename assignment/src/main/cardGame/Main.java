package main.cardGame;

import java.util.ArrayList;
import java.util.List;
import main.cardGame.controller.GameController;
import main.cardGame.factory.CardGamePlayerFactory;
import main.cardGame.factory.GameComponentFactory;
import main.cardGame.model.Dealer;
import main.cardGame.model.Deck;
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

        // 라운드 횟수 지정(요구사항에선 100)
        int numberOfRound = inputView.getNumberOfRounds();

        // 플레이어 생성
        List<Player> players = new ArrayList<>();
        int numberOfPlayer = inputView.getNumberOfPlayer();

        // n명의 플레이어가 정상적으로 생성될 때까지 반복
        while (numberOfPlayer > 0) {
            try {
                String name = inputView.getPlayerName();
                Player player = playerFactory.create(name);
                players.add(player);
                numberOfPlayer--;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        Deck deck = gameComponentFactory.getDeck();
        Dealer newDealer = gameComponentFactory.getDealer();
        Dealer dealer = new Dealer(deck, players);

        // 애플리케이션 운영
        GameController controller = new GameController(inputView, outputView, players, dealer, deck);
        // 팩토리를 통해서 players, dealer, deck 생성해서 받기
        controller.play(numberOfRound);

        // 애플리케이션 종료
        controller.end();
    }
}