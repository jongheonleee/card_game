package main.cardGame.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import main.cardGame.model.CardPlayer;
import main.framework.Player;
import main.framework.PlayerFactory;

public class CardGamePlayerFactory extends PlayerFactory {

    private static final String DUPLICATED_NAME = "중복된 이름입니다. 다른 이름을 입력해주세요!";
    private static final CardGamePlayerFactory singleton = new CardGamePlayerFactory(new HashSet<>());
    private final Set<String> names;

    private CardGamePlayerFactory(Set<String> names) {
        this.names = names;
    }

    @Override
    protected Player createPlayer(String name) {
        boolean isDuplicated = names.stream()
                .anyMatch(usedName -> usedName.equals(name));

        if (isDuplicated) {
            throw new IllegalArgumentException(DUPLICATED_NAME);
        }

        names.add(name);
        return new CardPlayer(name);
    }

    public static CardGamePlayerFactory getInstance() {
        return singleton;
    }
}
