package msdb5.game.table;

import msdb5.game.card.set.Deck;
import msdb5.game.card.set.DeckFactory;
import msdb5.game.player.Player;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactory {

    public GameTable create(Player[] players) {
        GameTable gameTable = new GameTable();

        Deck deck = createDeck();
        gameTable.setDeck(deck);

        gameTable.setPlayers(players);

        return gameTable;
    }

    private Deck createDeck() {
        DeckFactory deckFactory = new DeckFactory();
        return deckFactory.createDeck();
    }

}
