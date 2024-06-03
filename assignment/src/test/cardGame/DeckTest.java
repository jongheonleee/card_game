package test.cardGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import main.cardGame.Card;
import main.cardGame.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {


    // 덱 성공적으로 생성
    @DisplayName("덱 생성")
    @Test
    public void 덱_생성() {
        Deck deck = new Deck();

        assertEquals(52, deck.getCards().size());
        deck.getCards().stream().forEach(e -> {
            System.out.println("Card 정보 : " + e.getShape() + " " + e.getNumber());
        });
    }

    // 덱 섞기
    @DisplayName("덱 섞기")
    @Test
    public void 덱_섞기() {
        Deck deck = new Deck();
        deck.getCards().stream().forEach(e -> {
            System.out.println("Card 정보 : " + e.getShape() + " " + e.getNumber());
        });

        deck.shuffle();

        deck.getCards().stream().forEach(e -> {
            System.out.println("Card 정보 : " + e.getShape() + " " + e.getNumber());
        });
    }

    // 덱에서 카드 배분하기

    @DisplayName("카드 나누기")
    @Test
    public void 플레이어_패_배분() {
        Deck deck = new Deck();
        List<List<Card>> result = deck.splitCards(4);

        assertEquals(4, result.size());
        for (List<Card> cards : result) {
            System.out.println("============================");
            cards.stream().forEach(e -> {
                System.out.println("플레이어의 카드 정보 : " + e.getShape() + " " + e.getNumber());
            });
            System.out.println("============================");
        }
    }

    // 덱에서 카드 배분 실패, amount값이 허용된 값이 아님
    @DisplayName("카드 나누기 실패")
    @Test
    public void 플레이어_패_배분_실패() {
        Deck deck = new Deck();
        assertThrows(IllegalArgumentException.class,
                () -> deck.splitCards(10));
    }
}