package main.cardGame.service;

import java.util.List;
import main.cardGame.model.Card;

public class CardGameStrategy implements GameStrategy {

    @Override
    public boolean isRoyalStraightPlush(List<Card> cards) {
        Card mark = cards.get(0);
        if (mark.getNumber() != 10) return false;

        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getShape().equals(cards.get(i).getShape()))) return false;
            if (!(mark.getNumber() + i == cards.get(i).getNumber())) return false;
        }

        return true;
    }

    @Override
    public boolean isBackStraightPlush(List<Card> cards) {
        Card mark = cards.get(0);
        if (mark.getNumber() != 14) return false;

        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getShape().equals(cards.get(i).getShape()))) return false;
            if (!(1 + i == cards.get(i).getNumber())) return false;
        }

        return true;
    }

    @Override
    public boolean isStraightPlush(List<Card> cards) {
        Card mark = cards.get(0);

        for (int i=1; i<cards.size(); i++) {
            if (!(mark.getShape().equals(cards.get(i).getShape()))) return false;
            if (!(mark.getNumber() + i == cards.get(i).getNumber())) return false;
        }


        return true;
    }

    @Override
    public boolean isFourCard(List<Card> cards) {
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

    @Override
    public boolean isFullHouse(List<Card> cards) {
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

    @Override
    public boolean isPlush(List<Card> cards) {
        Card mark = cards.get(0);
        return cards.stream()
                .allMatch(card -> card.isSameShape(mark.getShape()));

    }

    @Override
    public boolean isMountain(List<Card> cards) {
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

    @Override
    public boolean isBackStraight(List<Card> cards) {
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

    @Override
    public boolean isStraight(List<Card> cards) {
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

    @Override
    public boolean isTriple(List<Card> cards) {
        for (Card mark : cards) {
            if (cards.stream()
                    .filter(card -> !card.equals(mark))
                    .filter(card -> card.getNumber() == mark.getNumber())
                    .count() + 1 == 3)
                return true;
        }

        return false;
    }

    @Override
    public boolean isTwoPair(List<Card> cards) {
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

    @Override
    public boolean isOnePair(List<Card> cards) {
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
