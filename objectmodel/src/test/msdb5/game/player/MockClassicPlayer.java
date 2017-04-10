package msdb5.game.player;

import msdb5.game.card.analysis.FixedScaleAnalyzer;
import msdb5.game.card.set.Hand;
import msdb5.game.card.Card;
import msdb5.game.card.analysis.ICardAnalyzer;

import java.util.stream.Stream;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockClassicPlayer extends MockPlayer {

    public MockClassicPlayer() {
        super(0.5F, 2);
    }

}
