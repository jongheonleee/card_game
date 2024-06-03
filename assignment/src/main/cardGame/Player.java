package main.cardGame;

import java.util.ArrayList;
import java.util.List;

// 게임 참여자
public class Player {

    private static final Integer MIN_LENGTH = 1;
    private static final Integer MAX_LENGTH = 20;

    private static final Integer MONEY = 10_000;


    private String name;
    private int money;

    private int win;
    private int loss;

    private List<Card> myDeck = new ArrayList<>();

    public Player(String name) {
        valid(name);
        this.name = name;
        this.money = MONEY;
        this.win = 0;
        this.loss = 0;
    }

    private void valid(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("잘못된 닉네임입니다.");
        }
    }

    private boolean isValidName(String name) {
        return (name != null && MIN_LENGTH <= name.length() && name.length() <= MAX_LENGTH);
    }

    public void takeDeck(List<Card> deck) {
        myDeck.clear();
        myDeck = deck;
    }

    public List<Card> getMyDeck() {
        return myDeck;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getWin() {
        return win;
    }

    public int getLoss() {
        return loss;
    }

    public void increaseWin() {
        win++;
    }

    public void increaseLoss() {
        loss++;
    }

    public void takeAward(int award) {
        money += award;
    }

    public int getScore() {
        return win - loss;
    }

    public int isWinner(int otherScore) {
        return getScore() == otherScore ? 0 : (getScore() >= otherScore ? 1 : -1);
    }
}
