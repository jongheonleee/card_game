package main.cardGame;

import main.cardGame.controller.GameController;
import main.cardGame.view.InputView;

public class Main {

    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        GameController controller = new GameController(inputView);
        int numberOfPlayer = controller.askNumberOfPlayer();
        int numberOfRounds = controller.askNumberOfRounds();
        controller.play(numberOfPlayer, numberOfRounds);
        controller.end();
    }
}