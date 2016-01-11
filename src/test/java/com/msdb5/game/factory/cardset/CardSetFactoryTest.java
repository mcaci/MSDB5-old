package com.msdb5.game.factory.cardset;

import org.junit.After;
import org.junit.Test;

import com.msdb5.game.cardset.CardSet;
import com.msdb5.game.cardset.card.Card;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 29/08/15.
 */
public abstract class CardSetFactoryTest {

    final CardSetFactory cardSetFactoryTestObject;
    CardSet mockCardSet;

    CardSetFactoryTest(CardSetFactory cardSetFactoryTestObject) {
        this.cardSetFactoryTestObject = cardSetFactoryTestObject;
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(mockCardSet.toString());
    }

    @Test
    public void testOnSize() throws Exception {
        int cardSetSize = mockCardSet.getCardSet().size();
        assertTrue("Deck size should be greater than 0", cardSetSize > 0);
        testOnConcreteSize(cardSetSize);
    }

    protected abstract void testOnConcreteSize(int cardSetSize);

    @Test
    public void testOnContent() throws Exception {
        assertNotNull(mockCardSet);
        assertNotNull(mockCardSet.getCardSet());
        Collection<?> cards = mockCardSet.getCardSet();
        for (Object card : cards) {
            assertTrue(card instanceof Card);
            assertTrue(((Card) card).isValid());
        }
    }

    @Test
    public void testAllCardsAreDifferent() throws Exception {
        Collection<?> cards = mockCardSet.getCardSet();

        Object[] cardArray = cards.toArray();
        for (int i = 0; i < cardArray.length; i++) {
            Object cardToSearch = cardArray[i];
            for (int j = i + 1; j < cardArray.length; j++) {
                Object cardInSearch = cardArray[j];
                boolean cardIsFound = cardToSearch.equals(cardInSearch);
                String message = cardToSearch + " in index " + i + " was found in game in index " + j;
                assertFalse(message, cardIsFound);
            }
        }
    }
}