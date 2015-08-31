package table;

import deck.Deck;
import deck.DeckFactory;
import player.Player;

import static gameinfo.GameInfoConstants.NUMBER_OF_PLAYERS;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactory {

    public GameTable getCreatedGameTable() {
        GameTable gameTable = new GameTable();

        Deck deck = createDeck();
        gameTable.setDeck(deck);

        Player[] players = createPlayers();
        gameTable.setPlayers(players);

        return gameTable;
    }

    private Deck createDeck() {
        DeckFactory deckFactory = new DeckFactory();
        return deckFactory.getCreatedDeck();
    }

    private Player[] createPlayers() {
        Player[] players = new Player[NUMBER_OF_PLAYERS];
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i] = new Player();
        }
        return players;
    }
}
