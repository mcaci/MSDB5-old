package msdb5.game.table;

import msdb5.game.card.set.Deck;
import msdb5.game.card.set.Hand;
import msdb5.game.card.Card;
import msdb5.game.player.Player;

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
    public GameTable create(Player[] players) {
        GameTable gameTable = super.create(players);

        Deck gameDeck = gameTable.getDeck();
        distributeCardsToPlayers(gameDeck, players, useSideDeck);

        gameTable.getInfo().setSideDeckPresent(useSideDeck);

        return gameTable;
    }

    private void distributeCardsToPlayers(Deck deck, Player[] players, boolean useSideDeck) {
        int numberOfCardsToDistribute = getNumberOfCardsToDistribute(deck.size(), useSideDeck);
        Queue<Card> deckQueue = deck.getCardSet();
        for (int i = 0; i < numberOfCardsToDistribute; i++) {
            Player player = players[i % NUMBER_OF_PLAYERS];
            Hand playerHand = player.getHand();
            Card cardToAdd = deckQueue.remove();
            playerHand.add(cardToAdd);
        }
    }

    private int getNumberOfCardsToDistribute(int size, boolean useSideDeck) {
        if (useSideDeck) {
            size -= SIDE_DECK_SIZE;
        }
        return size;
    }
}
