package main.cardGame.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.framework.GameComponent;

// 카드 관리 및 묶음
public class Deck implements GameComponent {

    private final static List<String> patterns = Arrays.asList("SPADE", "DIAMOND", "HEART", "CLOVA");
    private static final Integer MIN_NUMBER = 2;
    private static final Integer MAX_NUMBER = 14;

    private static final Integer MIN_PLAYER_COUNT = 2;
    private static final Integer MAX_PLAYER_COUNT = 4;
    private static final Integer TOTAL_COUNT = 52;
    private static final Integer SPLIT_COUNT = 5;

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        create();
    }

    private void create() {
        // 52개 카드 생성
        // 각 문양당 밑에 범위만큼 카드 생성
        // A(14) > K(13) > Q(12) > J(11) > 10~2
        for (int i=1; i<=52; i++) {
            cards.add(new Card(i));
        }
    }

    public void shuffle() {
        // 총 100번 반복
            // 랜덤 인덱스 선정
            // 인덱스 0과 위치 바꾸기
        for (int i=0; i<100; i++) {
            int j = (int)(Math.random() * TOTAL_COUNT);
            Card tmp = cards.get(0);
            cards.set(0, cards.get(j));
            cards.set(j, tmp);
        }
    }

    public List<List<Card>> splitCards(int amount) {
        // 섞음
        // 각 플레이어에게 할당할 덱 생성
        // 반환
        if (!isValidAmount(amount)) {
            throw new IllegalArgumentException("잘못된 플레이어 수입니다.");
        }

        shuffle();

        List<List<Card>> split = new ArrayList<>();
        for (int i=0; i<amount; i++) {
            int start = i * SPLIT_COUNT;
            int end = start + SPLIT_COUNT;
            split.add(cards.subList(start, end));
        }

        return split;
    }

    private boolean isValidAmount(int amount) {
        return (MIN_PLAYER_COUNT <= amount && amount <= MAX_PLAYER_COUNT);
    }

    public List<Card> getCards() {
        return cards;
    }
}
