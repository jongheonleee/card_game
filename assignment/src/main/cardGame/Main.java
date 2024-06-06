package main.cardGame;

import main.cardGame.controller.GameController;

public class Main {

    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.ready();
        controller.play();
        controller.end();
    }
}