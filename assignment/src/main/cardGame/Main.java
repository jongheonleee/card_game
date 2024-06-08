package main.cardGame;

import main.cardGame.controller.GameController;
import main.cardGame.view.InputView;
import main.cardGame.view.OutputView;

public class Main {

    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();

        GameController controller = new GameController(inputView, outputView);
        int numberOfPlayer = controller.askNumberOfPlayer();
        int numberOfRound = controller.askNumberOfRound();

        // 팩토리를 통해서 players, dealer, deck 생성해서 받기
        controller.play(numberOfPlayer, numberOfRound);
        controller.end();
    }
}