package main.app.model;


// 카드 정보 저장
public class Card {
    private static final Integer EACH_NUMBER = 13;
    private static final Integer EACH_KIND = 4;

    private static final Integer MIN_NUMBER = 1;
    private static final Integer MAX_NUMBER = 52;
    private final Integer number;

    public Card(Integer number) {
        valid(number);
        this.number = number;
    }

    private void valid(Integer number) {
        if (!isValidNumber(number)) {
            throw new IllegalArgumentException("잘못된 숫자입니다.");
        }
    }

    private boolean isValidNumber(Integer number) {
        return (MIN_NUMBER <= number && number <= MAX_NUMBER);
    }

    public Integer getNumber() {
        return (number % EACH_KIND) + 1;
    }

    public Integer getKind() {
        return (number % EACH_NUMBER);
    }

    public boolean isSameNumber(Integer number) {
        return getNumber() == number;
    }

    public boolean isSameKind(Integer kind) {
        return getKind() == kind;
    }
}
