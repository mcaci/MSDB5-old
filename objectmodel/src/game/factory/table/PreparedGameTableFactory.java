package game.factory.table;

import game.cardset.Deck;
import game.cardset.Hand;
import game.cardset.card.Card;
import game.player.Player;
import game.table.GameTable;

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
