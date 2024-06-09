package main.cardGame;

import main.cardGame.controller.GameController;
import main.cardGame.factory.CardGamePlayerFactory;
import main.cardGame.factory.GameComponentFactory;
import main.cardGame.view.InputView;
import main.cardGame.view.OutputView;

public class Main {

    public static void main(String[] args) {
        // 애플리케이션에 필요한 객체를 생성
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();
        CardGamePlayerFactory playerFactory = CardGamePlayerFactory.getInstance();
        GameComponentFactory gameComponentFactory = GameComponentFactory.getInstance();

        // 컨틀로러에 주입
        GameController controller = new GameController(inputView, outputView, playerFactory, gameComponentFactory);

        // 애플리케이션 객체 내부적으로 생성
        controller.createGameComponent();

        // 애플리케이션 운영
        controller.play();

        // 애플리케이션 종료
        controller.end();
    }
}