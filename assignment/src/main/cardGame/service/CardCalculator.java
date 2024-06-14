package main.cardGame.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import main.cardGame.model.Card;
import main.cardGame.model.CardPlayer;

public class CardCalculator implements Calculable {

    private final Map<Integer, Integer> numberMap = new HashMap<>();
    private final Map<Integer, Integer> kindMap = new HashMap<>();
    public CardCalculator() {
    }

    @Override
    public int calculate(List<Card> cards) {
        clear();

        // 계산 지표
        boolean isAllSameKind = true;
        boolean isAllDifferentKind = true;

        boolean isNumberInRow = true;
        boolean isNumberInReverseRow = true;

        boolean isFirstNumber10 = true;
        boolean isFirstNumberA = true;


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

        // 무늬가 몇개가 같은지
        // 숫자가 몇개가 같은지
        int numberOfKind = kindMap.size();
        int numberOfNumber = numberMap.size();


        List<Integer> counts = numberMap
                .values()
                .stream()
                .toList();

        // 모두 같은 무늬,  모두 다른 무늬
        if (numberOfKind != 1) {
            isAllSameKind = false;
        } else if (numberOfKind != 4) {
            isAllDifferentKind = false;
        }


        // 10~14 연속
        // 14 - 2~5 연속
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

        if (isAllSameKind && isNumberInRow && isFirstNumber10) {
            return 13;
        }

        if (isAllSameKind && isNumberInReverseRow && isFirstNumberA) {
            return 12;
        }

        if (isAllSameKind && isNumberInRow) {
            return 11;
        }


        // 같은 숫자 4개
        // 같은 숫자 3/2개
        if (numberOfNumber == 2) {
            boolean isSameNumber4 = counts.contains(4);
            if (isSameNumber4) {
                return 10;
            }

            boolean isSameNumber3N2 = counts.contains(2) && counts.contains(3);
            if (isSameNumber3N2) {
                return 9;
            }
        }

        if (isAllSameKind) {
            return 8;
        }

        if (isAllDifferentKind && isNumberInRow) {
            return 7;
        }

        if (isAllDifferentKind && isNumberInReverseRow) {
            return 6;
        }

        if (isAllDifferentKind && isNumberInRow) {
            return 5;
        }

        if (numberOfNumber == 2) {
            if (counts.contains(3) && !counts.contains(2)) {
                return 4;
            }

            if (counts.contains(2) && counts.contains(2)) {
                return 3;
            }
        }

        if (counts.contains(2)) {
            return 2;
        }

        return 1;
    }

    private void clear() {
        numberMap.clear();
        kindMap.clear();
    }
}
