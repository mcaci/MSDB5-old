package msdb5.game.card.set;

import msdb5.game.card.Card;
import org.junit.After;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 29/08/15.
 */
public abstract class CardSetFactoryTest {

    private final CardSet<?> mockCardSet;

    CardSetFactoryTest(CardSet<?> cardSet) {
        this.mockCardSet = cardSet;
    }

    CardSet<?> getMockCardSet() {
        return mockCardSet;
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(mockCardSet.toString());
    }

    @Test
    public void testOnSize() throws Exception {
        int cardSetSize = mockCardSet.getCardSet().size();
        assertTrue("Card set size should be greater or equals than 0", cardSetSize >= 0);
        testOnConcreteSize(cardSetSize);
    }

    protected abstract void testOnConcreteSize(int cardSetSize);

    @Test
    public void testOnContent() throws Exception {
        assertNotNull(mockCardSet);
        assertNotNull(mockCardSet.getCardSet());
        Stream<Card> cards = mockCardSet.getCardSet().stream();
        assertTrue(cards.allMatch(Card::isValid));
    }

    @Test
    public void testAllCardsAreDifferent() throws Exception {
        Collection<Card> cards = mockCardSet.getCardSet();
        int actualCardSize = cards.size();
        long expectedCardSize = cards.stream().distinct().count();
        String message = "The size expected for the card set is " + expectedCardSize + " but it was found to be " + actualCardSize;
        assertEquals(message, expectedCardSize, actualCardSize);
    }
}