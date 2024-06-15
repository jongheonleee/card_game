package main.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import main.framework.domain.AppComponent;

public class Ground implements AppComponent {

    private final List<Player> players;

    public Ground(List<Player> players) {
        this.players = players;
    }

    // 각 플레이어에게 카드 배분
    public void giveOutCards(List<List<Card>> splitCards) {
        for (int i=0; i< players.size(); i++) {
            List<Card> cards = splitCards.get(i);
            Player player = players.get(i);
            player.takeDeck(cards);
        }
    }

    // 승자 발표, 승점 패점 부여
    public List<Player> decideWinnerInRound(int award) {
        List<Player> winners = new ArrayList<>();

        int maxScore = players.stream()
                .mapToInt(player -> player.getScore())
                .max()
                .getAsInt();

        for (Player player : players) {
            if (player.getScore() == maxScore) {
                player.increaseWin();
                player.takeAward(award);
                winners.add(player);
            } else {
                player.increaseLoss();
            }
        }

        return winners;
    }


    // 최종 결과 발표
    public List<Player> decideWinner() {
        return players.stream()
                .sorted(Collections.reverseOrder())
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }



}
