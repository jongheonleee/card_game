package main.cardGame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.cardGame.model.Dealer;
import main.cardGame.model.Deck;
import main.cardGame.model.Player;
import main.cardGame.view.InputView;

// 전체 게임 애플리케이션 관장
public class GameController {

    private final InputView input;
    private Dealer dealer;
    private Deck deck;


    public GameController(InputView input) {
        this.input = input;
    }

//    public GameController() {
//
//    }
    // 게임 시작
    public void play(int numberOfPlayer, int numberOfRound) {
        List<Player> players = new ArrayList<>();
        // 밑에 for문을 굳이 컨트롤러가 알아야할까? 객체를 생성하는 로직이 굳이 여기에 쓸 필요가 있을까?
        // 나중에 팩토리 패턴으로 생성과 사용을 불리하기
        for (int i=0; i<numberOfPlayer; i++) {
            String name = input.getPlayerName();
//            System.out.println("플레이어의 닉네임을 입력해주세요(글자수 1~20) > ");
//            name = sc.next();

            Player player = new Player(name);
            players.add(player);
        }

        // 밑에도 마찬가지로 팩토리 패턴으로 생성과 사용을 불리하기
        deck = new Deck();
        dealer = new Dealer(deck, players);


        // 게임 진행
        for (int i=0; i<numberOfRound; i++) {
            dealer.giveOutCards(numberOfPlayer);
            dealer.calculateScore();
            List<Player> winners = dealer.decideWinnerInRound();

            System.out.println("\n======== 현재(" + i + ") 라운드 승자 ========\n");
            for (Player winner : winners) {
                System.out.println("이름 : " + winner.getName() + ", 획득 점수 : " + winner.getScore());
            }
            System.out.println("\n=============================\n");
        }
    }

    public int askNumberOfPlayer() {
        try {
            int numberOfPlayer = input.getNumberOfPlayer();
            if (!(2 <= numberOfPlayer && numberOfPlayer <= 4)) {
                throw new IllegalArgumentException("잘못된 플레이어 수 입니다. 허용 가능한 플레이어 수는 2~4명입니다. 다시 입력해주세요");
            }

            return numberOfPlayer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return askNumberOfPlayer();
        }
    }

    public int askNumberOfRounds() {
        try {
            int numberOfRounds = input.getNumberOfRounds();
            if (!(1 <= numberOfRounds && numberOfRounds <= 10_000)) {
                throw new IllegalArgumentException("잘못된 게임 라운드 수입니다. 허용 가능한 라운드 수는 1~10,000입니다. 다시 입력해주세요");
            }

            return numberOfRounds;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return askNumberOfRounds();
        }
    }

    // 사용자로부터 몇명 플레이할 건지 물어보고 입력 받음
    public int ready(int numberOfPlayer) {
        // 사용자에게 게임 시작한다고 알림
        // 게임에 필요한 값들 입력 받기
        // 게임에 필요한 객체들 생성
//        int numberOfPlayer = input.getNumberOfPlayer();
//        System.out.println("플레이어 수를 입력해주세요(최소 2~4) > ");
//        numberOfPlayer = sc.nextInt();

        List<Player> players = new ArrayList<>();
        // 밑에 for문을 굳이 컨트롤러가 알아야할까? 객체를 생성하는 로직이 굳이 여기에 쓸 필요가 있을까?
        // 나중에 팩토리 패턴으로 생성과 사용을 불리하기
        for (int i=0; i<numberOfPlayer; i++) {
            String name = input.getPlayerName();
//            System.out.println("플레이어의 닉네임을 입력해주세요(글자수 1~20) > ");
//            name = sc.next();

            Player player = new Player(name);
            players.add(player);
        }

        // 밑에도 마찬가지로 팩토리 패턴으로 생성과 사용을 불리하기
        deck = new Deck();
        dealer = new Dealer(deck, players);

        return numberOfPlayer;
    }

    // 사용자에게 게임 결과를 출력해줌
    
    public void end() {
        // 결과 발표
        System.out.println("======== 최종 결과 ========\n");

        dealer.decideWinner()
                .stream()
                .forEach(player -> System.out.println("이름 : " + player.getName() + ", 획득 점수 : " + player.getTotalScore()));

        System.out.println("\n========================");
    }

}
