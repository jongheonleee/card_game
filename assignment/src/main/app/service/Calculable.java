package main.app.service;

import java.util.List;
import main.app.model.Card;
import main.app.model.Ranking;

public interface Calculable {
    int calculate(List<Card> cards, Ranking ranking);
}
