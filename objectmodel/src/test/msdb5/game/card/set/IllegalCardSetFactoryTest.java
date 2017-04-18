package msdb5.game.card.set;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by mcaci on 4/12/17.
 */
public class IllegalCardSetFactoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSize() throws Exception {
        new CardSetFactory(ArrayList::new,-1) {
            @Override
            public CardSet create() {
                return null;
            }
        };
    }

}
