package msdb5.game.card.set;

import static org.junit.Assert.assertEquals;

/**
 * Created by nikiforos on 08/09/15.
 */
public class CollectedCardSetFactoryTest extends CardSetFactoryTest {

    public CollectedCardSetFactoryTest() {
        super(new CollectedCardSetFactory().create());
    }


    @Override
    public void testOnConcreteSize(int deckSize) {
        assertEquals("Collected cardset is not empty", 0, deckSize);
    }

}
