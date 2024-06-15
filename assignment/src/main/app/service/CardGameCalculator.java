package main.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.app.model.Card;
import main.app.model.Ranking;

public class CardGameCalculator implements Calculable {

    private final Map<Integer, Integer> numberMap = new HashMap<>();
    private final Map<Integer, Integer> kindMap = new HashMap<>();
    public CardGameCalculator() {
    }

    @Override
    public int calculate(List<Card> cards, Ranking ranking) {
        clear();

        // 계산 지표
        boolean isAllSameKind = true;
        boolean isAllDifferentKind = true;

        boolean isNumberInRow = true;
        boolean isNumberInReverseRow = true;

        boolean isFirstNumber10 = true;
        boolean isFirstNumberA = true;

        boolean isSameNumber3N2 = false;
        boolean isSameNumber4 = false;

        boolean isTwoPair = true;
        boolean isOnePair = true;


        for (Card card : cards) {
            // 숫자 카운트
            if (numberMap.containsKey(card.getNumber())) {
                numberMap.put(card.getNumber(), numberMap.get(card.getNumber()) + 1);
            } else {
                numberMap.put(card.getNumber(), 1);
            }

            // 무늬 카운트
            if (kindMap.containsKey(card.getKind())) {
                kindMap.put(card.getKind(), kindMap.get(card.getKind()) + 1);
            } else {
                kindMap.put(card.getKind(), 1);
            }
        }

        int numberOfKind = kindMap.size();
        int numberOfNumber = numberMap.size();


        List<Integer> counts = numberMap
                .values()
                .stream()
                .toList();

        if (numberOfKind != 1) {
            isAllSameKind = false;
        } else if (numberOfKind != 4) {
            isAllDifferentKind = false;
        }

        Card firstCard = cards.get(0);
        int firstNumber = firstCard.getNumber();
        if (firstNumber != 10) isFirstNumber10 = false;
        if (firstNumber != 14) isFirstNumberA = false;

        int mark = firstNumber;
        for (Card card : cards) {
            if (!(mark == card.getNumber())) {
                isNumberInRow = false;
                break;
            }
            mark++;
        }

        int mark2 = 15;
        if (isFirstNumberA) {
            for (int i=1; i<cards.size(); i++) {
                Card card = cards.get(i);

                if (mark2%13 != (card.getNumber() + 13)%13) {
                    isNumberInReverseRow = false;
                    break;
                }
            }
        }


        if (numberOfNumber == 2) {
            isSameNumber4 = counts.contains(4);
            isSameNumber3N2 = counts.contains(2) && counts.contains(3);
        }


        return ranking.getScore(isAllSameKind, isAllDifferentKind, isNumberInRow,
                              isNumberInReverseRow, isFirstNumber10, isFirstNumberA,
                              isSameNumber4, isSameNumber3N2, isTwoPair, isOnePair);
    }

    private void clear() {
        numberMap.clear();
        kindMap.clear();
    }
}
