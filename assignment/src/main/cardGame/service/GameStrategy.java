package main.cardGame.service;

import java.util.List;
import main.cardGame.model.Card;

public interface GameStrategy {
    boolean isRoyalStraightPlush(List<Card> list);
    boolean isBackStraightPlush(List<Card> list);

    boolean isStraightPlush(List<Card> list);

    boolean isFourCard(List<Card> list);

    boolean isFullHouse(List<Card> list);

    boolean isPlush(List<Card> list);

    boolean isMountain(List<Card> list);

    boolean isBackStraight(List<Card> list);

    boolean isStraight(List<Card> list);

    boolean isTriple(List<Card> list);

    boolean isTwoPair(List<Card> list);

    boolean isOnePair(List<Card> list);
}
