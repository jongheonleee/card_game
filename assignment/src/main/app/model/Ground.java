package main.app.model;

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
        int maxScore = players.stream()
                .mapToInt(player -> player.getScore())
                .max()
                .getAsInt();

        players.stream()
                .filter(player -> player.getScore() == maxScore)
                .forEach(player -> {
                    player.increaseWin();
                    player.takeAward(award);
                });

        players.stream()
                .filter(player -> player.getScore() != maxScore)
                .forEach(player -> player.increaseLoss());

        List<Player> winners = players.stream()
                .filter(player -> player.getScore() == maxScore)
                .collect(Collectors.toList());

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
