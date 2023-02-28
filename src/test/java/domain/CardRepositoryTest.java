package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardRepositoryTest {
    @BeforeEach
    void setUp() {
        CardRepository.init();
    }

    @Test
    @DisplayName("CardRepository가 생성되면 모든 종류의 카드가 저장되어 있다.")
    void initializingCardRepository() {
        assertThat(CardRepository.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("CardRepository에서 인덱스가 주어지면 그 인덱스에 해당하는 카드를 가져온다.")
    void findCardByIndex() {
        Card card = CardRepository.findCardByIndex(3);

        assertThat(card).isEqualTo(new Card(Shape.HEART, Number.FOUR));
        assertThat(CardRepository.size()).isEqualTo(51);
    }
}