package main.framework.factory;

import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import main.app.controller.CardGameController;
import main.app.model.Player;
import main.app.model.Dealer;
import main.app.model.Deck;
import main.app.model.Ground;
import main.app.service.Calculable;
import main.app.service.CardGameCalculator;
import main.app.view.CardGameOutput;
import main.framework.domain.AppComponent;
import main.framework.domain.AppController;
import main.app.view.CardGameInput;
import main.framework.view.AppComponentView;

public class AppContext {

    private static final AppContext singleton = new AppContext();
    private final AppComponentView appComponentView = AppComponentView.getInstance();
    private final Map<String, AppComponent> pool = new HashMap();

    private AppContext() {}

    public synchronized AppComponent create(String name, Class clazz)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        AppComponent target = pool.get(name);

        if (target == null) {
            if (name.equals("deck")) {
                target = (AppComponent) clazz.getDeclaredConstructor().newInstance();
            } else if (name.equals("dealer")) {
                Calculable calculator = new CardGameCalculator();
                target = (AppComponent) clazz.getConstructor(Calculable.class).newInstance(calculator);
            } else if (name.equals("players")) {
                List<Player> list = new ArrayList<>();
                int numberOfPlayer = appComponentView.getNumberOfPlayer();

                // n명의 플레이어가 정상적으로 생성될 때까지 반복
                while (numberOfPlayer > 0) {
                    try {
                        String userName = appComponentView.getPlayerName();
                        Player player = new Player(userName);
                        list.add(player);
                        numberOfPlayer--;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                target = (AppComponent) clazz.getConstructor(List.class).newInstance(list);

            } else if (name.equals("cardGameInputView")){
                Scanner sc = new Scanner(System.in);
                target = (AppComponent) clazz.getDeclaredConstructor(Scanner.class).newInstance(sc);
            } else if (name.equals("cardGameOutputView")){
                target = (AppComponent) clazz.getDeclaredConstructor().newInstance();
            } else {
                throw new ClassNotFoundException(name + " 과 관련된 클래스를 찾을 수 없습니다.");
            }
            pool.put(name, target);
        }

        return target;
    }


    public AppController getAppController()
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AppComponent players = create("players", Ground.class);
        AppComponent dealer = create("dealer", Dealer.class);
        AppComponent deck = create("deck", Deck.class);
        AppComponent inputView = create("cardGameInputView", CardGameInput.class);
        AppComponent outputView = create("cardGameOutputView", CardGameOutput.class);

        AppController controller = new CardGameController.Builder()
                .ground((Ground) players).dealer((Dealer) dealer)
                .deck((Deck) deck).inputView((CardGameInput) inputView)
                .outputView((CardGameOutput) outputView).build();

        return controller;
    }

    public static AppContext getInstance() {
        return singleton;
    }
}
