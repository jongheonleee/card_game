package main.cardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 전체 게임 애플리케이션 관장
public class GameController {

    private static final Scanner sc = new Scanner(System.in);
    
    private Dealer dealer;
    private Deck deck;

    private int numberOfPlayer;

    public GameController() {

    }

    // 게임 시작
    public void play() {
        // 게임 진행
        for (int i=0; i<100; i++) {
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

    // 사용자로부터 몇명 플레이할 건지 물어보고 입력 받음
    public void ready() {
        // 사용자에게 게임 시작한다고 알림
        // 게임에 필요한 값들 입력 받기
        // 게임에 필요한 객체들 생성
        System.out.println("========[포커 게임]========\n");
        System.out.println("플레이어 수를 입력해주세요(최소 2~4) > ");
        numberOfPlayer = sc.nextInt();

        List<Player> players = new ArrayList<>();
        for (int i=0; i<numberOfPlayer; i++) {
            System.out.println("플레이어의 닉네임을 입력해주세요(글자수 1~20) > ");
            String name = sc.next();

            Player player = new Player(name);
            players.add(player);
        }

        deck = new Deck();
        dealer = new Dealer(deck, players);
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
