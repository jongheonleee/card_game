package main.framework;

import java.util.List;
import main.cardGame.model.Card;

public interface Player extends Comparable {

    void takeDeck(List<Card> deck);

    List<Card> getMyDeck();

    String getName();

    int getMoney();

    int getWin();

    int getLoss();

    void increaseWin();

    void increaseLoss();

    void takeAward(int award);

    int getTotalScore();

    void setScore(int score);

    int getScore();

    int isWinner(int otherScore);
}
