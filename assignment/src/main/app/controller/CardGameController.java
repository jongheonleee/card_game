package main.app.controller;

import java.util.List;
import main.app.model.Card;
import main.app.model.Dealer;
import main.app.model.Deck;
import main.app.model.Ground;
import main.app.model.Player;
import main.framework.domain.AppController;
import main.app.view.CardGameInput;
import main.app.view.CardGameOutput;

// 전체 게임 애플리케이션 관장
public class CardGameController implements AppController {

    private final CardGameOutput cardGameOutput;
    private final CardGameInput cardGameInput;
    private final Ground ground;
    private final Dealer dealer;
    private final Deck deck;

    public static class Builder {

        private Ground ground;
        private Dealer dealer;
        private Deck deck;

        private CardGameInput cardGameInput;
        private CardGameOutput cardGameOutput;

        public Builder() {
        }

        public Builder ground(Ground ground) {
            this.ground = ground;
            return this;
        }

        public Builder dealer(Dealer dealer) {
            this.dealer = dealer;
            return this;
        }

        public Builder deck(Deck deck) {
            this.deck = deck;
            return this;
        }

        public Builder inputView(CardGameInput cardGameInput) {
            this.cardGameInput = cardGameInput;
            return this;
        }

        public Builder outputView(CardGameOutput cardGameOutput) {
            this.cardGameOutput = cardGameOutput;
            return this;
        }

        public CardGameController build() {
            return new CardGameController(this);
        }
    }

    private CardGameController(Builder builder) {
        this.ground = builder.ground;
        this.dealer = builder.dealer;
        this.deck = builder.deck;
        this.cardGameInput = builder.cardGameInput;
        this.cardGameOutput = builder.cardGameOutput;
    }


    public void play() {
        // 사용자로부터 라운드 횟수, 상점 입력 받음
        int numberOfRound = cardGameInput.getNumberOfRounds();
        int award = cardGameInput.getAward();

        // 해당 라운드 만큼 게임 진행(요구사항에서는 100)
        for (int currRound=1; currRound<=numberOfRound; currRound++) {
            round(currRound, award);
        }
    }

    private void round(int currRound, int award) {
        // 덱이 카드 섞고 나눔
        int numberOfPlayer = ground.getPlayers().size();
        List<List<Card>> splitCards = deck.splitCards(numberOfPlayer);

        // 나눈 카드를 플레이어에게 각각 배분
        ground.giveOutCards(splitCards);

        // 딜러가 각 플레이어의 점수 계산
        dealer.calculateScore(ground.getPlayers());

        // 해당 라운드 승자 추출
        List<Player> winners = ground.decideWinnerInRound(award);

        // 해당 라운드 승자 출력
        cardGameOutput.showRoundWinner(currRound, winners);
    }

    
    public void end() {
        // 최종 우승자 추출
        List<Player> winners = ground.decideWinner();

        // 최종 우승자 출력
        cardGameOutput.showFinalWinner(winners);
    }

}
