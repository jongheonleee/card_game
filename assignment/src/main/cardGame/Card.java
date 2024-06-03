package main.cardGame;

import java.util.*;


// 카드 정보 저장
public class Card {

    private final static List<String> patterns = Arrays.asList("SPADE", "DIAMOND", "HEART", "CLOVA");
    private static final Integer MIN_NUMBER = 2;
    private static final Integer MAX_NUMBER = 14;

    private String shape;
    private Integer number;

    public Card(String shape, Integer number) {
        valid(shape, number);
        this.shape = shape;
        this.number = number;
    }


    private void valid(String shape, Integer number) {
        if (!isValidNumber(number)) {
            throw new IllegalArgumentException("잘못된 숫자입니다.");
        }

        if (!isValidShape(shape)) {
            throw new IllegalArgumentException("잘못된 무늬입니다.");
        }
    }

    private boolean isValidShape(String shape) {
        return patterns.contains(shape);
    }

    private boolean isValidNumber(Integer number) {
        return (MIN_NUMBER <= number && number <= MAX_NUMBER);
    }

    // 다른 무늬와 같은지 다른지 판반, boolean
    public boolean isSameShape(String other) {
        return shape.equals(other);
    }

    // 다른 숫자와 비교, 같으면 0, 해당 숫자가 크면 1, 작으면 -1
    public int compareNumber(Integer other) {
        return number == other ? 0 : (number >= other ? 1 : -1);
    }

    public Integer getNumber() {
        return number;
    }

    public String getShape() {
        return shape;
    }

}
