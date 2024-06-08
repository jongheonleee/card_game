package main.cardGame.model;

import java.util.ArrayList;
import java.util.List;
import main.framework.Player;

// 게임 참여자
public class CardPlayer implements Player {

    private static final Integer MIN_LENGTH = 1;
    private static final Integer MAX_LENGTH = 20;

    private static final Integer MONEY = 10_000;


    private String name;
    private int money;

    private int score;

    private int win;
    private int loss;

    private List<Card> myDeck = new ArrayList<>();

    public CardPlayer(String name) {
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

    @Override
    public void takeDeck(List<Card> deck) {
        myDeck = deck;
    }

    @Override
    public List<Card> getMyDeck() {
        return myDeck;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public int getWin() {
        return win;
    }

    @Override
    public int getLoss() {
        return loss;
    }

    @Override
    public void increaseWin() {
        win++;
    }

    @Override
    public void increaseLoss() {
        loss++;
    }

    @Override
    public void takeAward(int award) {
        money += award;
    }

    @Override
    public int getTotalScore() {
        return win - loss;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int isWinner(int otherScore) {
        return getTotalScore() == otherScore ? 0 : (getTotalScore() >= otherScore ? 1 : -1);
    }

    @Override
    public int compareTo(Object o) {
        Player other = (Player) o;

        if (this.getTotalScore() > other.getTotalScore()) {
            return 1;
        } else if (this.getTotalScore() == other.getTotalScore()) {
            return this.getName().compareTo(other.getName());
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "[name='" + name + '\'' +
                ", money=" + money +
                ", score=" + score +
                ", win=" + win +
                ", loss=" + loss +
                ']';
    }
}
