package msdb5.game.card;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 11/09/15.
 */
public class CardNumberTest {

    @Test
    public void testWeights() throws Exception {
        assertTrue(CardNumber.ASSO.getWeight() > CardNumber.TRE.getWeight());
        assertTrue(CardNumber.TRE.getWeight() > CardNumber.RE.getWeight());
        assertTrue(CardNumber.RE.getWeight() > CardNumber.CAVALLO.getWeight());
        assertTrue(CardNumber.CAVALLO.getWeight() > CardNumber.DONNA.getWeight());
        assertTrue(CardNumber.DONNA.getWeight() > CardNumber.SETTE.getWeight());
        assertTrue(CardNumber.SETTE.getWeight() > CardNumber.SEI.getWeight());
        assertTrue(CardNumber.SEI.getWeight() > CardNumber.CINQUE.getWeight());
        assertTrue(CardNumber.CINQUE.getWeight() > CardNumber.QUATTRO.getWeight());
        assertTrue(CardNumber.QUATTRO.getWeight() > CardNumber.DUE.getWeight());
    }

    @Test
    public void testPoints() throws Exception {
        assertTrue(CardNumber.ASSO.getPoints() > CardNumber.TRE.getPoints());
        assertTrue(CardNumber.TRE.getPoints() > CardNumber.RE.getPoints());
        assertTrue(CardNumber.RE.getPoints() > CardNumber.CAVALLO.getPoints());
        assertTrue(CardNumber.CAVALLO.getPoints() > CardNumber.DONNA.getPoints());
        assertTrue(CardNumber.DONNA.getPoints() > CardNumber.SETTE.getPoints());
        assertTrue(CardNumber.SETTE.getPoints() == CardNumber.SEI.getPoints());
        assertTrue(CardNumber.SEI.getPoints() == CardNumber.CINQUE.getPoints());
        assertTrue(CardNumber.CINQUE.getPoints() == CardNumber.QUATTRO.getPoints());
        assertTrue(CardNumber.QUATTRO.getPoints() == CardNumber.DUE.getPoints());
    }
}