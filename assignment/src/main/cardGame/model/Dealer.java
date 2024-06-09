package main.cardGame.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import main.cardGame.service.GameStrategy;
import main.framework.GameComponent;
import main.framework.Player;

// 게임 진행자, 게임 룰을 인지하고 있고 이를 통해 게임을 진행함
public class Dealer implements GameComponent {

    private final GameStrategy strategy;

    public Dealer(GameStrategy strategy) {
        this.strategy = strategy;
    }

    // 각 플레이어에게 카드 배분


    // 각 플레이어의 점수 계산, 비교 대상, 비교 기준
    public void calculateScore(List<Player> players) {
        for (Player player : players) {
            if (strategy.isRoyalStraightPlush(player.getMyDeck())) {
                player.setScore(13);
            }
            else if (strategy.isBackStraightPlush(player.getMyDeck())) {
                player.setScore(12);
            }
            else if (strategy.isStraightPlush(player.getMyDeck())) {
                player.setScore(11);
            }
            else if (strategy.isFourCard(player.getMyDeck())) {
                player.setScore(10);
            }
            else if (strategy.isFullHouse(player.getMyDeck())) {
                player.setScore(9);
            }
            else if (strategy.isPlush(player.getMyDeck())) {
                player.setScore(8);
            }
            else if (strategy.isMountain(player.getMyDeck())) {
                player.setScore(7);
            }
            else if (strategy.isBackStraight(player.getMyDeck())) {
                player.setScore(6);
            }
            else if (strategy.isStraight(player.getMyDeck())) {
                player.setScore(5);
            }
            else if (strategy.isTriple(player.getMyDeck())) {
                player.setScore(4);
            }
            else if (strategy.isTwoPair(player.getMyDeck())) {
                player.setScore(3);
            }
            else if (strategy.isOnePair(player.getMyDeck())) {
                player.setScore(2);
            }
            else {
                player.setScore(1);
            }
        }
    }

}
