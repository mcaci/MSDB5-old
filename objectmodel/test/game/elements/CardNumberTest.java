package game.elements;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 11/09/15.
 */
public class CardNumberTest {

    @Test
    public void testValueOfTheCard() throws Exception {
        assertTrue(CardNumber.ASSO.valueOfTheCard() > CardNumber.TRE.valueOfTheCard());
        assertTrue(CardNumber.TRE.valueOfTheCard() > CardNumber.RE.valueOfTheCard());
        assertTrue(CardNumber.RE.valueOfTheCard() > CardNumber.CAVALLO.valueOfTheCard());
        assertTrue(CardNumber.CAVALLO.valueOfTheCard() > CardNumber.DONNA.valueOfTheCard());
        assertTrue(CardNumber.DONNA.valueOfTheCard() > CardNumber.SETTE.valueOfTheCard());
        assertTrue(CardNumber.SETTE.valueOfTheCard() > CardNumber.SEI.valueOfTheCard());
        assertTrue(CardNumber.SEI.valueOfTheCard() > CardNumber.CINQUE.valueOfTheCard());
        assertTrue(CardNumber.CINQUE.valueOfTheCard() > CardNumber.QUATTRO.valueOfTheCard());
        assertTrue(CardNumber.QUATTRO.valueOfTheCard() > CardNumber.DUE.valueOfTheCard());
    }
}