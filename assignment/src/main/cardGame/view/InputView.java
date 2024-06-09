package main.cardGame.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Integer MIN_ROUND_COUNT = 1;
    private static final Integer MAX_ROUND_COUNT = 10_000;

    private static final Integer MIN_AWARD = 1;
    private static final Integer MAX_AWARD = 100_000;
    private static final String WRONG_ROUND_COUNT = "잘못된 게임 라운드 수입니다. 허용 가능한 라운드 수는 1~10,000입니다. 다시 입력해주세요";


    private static final Integer MIN_NUMBER_OF_PLAYER = 2;
    private static final Integer MAX_NUMBER_OF_PLAYER = 4;

    private static final String WRONG_NUMBER_OF_PLAYER = "잘못된 플레이어 수 입니다. 허용 가능한 플레이어 수는 2~4명입니다. 다시 입력해주세요";

    private static final String WRONG_AWARD = "잘못된 승점 부여입니다. 허용 가능한 범위는 1~100,000입니다.";
    private static final String GAME_START_MESSAGE = "========[포커 게임]========\n";
    private static final String ASK_NUMBER_OF_PLAYER = "플레이어 수를 입력해주세요(허용범위 : 2~4) > ";
    private static final String ASK_PLAYER_NAME = "플레이어의 닉네임을 입력해주세요(허용길이 : 1~20) > ";
    private static final String ASK_NUMBER_OF_ROUNDS = "플레이 횟수를 지정해주세요(허용범위 : 2이상) > ";
    private static final String ASK_AWARD = "부여할 승점을 입력하세요!(허용 범위 : 1~100,000) > ";
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

        try {
            String input = getInput();
            int numberOfPlayer = parseInt(input);

            if (!(MIN_NUMBER_OF_PLAYER <= numberOfPlayer && numberOfPlayer <= MAX_NUMBER_OF_PLAYER)) {
                throw new IllegalArgumentException(WRONG_NUMBER_OF_PLAYER);
            }

            return numberOfPlayer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNumberOfPlayer();
        }
    }

    public String getPlayerName() {
        System.out.println(ASK_PLAYER_NAME);
        return getInput();
    }

    public int getNumberOfRounds() {
        System.out.println(ASK_NUMBER_OF_ROUNDS);

        try {
            String input = getInput();
            int numberOfRound = parseInt(input);
            if (!(MIN_ROUND_COUNT <= numberOfRound && numberOfRound <= MAX_ROUND_COUNT)) {
                throw new IllegalArgumentException(WRONG_ROUND_COUNT);
            }

            return numberOfRound;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNumberOfRounds();
        }
    }

    public int getAward() {
        System.out.println(ASK_AWARD);
        try {
            String input = getInput();
            int award = parseInt(input);
            if (!(MIN_AWARD <= award && award <= MAX_AWARD)) {
                throw new IllegalArgumentException(WRONG_AWARD);
            }

            return award;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getAward();
        }

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
            throw new IllegalArgumentException(WRONG_INPUT);
        }
    }

    public static InputView getInstance() {
        return singleton;
    }

}
