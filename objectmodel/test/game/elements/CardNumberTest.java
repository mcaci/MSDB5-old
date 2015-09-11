package game.elements;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 11/09/15.
 */
public class CardNumberTest {

    @Test
    public void testValue() throws Exception {
        assertTrue(CardNumber.ASSO.value() > CardNumber.TRE.value());
        assertTrue(CardNumber.TRE.value() > CardNumber.RE.value());
        assertTrue(CardNumber.RE.value() > CardNumber.CAVALLO.value());
        assertTrue(CardNumber.CAVALLO.value() > CardNumber.DONNA.value());
        assertTrue(CardNumber.DONNA.value() > CardNumber.SETTE.value());
        assertTrue(CardNumber.SETTE.value() > CardNumber.SEI.value());
        assertTrue(CardNumber.SEI.value() > CardNumber.CINQUE.value());
        assertTrue(CardNumber.CINQUE.value() > CardNumber.QUATTRO.value());
        assertTrue(CardNumber.QUATTRO.value() > CardNumber.DUE.value());
    }
}