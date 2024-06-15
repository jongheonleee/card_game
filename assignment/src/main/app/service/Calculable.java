package main.app.service;

import java.util.List;
import main.app.model.Card;

public interface Calculable {
    int calculate(List<Card> cards);
}
