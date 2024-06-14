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

        // 팩토리를 통해서 애플리케이션에 필요한 인스턴스 생성 & 등록
        // 컨트롤러에서는 사용만함
        players = gameComponentFactory.createPlayers(list);
        deck = gameComponentFactory.createDeck();
        dealer = gameComponentFactory.createDealer();
    }



    public void play() {
        // 사용자로부터 라운드 횟수, 상점 입력 받음
        int numberOfRound = input.getNumberOfRounds();
        int award = input.getAward();

        // 해당 라운드 만큼 게임 진행(요구사항에서는 100)
        for (int currRound=1; currRound<=numberOfRound; currRound++) {
            round(currRound, award);
        }
    }

    public void round(int currRound, int award) {
        // 덱이 카드 섞고 나눔
        int numberOfPlayer = players.getPlayers().size();
        List<List<Card>> splitCards = deck.splitCards(numberOfPlayer);

        // 나눈 카드를 플레이어에게 각각 배분
        players.giveOutCards(splitCards);

        // 딜러가 각 플레이어의 점수 계산
        dealer.calculateScore(players.getPlayers());

        // 해당 라운드 승자 추출
        List<Player> winners = players.decideWinnerInRound(award);

        // 해당 라운드 승자 출력
        output.showRoundWinner(currRound, winners);
    }

    
    public void end() {
        // 최종 우승자 추출
        List<Player> winners = players.decideWinner();

        // 최종 우승자 출력
        output.showFinalWinner(winners);
    }

}
