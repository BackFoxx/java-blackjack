package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Player {
    private final Cards cards;
    private final String name;

    public Player(String name) {
        this.cards = new Cards();
        this.name = name;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int getTotalScore() {
        return cards.getTotalScore();
    }

    public boolean isBurst() {
        return cards.isBurst();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract boolean isNameEqualTo(String playerName);

    public String getName() {
        return this.name;
    }
}
