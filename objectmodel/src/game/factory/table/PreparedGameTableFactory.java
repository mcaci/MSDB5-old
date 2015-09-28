package game.factory.table;

import game.elements.base.Card;
import game.elements.cardset.Deck;
import game.elements.cardset.Hand;
import game.elements.player.Player;
import game.elements.table.GameTable;

import java.util.Queue;

/**
 * Created by nikiforos on 31/08/15.
 */
public class PreparedGameTableFactory extends GameTableFactory {

    private static final int NUMBER_OF_PLAYERS = 5;
    private static final int SIDE_DECK_SIZE = 5;

    private final boolean useSideDeck;

    public PreparedGameTableFactory(boolean useSideDeck) {
        this.useSideDeck = useSideDeck;
    }

    @Override
    public GameTable create() {
        GameTable gameTable = super.create();

        Deck gameDeck = gameTable.getDeck();
        Player[] players = gameTable.getPlayers();
        distributeCardsToPlayers(gameDeck, players, useSideDeck);

        return gameTable;
    }

    private void distributeCardsToPlayers(Deck gameDeck, Player[] players, boolean useSideDeck) {
        Queue<Card> deck = gameDeck.getCardSet();
        int size = gameDeck.getCardSet().size();
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
