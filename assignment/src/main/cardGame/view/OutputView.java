package main.cardGame.view;

import java.util.List;
import main.cardGame.model.Player;

public class OutputView {

    private static final String CURRENT_ROUND_MESSAGE = "\n======== 현재(%d) 라운드 승자 ========\n";
    private static final String FINAL_ROUND_MESSAGE = "\n======== 최종 우승자 ========\n";
    private static final String WINNER_MESSAGE = "\n이름 : %s, 획득 점수 : %d\n";

    private static final String LINE_BREAK = "\n=============================\n";
    private static final OutputView singleton = new OutputView();

    private OutputView() {}

    public void showRoundWinner(int currRound, List<Player> winners) {
        System.out.println(String.format(CURRENT_ROUND_MESSAGE, currRound));
        winners.stream().forEach(winner -> System.out.println(String.format(WINNER_MESSAGE, winner.getName(), winner.getScore())));
        System.out.println(LINE_BREAK);
    }

    public void showFinalWinner(List<Player> winners) {
        System.out.println(FINAL_ROUND_MESSAGE);
        winners.stream().forEach(winner -> System.out.println(String.format(WINNER_MESSAGE, winner.getName(), winner.getTotalScore())));
        System.out.println(LINE_BREAK);
    }

    public static OutputView getInstance() {
        return singleton;
    }

}
