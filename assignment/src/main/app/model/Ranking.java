package main.app.model;

public class Ranking {
    private enum Hand {
        LOYAL_STRAIGHT_PLUSH(13),
        BACK_STRAIGHT_PLUSH(12),
        STRAIGHT_PLUSH(11),
        FOUR_CARD(10),
        PULL_HOUSE(9),
        PLUSH(8),
        MOUNTAIN(7),
        BACK_STRAIGHT(6),
        STRAIGHT(5),
        TRIPLE(4),
        TWO_PAIR(3),
        ONE_PAIR(2),
        NO_PAIR(1);

        private final int score;

        Hand(int score) {
            this.score = score;
        }
    }

    public static int getScore(boolean isAllSameKind, boolean isAllDifferentKind, boolean isNumberInRow,
            boolean isNumberInReverseRow, boolean isFirstNumber10, boolean isFirstNumberA,
            boolean isSameNumber4, boolean isSameNumber3N2, boolean isTwoPair, boolean isOnePair) {
        if (isAllSameKind && isNumberInRow && isFirstNumber10) {
            return Hand.LOYAL_STRAIGHT_PLUSH.score;
        } else if (isAllSameKind && isNumberInReverseRow && isFirstNumberA) {
            return Hand.BACK_STRAIGHT_PLUSH.score;
        } else if (isAllSameKind && isNumberInRow) {
            return Hand.STRAIGHT_PLUSH.score;
        } else if (isSameNumber4) {
            return Hand.FOUR_CARD.score;
        } else if (isSameNumber3N2) {
            return Hand.PULL_HOUSE.score;
        } else if (isAllSameKind) {
            return Hand.MOUNTAIN.score;
        } else if (isAllDifferentKind && isNumberInRow) {
            return Hand.BACK_STRAIGHT.score;
        } else if (isAllDifferentKind && isNumberInReverseRow) {
            return Hand.STRAIGHT.score;
        } else if (isAllDifferentKind && isNumberInRow) {
            return Hand.TRIPLE.score;
        } else if (isTwoPair) {
            return Hand.TWO_PAIR.score;
        } else if (isOnePair) {
            return Hand.ONE_PAIR.score;
        } else {
            return Hand.NO_PAIR.score;
        }
    }
}
