package main.cardGame.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import main.framework.GameComponent;
import main.framework.Player;

// 게임 진행자, 게임 룰을 인지하고 있고 이를 통해 게임을 진행함
public class Dealer implements GameComponent {

    public Dealer() {
    }

    // 각 플레이어에게 카드 배분


    // 각 플레이어의 점수 계산, 비교 대상, 비교 기준
    public void calculateScore(List<Player> players) {
        for (Player player : players) {
            if (isRoyalStraightPlush(player.getMyDeck())) {
                player.setScore(13);
            }
            else if (isBackStraightPlush(player.getMyDeck())) {
                player.setScore(12);
            }
            else if (isStraightPlush(player.getMyDeck())) {
                player.setScore(11);
            }
            else if (isFourCard(player.getMyDeck())) {
                player.setScore(10);
            }
            else if (isFullHouse(player.getMyDeck())) {
                player.setScore(9);
            }
            else if (isPlush(player.getMyDeck())) {
                player.setScore(8);
            }
            else if (isMountain(player.getMyDeck())) {
                player.setScore(7);
            }
            else if (isBackStraight(player.getMyDeck())) {
                player.setScore(6);
            }
            else if (isStraight(player.getMyDeck())) {
                player.setScore(5);
            }
            else if (isTriple(player.getMyDeck())) {
                player.setScore(4);
            }
            else if (isTwoPair(player.getMyDeck())) {
                player.setScore(3);
            }
            else if (isOnePair(player.getMyDeck())) {
                player.setScore(2);
            }
            else {
                player.setScore(1);
            }
        }
    }

    private boolean isRoyalStraightPlush(List<Card> cards) {
        Card mark = cards.get(0);
        if (mark.getNumber() != 10) return false;

        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getShape().equals(cards.get(i).getShape()))) return false;
            if (!(mark.getNumber() + i == cards.get(i).getNumber())) return false;
        }

        return true;
    }

    private boolean isBackStraightPlush(List<Card> cards) {
        Card mark = cards.get(0);
        if (mark.getNumber() != 14) return false;

        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getShape().equals(cards.get(i).getShape()))) return false;
            if (!(1 + i == cards.get(i).getNumber())) return false;
        }

        return true;
    }

    private boolean isStraightPlush(List<Card> cards) {
        Card mark = cards.get(0);

        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getShape().equals(cards.get(i).getShape()))) return false;
            if (!(mark.getNumber() + i == cards.get(i).getNumber())) return false;
        }


        return true;
    }

    private boolean isFourCard(List<Card> cards) {
        for (Card mark : cards) {
            int target = mark.getNumber();
            if (cards.stream()
                    .filter(card -> !mark.equals(card))
                    .filter(card -> card.getNumber() == target)
                    .count() + 1 == 4)
                return true;
        }

        return false;
    }

    private boolean isFullHouse(List<Card> cards) {
        // 서로 숫자가 다른 두 카드 선정
            // 같은 숫자가 3개인지
            // 같은 숫자가 2개인지
        boolean check = true;

        for (Card mark1 : cards) {
            for (Card mark2 : cards) {
                if (mark1.equals(mark2) || mark1.getNumber() == mark2.getNumber()) continue;

                // mark1 : 3, mark2 : 2
                if (!(cards.stream()
                        .filter(card -> !card.equals(mark1))
                        .filter(card -> mark1.getNumber() == card.getNumber())
                        .count() + 1 == 3)) {
                    check = false;
                }

                if (!(cards.stream()
                        .filter(card -> !card.equals(mark2))
                        .filter(card -> mark2.getNumber() == card.getNumber())
                        .count() + 1 == 2)) {
                    check = false;
                }

                if (check) return true;

                // mark1 : 2, mark2 : 3
                if (!(cards.stream()
                        .filter(card -> !card.equals(mark2))
                        .filter(card -> mark2.getNumber() == card.getNumber())
                        .count() + 1 == 3)) {
                    check = false;
                }

                if (!(cards.stream()
                        .filter(card -> !card.equals(mark1))
                        .filter(card -> mark1.getNumber() == card.getNumber())
                        .count() + 1 == 2)) {
                    check = false;
                }

                if (check) return true;

            }
        }

        return check;
    }

    private boolean isPlush(List<Card> cards) {
        Card mark = cards.get(0);
        return cards.stream()
                .allMatch(card -> card.isSameShape(mark.getShape()));
    }

    private boolean isMountain(List<Card> cards) {
        Card mark = cards.get(0);

        if (mark.getNumber() != 10)
            return false;

        boolean isNotAllSameShape = !(cards.stream()
                .allMatch(card -> card.isSameShape(mark.getShape())));

        boolean isNumberInOrder = true;
        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getNumber() + i == cards.get(i).getNumber()))
                isNumberInOrder = false;
        }
        return isNotAllSameShape && isNumberInOrder;
    }

    private boolean isBackStraight(List<Card> cards) {
        Card mark = cards.get(0);

        if (mark.getNumber() != 14)
            return false;

        boolean isNotAllSameShape = !(cards.stream()
                .allMatch(card -> card.isSameShape(mark.getShape())));

        boolean isNumberInOrder = true;
        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getNumber() - 13 + i == cards.get(i).getNumber()))
                isNumberInOrder = false;
        }
        return isNotAllSameShape && isNumberInOrder;
    }

    private boolean isStraight(List<Card> cards) {
        Card mark = cards.get(0);

        boolean isNotAllSameShape = !(cards.stream()
                .allMatch(card -> card.isSameShape(mark.getShape())));

        boolean isNumberInOrder = true;
        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getNumber() + i == cards.get(i).getNumber()))
                isNumberInOrder = false;
        }

        return isNotAllSameShape && isNumberInOrder;
    }

    private boolean isTriple(List<Card> cards) {
        for (Card mark : cards) {
            if (cards.stream()
                    .filter(card -> !card.equals(mark))
                    .filter(card -> card.getNumber() == mark.getNumber())
                    .count() + 1 == 3)
                return true;
        }

        return false;
    }

    private boolean isTwoPair(List<Card> cards) {
        for (Card mark1 : cards) {
            for (Card mark2 : cards) {
                if (mark1.getNumber() == mark2.getNumber()) continue;

                if (!cards.stream()
                        .filter(card -> !card.equals(mark1))
                        .anyMatch(card -> card.getNumber() == mark1.getNumber())) {
                    break;
                }

                if (!cards.stream()
                        .filter(card -> !card.equals(mark2))
                        .anyMatch(card -> card.getNumber() == mark2.getNumber())) {
                    break;
                }

                return true;
            }
        }

        return false;
    }

    private boolean isOnePair(List<Card> cards) {
        for (Card mark : cards) {
            if (cards.stream()
                    .filter(card -> !card.equals(mark))
                    .filter(card -> card.getNumber() == mark.getNumber())
                    .count() == 1) {
                return true;
            }
        }

        return false;
    }

}
