package game.factory.table;

import game.elements.cardset.Deck;
import game.elements.player.Player;
import game.elements.table.GameTable;
import game.factory.cardset.DeckFactory;

import static game.elements.table.GameTable.NUMBER_OF_PLAYERS;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactory {

    public GameTable create() {
        GameTable gameTable = new GameTable();

        Deck deck = createDeck();
        gameTable.setDeck(deck);

        Player[] players = createPlayers();
        gameTable.setPlayers(players);

        return gameTable;
    }

    private Deck createDeck() {
        DeckFactory deckFactory = new DeckFactory();
        return deckFactory.createDeck();
    }

    private Player[] createPlayers() {
        Player[] players = new Player[NUMBER_OF_PLAYERS];
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i] = new Player();
        }
        return players;
    }
}
