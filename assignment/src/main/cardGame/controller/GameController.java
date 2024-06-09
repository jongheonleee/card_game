package main.cardGame.controller;

import java.util.ArrayList;
import java.util.List;
import main.cardGame.factory.GameComponentFactory;
import main.cardGame.model.Card;
import main.cardGame.model.Dealer;
import main.cardGame.model.Deck;
import main.cardGame.model.Players;
import main.framework.Player;
import main.cardGame.view.InputView;
import main.cardGame.view.OutputView;
import main.framework.PlayerFactory;

// 전체 게임 애플리케이션 관장
public class GameController {

    private static final Integer MIN_ROUND_COUNT = 1;
    private static final Integer MAX_ROUND_COUNT = 10_000;

    private static final Integer MIN_NUMBER_OF_PLAYER = 2;
    private static final Integer MAX_NUMBER_OF_PLAYER = 4;

    private static final String WRONG_NUMBER_OF_PLAYER = "잘못된 플레이어 수 입니다. 허용 가능한 플레이어 수는 2~4명입니다. 다시 입력해주세요";

    private static final String WRONG_ROUND_COUNT = "잘못된 게임 라운드 수입니다. 허용 가능한 라운드 수는 1~10,000입니다. 다시 입력해주세요";

    private final InputView input;
    private final OutputView output;

    private final PlayerFactory playerFactory;

    private final GameComponentFactory gameComponentFactory;

    private Players players;
    private Dealer dealer;
    private Deck deck;


    public GameController(InputView input, OutputView output, PlayerFactory playerFactory, GameComponentFactory gameComponentFactory) {
        this.input = input;
        this.output = output;
        this.playerFactory = playerFactory;
        this.gameComponentFactory = gameComponentFactory;
    }

    public void createGameComponent() {
        // 플레이어 생성
        List<Player> list = new ArrayList<>();
        int numberOfPlayer = input.getNumberOfPlayer();

        // n명의 플레이어가 정상적으로 생성될 때까지 반복
        while (numberOfPlayer > 0) {
            try {
                String name = input.getPlayerName();
                Player player = playerFactory.create(name);
                list.add(player);
                numberOfPlayer--;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        players = gameComponentFactory.createPlayers(list);
        deck = gameComponentFactory.createDeck();
        dealer = gameComponentFactory.createDealer();
    }



    public void play() {
        int numberOfRound = input.getNumberOfRounds();
        int award = 100;
        for (int currRound=1; currRound<=numberOfRound; currRound++) {
            round(currRound, award);
        }
    }

    public void round(int currRound, int award) {
        List<List<Card>> splitCards = deck.splitCards(players.getPlayers().size());
        players.giveOutCards(splitCards);
        dealer.calculateScore(players.getPlayers());
        List<Player> winners = players.decideWinnerInRound(award);
        output.showRoundWinner(currRound, winners);
    }

    
    public void end() {
        List<Player> winners = players.decideWinner();
        output.showFinalWinner(winners);
    }

}
