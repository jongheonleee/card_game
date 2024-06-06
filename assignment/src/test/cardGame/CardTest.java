package test.cardGame;

import static org.junit.jupiter.api.Assertions.*;

import main.cardGame.model.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CardTest {

    // 객체가 성공적으로 생성
    @DisplayName("객체가 성공적으로 생성")
    @Test
    public void 객체_생성_성공() {
        // given
        Integer num = 5;
        String shape = "SPADE";

        // when
        Card card = new Card(shape, num);

        // then
        assertEquals(shape, card.getShape());
        assertEquals(num, card.getNumber());

    }

    // 객체가 비정상적으로 생성, 예외 발생
    @DisplayName("객체가 비정상적으로 생성")
    @Test
    public void 객체_생성_실패() {
        // given : 숫자가 잘못된 경우, 무늬가 잘못된 경우
        Integer wrongNum = -1;
        Integer num = 5;
        String wrongShape = "Boom";
        String shape = "SPADE";


        // when : 숫자가 잘못됨
        // then
        assertThrows(IllegalArgumentException.class,
                () -> new Card(shape, wrongNum));



        // when : 무늬가 잘못됨
        // then
        assertThrows(IllegalArgumentException.class,
                () -> new Card(wrongShape, num));
    }

    // 걑은 무늬인지 판단
    @DisplayName("같은 무늬인 경우")
    @Test
    public void 같은_무늬_비교() {
        Card c1 = new Card("SPADE", 5);
        Card c2 = new Card("SPADE", 7);
        assertTrue(c1.isSameShape(c2.getShape()));
    }

    @DisplayName("다른 무늬인 경우")
    @Test
    public void 다른_무늬_비교() {
        Card c1 = new Card("SPADE", 5);
        Card c2 = new Card("HEART", 7);
        assertFalse(c1.isSameShape(c2.getShape()));
    }

    // 숫자 비교
    @DisplayName("같은 숫자 경우")
    @Test
    public void 같은_숫자_비교() {
        Card c1 = new Card("SPADE", 5);
        Card c2 = new Card("HEART", 5);
        assertEquals(0, c1.compareNumber(c2.getNumber()));
    }

    @DisplayName("같은 무늬인 경우")
    @Test
    public void 다른_숫자_비교() {
        // c1이 큰경우
        Card c1 = new Card("SPADE", 8);
        Card c2 = new Card("HEART", 5);
        assertEquals(1, c1.compareNumber(c2.getNumber()));


        // c2이 큰경우
        c1 = new Card("SPADE", 5);
        c2 = new Card("HEART", 8);
        assertEquals(-1, c1.compareNumber(c2.getNumber()));

    }


}