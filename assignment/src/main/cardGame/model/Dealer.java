package main.cardGame.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import main.cardGame.service.Calculable;
import main.framework.GameComponent;
import main.framework.Player;

// 게임 진행자, 게임 룰을 인지하고 있고 이를 통해 게임을 진행함
public class Dealer implements GameComponent {

    private final Calculable strategy;

    public Dealer(Calculable strategy) {
        this.strategy = strategy;
    }


    // 각 플레이어의 점수 계산, 비교 대상, 비교 기준
    public void calculateScore(List<Player> players) {
        for (Player player : players) {
            int score = strategy.calculate(player.getMyDeck());
            player.setScore(score);
        }
    }

}
