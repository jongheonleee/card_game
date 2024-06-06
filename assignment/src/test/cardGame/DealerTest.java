package test.cardGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import main.cardGame.model.Card;
import main.cardGame.model.Dealer;
import main.cardGame.model.Deck;
import main.cardGame.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DealerTest {


    private Deck deck;
    private List<Player> players;
    private Dealer dealer;

    @BeforeEach
    public void 객체_생성() {
        deck = new Deck();
        players = new ArrayList<>();

        for (int i=0; i<5; i++) {
            Player player = new Player("test" + i);
            players.add(player);
        }

        dealer = new Dealer(deck, players);
    }

    // 생성 성공 실패 테스트
    @Test
    @DisplayName("객체 생성 테스트")
    public void 객체_생성_테스트() {
        assertNotNull(dealer);
    }

    // 카드 배분 테스트
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    @DisplayName("카드 배분 테스트")
    public void 카드_배분_테스트(int amount) {
        int cardCnt = 5;
        dealer.giveOutCards(amount);
        for (int i=0; i<amount; i++) {
            assertEquals(cardCnt, players.get(i).getMyDeck().size());
        }

    }

    // 플레이어 점수 계산 테스트
        // 로얄 스트레이트 플러쉬(13), 백 스트레이트 플러쉬(12), 스트레이트 플러쉬(11), 포카드(10)
        // 폴하우스(9), 플러쉬(8), 마운틴(7), 백스트레이트(6), 스트레이트(5), 트리플(4)
        // 투페어(3), 원페어(2), 노페어(1)

    @DisplayName("로얄 스트레이트 플러쉬 계산 테스트")
    @Test
    public void 계산_테스트_1() {
        List<Card> myDeck = new ArrayList<>();
        String shape = "SPADE";
        int[] numbers = {10, 11, 12, 13, 14};
        int score = 13;

        for (int number : numbers) {
            Card card = new Card(shape, number);
            myDeck.add(card);
        }

        players.stream()
                .forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();

        assertTrue(players.stream()
                        .allMatch(player -> player.getScore() == score));
    }

    @DisplayName("백 스트레이트 플러쉬 계산 테스트")
    @Test
    public void 계산_테스트_2() {
        List<Card> myDeck = new ArrayList<>();
        String shape = "SPADE";
        int[] numbers = {14, 2, 3, 4, 5};
        int score = 12;

        for (int number : numbers) {
            Card card = new Card(shape, number);
            myDeck.add(card);
        }

        players.stream()
                .forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();

        assertTrue(players.stream()
                        .allMatch(player -> player.getScore() == score));
    }

    @DisplayName("스트레이트 플러쉬 계산 테스트")
    @Test
    public void 계산_테스트_3() {
        List<Card> myDeck = new ArrayList<>();
        String shape = "SPADE";
        int[] numbers = {2, 3, 4, 5, 6};
        int score = 11;

        for (int number : numbers) {
            Card card = new Card(shape, number);
            myDeck.add(card);
        }

        players.stream()
                .forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();

        assertTrue(players.stream()
                        .allMatch(player -> player.getScore() == score));
    }

    @DisplayName("포카드 계산 테스트")
    @Test
    public void 계산_테스트_4() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "SPADE", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {2, 5, 2, 2, 2};
        int score = 10;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream()
                .forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();

        assertTrue(players.stream()
                        .allMatch(player -> player.getScore() == score));
    }

    @DisplayName("폴하우스 계산 테스트")
    @Test
    public void 계산_테스트_5() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "SPADE", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {2, 5, 2, 2, 5};
        int score = 9;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }

    @DisplayName("플러쉬 계산 테스트")
    @Test
    public void 계산_테스트_6() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "SPADE", "SPADE", "SPADE", "SPADE"};
        int[] numbers = {2, 9, 12, 13, 14};
        int score = 8;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }

    @DisplayName("마운틴 계산 테스트")
    @Test
    public void 계산_테스트_7() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "CLOVA", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {10, 11, 12, 13, 14};
        int score = 7;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }

    @DisplayName("백스트레이트 계산 테스트")
    @Test
    public void 계산_테스트_8() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "CLOVA", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {14, 2, 3, 4, 5};
        int score = 6;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }

    @DisplayName("스트레이트 계산 테스트")
    @Test
    public void 계산_테스트_9() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "CLOVA", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {2, 3, 4, 5, 6};
        int score = 5;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }

    @DisplayName("트리플 계산 테스트")
    @Test
    public void 계산_테스트_10() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "CLOVA", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {2, 2, 2, 5, 6};
        int score = 4;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }

    @DisplayName("투 페어 계산 테스트")
    @Test
    public void 계산_테스트_11() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "CLOVA", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {2, 2, 7, 5, 5};
        int score = 3;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }

    @DisplayName("원 페어 계산 테스트")
    @Test
    public void 계산_테스트_12() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "CLOVA", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {2, 2, 3, 11, 5};
        int score = 2;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }

    @DisplayName("노 페어 계산 테스트")
    @Test
    public void 계산_테스트_13() {
        List<Card> myDeck = new ArrayList<>();
        String[] shapes = {"SPADE", "CLOVA", "HEART", "CLOVA", "DIAMOND"};
        int[] numbers = {2, 7, 3, 11, 5};
        int score = 1;

        for (int i=0; i<5; i++) {
            Card card = new Card(shapes[i], numbers[i]);
            myDeck.add(card);
        }

        players.stream().forEach(player -> player.takeDeck(myDeck));
        dealer.calculateScore();
        assertTrue(players.stream().allMatch(player -> player.getScore() == score));
    }


    // 승자 발표, 승점 부여 테스트
    @DisplayName("승점/패점 부여")
    @Test
    public void 승점_패점_부여() {
        int score = 10;
        for (Player player : players) {
            player.setScore(score--);
        }

        dealer.decideWinnerInRound();

        for (Player player : players) {
            if (player.getName().equals("test0")) {
                assertEquals(1, player.getTotalScore());
            } else {
                assertEquals(-1, player.getTotalScore());
            }
        }
    }


    // 최종 결과 발표 테스트
    @DisplayName("최종 결과 발표 테스트")
    @Test
    public void 최종_결과_발표_테스트() {
        int score = 1;
        for (Player player : players) {
            player.setScore(score++);
        }

        dealer.decideWinnerInRound();
        List<Player> result = dealer.decideWinner();
        result.stream()
                .forEach(System.out::print);
    }


}