package table;

import deck.Deck;
import deck.DeckFactory;
import player.Player;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactory {

    public static final int NUMBER_OF_PLAYERS = 5;

    public GameTable getCreatedGameTable() {
        GameTable gameTable = new GameTable();

        DeckFactory deckFactory = new DeckFactory();
        Deck deck = deckFactory.getCreatedDeck();
        gameTable.setDeck(deck);

        Player[] players = new Player[NUMBER_OF_PLAYERS];
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i] = new Player();
        }
        gameTable.setPlayers(players);

        return gameTable;
    }
}
