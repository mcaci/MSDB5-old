package game.factory;

import game.elements.Deck;
import game.elements.GameTable;
import game.player.Player;
import strategy.auction.AuctionActionDefault;

import static game.elements.GameTable.NUMBER_OF_PLAYERS;

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
        DeckFactory cardSetFactory = new DeckFactory();
        return cardSetFactory.createDeck();
    }

    private Player[] createPlayers() {
        Player[] players = new Player[NUMBER_OF_PLAYERS];
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i] = new Player(new AuctionActionDefault());
        }
        return players;
    }
}
