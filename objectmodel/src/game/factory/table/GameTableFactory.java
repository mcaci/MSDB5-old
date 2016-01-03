package game.factory.table;

import game.cardset.Deck;
import game.factory.cardset.DeckFactory;
import game.player.Player;
import game.table.GameTable;

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
