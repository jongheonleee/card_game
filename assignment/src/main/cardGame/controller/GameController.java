package main.cardGame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.cardGame.model.Dealer;
import main.cardGame.model.Deck;
import main.cardGame.model.Player;
import main.cardGame.view.InputView;
import main.cardGame.view.OutputView;

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
    private Dealer dealer;
    private Deck deck;


    public GameController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    private void ready(int numberOfPlayer) {
        List<Player> players = new ArrayList<>();
        // 밑에 for문을 굳이 컨트롤러가 알아야할까? 객체를 생성하는 로직이 굳이 여기에 쓸 필요가 있을까?
        // 나중에 팩토리 패턴으로 생성과 사용을 불리하기
        for (int i=0; i<numberOfPlayer; i++) {
            String name = input.getPlayerName();
            Player player = new Player(name);
            players.add(player);
        }

        // 밑에도 마찬가지로 팩토리 패턴으로 생성과 사용을 불리하기
        deck = new Deck();
        dealer = new Dealer(deck, players);
    }


    public void play(int numberOfPlayer, int numberOfRound) {
        ready(numberOfPlayer);

        // 게임 진행
        for (int i=1; i<=numberOfRound; i++) {
            dealer.giveOutCards(numberOfPlayer);
            dealer.calculateScore();
            List<Player> winners = dealer.decideWinnerInRound();
            output.showRoundWinner(i, winners);
        }
    }

    public int askNumberOfPlayer() {
        try {
            int numberOfPlayer = input.getNumberOfPlayer();
            if (!(MIN_NUMBER_OF_PLAYER <= numberOfPlayer && numberOfPlayer <= MAX_NUMBER_OF_PLAYER)) {
                throw new IllegalArgumentException(WRONG_NUMBER_OF_PLAYER);
            }

            return numberOfPlayer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return askNumberOfPlayer();
        }
    }

    public int askNumberOfRound() {
        try {
            int numberOfRounds = input.getNumberOfRounds();
            if (!(MIN_ROUND_COUNT <= numberOfRounds && numberOfRounds <= MAX_ROUND_COUNT)) {
                throw new IllegalArgumentException(WRONG_ROUND_COUNT);
            }

            return numberOfRounds;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return askNumberOfRound();
        }
    }
//
//    // 사용자로부터 몇명 플레이할 건지 물어보고 입력 받음
//    public int ready(int numberOfPlayer) {
//        // 사용자에게 게임 시작한다고 알림
//        // 게임에 필요한 값들 입력 받기
//        // 게임에 필요한 객체들 생성
////        int numberOfPlayer = input.getNumberOfPlayer();
////        System.out.println("플레이어 수를 입력해주세요(최소 2~4) > ");
////        numberOfPlayer = sc.nextInt();
//
//        List<Player> players = new ArrayList<>();
//        // 밑에 for문을 굳이 컨트롤러가 알아야할까? 객체를 생성하는 로직이 굳이 여기에 쓸 필요가 있을까?
//        // 나중에 팩토리 패턴으로 생성과 사용을 불리하기
//        for (int i=0; i<numberOfPlayer; i++) {
//            String name = input.getPlayerName();
//
//            Player player = new Player(name);
//            players.add(player);
//        }
//
//        // 밑에도 마찬가지로 팩토리 패턴으로 생성과 사용을 불리하기
//        deck = new Deck();
//        dealer = new Dealer(deck, players);
//
//        return numberOfPlayer;
//    }

    // 사용자에게 게임 결과를 출력해줌
    
    public void end() {
        List<Player> winners = dealer.decideWinner();
        output.showFinalWinner(winners);
    }

}
