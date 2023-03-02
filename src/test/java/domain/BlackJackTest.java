package domain;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {
    @Test
    @DisplayName("게임 시작 시 플레이어들에게 카드를 2장씩 나눠준다.")
    void whenStartingGame_thenPerPlayerHavingTwoCard() {
        BlackJack blackJack = new BlackJack("여우,아벨", cardSize -> 0);
        blackJack.startGame();

        Map<Player, List<Card>> playersCards = blackJack.getPlayersCards();
        assertThat(playersCards.get(new Dealer()))
                .containsExactly(new Card(Shape.HEART, Number.ACE), new Card(Shape.HEART, Number.TWO));
        assertThat(playersCards.get(new Participant("여우")))
                .containsExactly(new Card(Shape.HEART, Number.THREE), new Card(Shape.HEART, Number.FOUR));
        assertThat(playersCards.get(new Participant("아벨")))
                .containsExactly(new Card(Shape.HEART, Number.FIVE), new Card(Shape.HEART, Number.SIX));
    }

    @Test
    @DisplayName("플레이어에게 한 장을 추가한다.")
    void givenPlayer_thenGivesCard() {
        BlackJack blackJack = new BlackJack("여우,아벨", cardSize -> 0);
        blackJack.startGame();

        blackJack.giveCard("여우");

        List<Card> cards = blackJack.getCardsFrom("여우");

        assertThat(cards).containsExactly(
                new Card(Shape.HEART, Number.THREE),
                new Card(Shape.HEART, Number.FOUR),
                new Card(Shape.HEART, Number.SEVEN)
        );
    }
}