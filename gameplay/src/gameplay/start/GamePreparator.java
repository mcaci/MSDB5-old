package gameplay.start;

import game.elements.Card;
import game.elements.Deck;
import game.elements.GameTable;
import game.factory.GameTableFactory;
import game.player.Hand;
import game.player.Player;

import java.util.Queue;

/**
 * Created by nikiforos on 31/08/15.
 */
public class GamePreparator {

    private static final int NUMBER_OF_PLAYERS = 5;
    private static final int SIDE_DECK_SIZE = 5;

    public GameTable getPreparedTable(boolean useSideDeck) {
        GameTableFactory gameTableFactory = new GameTableFactory();
        GameTable gameTable = gameTableFactory.getCreatedGameTable();

        Deck gameDeck = gameTable.getDeck();
        Player[] players = gameTable.getPlayers();
        distributeCardsToPlayers(gameDeck, players, useSideDeck);

        return gameTable;
    }

    private void distributeCardsToPlayers(Deck gameDeck, Player[] players, boolean useSideDeck) {
        Queue<Card> deck = gameDeck.getDeck();
        int size = gameDeck.getDeck().size();
        if (useSideDeck) {
            size -= SIDE_DECK_SIZE;
        }
        for (int i = 0; i < size; i++) {
            Player player = players[i % NUMBER_OF_PLAYERS];
            Hand playerHand = player.getHand();
            Card cardToAdd = deck.remove();
            playerHand.add(cardToAdd);
        }
    }
}