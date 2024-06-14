package main.cardGame.service;

import java.util.List;
import main.cardGame.model.Card;

public interface Calculable {
    int calculate(List<Card> cards);
}
