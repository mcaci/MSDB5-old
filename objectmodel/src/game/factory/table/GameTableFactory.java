package game.factory.table;

import game.cardset.Deck;
import game.factory.cardset.DeckFactory;
import game.player.Player;
import game.player.info.AuctionInfo;
import game.table.GameTable;

import static game.table.GameTableInfo.NUMBER_OF_PLAYERS;

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
            // TODO: Filler, to be replaced with real implementations
            players[i] = new Player() {
                @Override
                public AuctionInfo performAuctionAction(int currentScore) {
                    return null;
                }
            };
        }
        return players;
    }
}
