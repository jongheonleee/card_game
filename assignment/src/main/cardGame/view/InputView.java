package main.cardGame.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final String GAME_START_MESSAGE = "========[포커 게임]========\n";
    private static final String ASK_NUMBER_OF_PLAYER = "플레이어 수를 입력해주세요(최소 2~4) > ";
    private static final String ASK_PLAYER_NAME = "플레이어의 닉네임을 입력해주세요(글자수 1~20) > ";
    private static final String ASK_NUMBER_OF_ROUNDS = "플레이 횟수를 지정해주세요(최소 1) > ";
    private static final String WRONG_INPUT = "잘못된 입력값입니다. 입력 형식을 다시 한번 확인해주세요";
    private static final Pattern pattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
    private static final InputView singleton = new InputView(new Scanner(System.in));

    private final Scanner sc;

    private InputView(Scanner sc) {
        this.sc = sc;
    }

    public int getNumberOfPlayer() {
        System.out.println(GAME_START_MESSAGE);
        System.out.println(ASK_NUMBER_OF_PLAYER);
        String input = getInput();
        return parseInt(input);
    }

    public String getPlayerName() {
        System.out.println(ASK_PLAYER_NAME);
        return getInput();
    }

    public int getNumberOfRounds() {
        System.out.println(ASK_NUMBER_OF_ROUNDS);
        String input = getInput();
        return parseInt(input);
    }

    private String getInput() {
        String input = sc.nextLine();

        if (!isValidInput(input)) {
            throw new IllegalArgumentException(WRONG_INPUT);
        }

        return input;

    }

    private boolean isValidInput(String input) {
        // 공백
        if (isBlank(input)) return false;
        // 스페이스
        if (isSpace(input)) return false;
        // 특수문자
        if (isSpecialToken(input)) return false;

        return true;
    }

    private boolean isBlank(String input) {
        return input == null || input.isBlank();
    }

    private boolean isSpace(String input) {
        return input.contains(" ");
    }

    private boolean isSpecialToken(String input) {
        return pattern.matcher(input).find();
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력 형식입니다. 숫자를 입력해주세요!");
        }
    }

    public static InputView getInstance() {
        return singleton;
    }

}
