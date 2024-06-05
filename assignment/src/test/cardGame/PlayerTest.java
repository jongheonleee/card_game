package test.cardGame;

import static org.junit.jupiter.api.Assertions.*;

import main.cardGame.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @DisplayName("객체 생성 성공")
    @Test
    public void 객체_생성_성공() {
        Player player = new Player("test player");
        assertTrue(player != null);
        assertTrue(player.getScore() == 0 && player.getLoss() == 0 && player.getWin() == 0);
        assertTrue(player.getMoney() == 10_000);
        assertEquals("test player", player.getName());
    }

    @DisplayName("객체 생성 실패, 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", "the name's length is over the maximum"})
    public void 객체_생성_실패(String wrongName) {
        assertThrows(IllegalArgumentException.class,
                () -> new Player(wrongName));
    }

    @DisplayName("상금 계산 기능 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100, 1_000, 10_000})
    public void 상금_계산(int amount) {
        Player player = new Player("test");
        int beforeMoney = player.getMoney();

        player.takeAward(amount);
        assertEquals(beforeMoney + amount, player.getMoney());
    }

    @DisplayName("승수/패수 증가 테스트")
    @Test
    public void 승_증가_패_증가() {
        Player player = new Player("test");
        int beforeWin = player.getWin();
        int beforeLoss = player.getLoss();

        player.increaseWin();
        player.increaseLoss();

        assertEquals(beforeLoss + 1, player.getLoss());
        assertEquals(beforeWin + 1, player.getWin());
        assertEquals(0, player.getScore());
    }

    @DisplayName("승자 판단")
    @Test
    public void 승자_판단() {
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");

        player1.increaseWin();
        player2.increaseLoss();

        assertTrue(player1.isWinner(player2.getScore()) == 1);

        player1.increaseLoss();
        player2.increaseWin();

        assertTrue(player1.isWinner(player2.getScore()) == 0);

        player1.increaseLoss();
        player2.increaseWin();

        assertTrue(player1.isWinner(player2.getScore()) == -1);

    }

}