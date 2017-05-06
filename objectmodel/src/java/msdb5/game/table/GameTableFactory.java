package msdb5.game.table;

import msdb5.game.card.set.Deck;
import msdb5.game.card.set.DeckFactory;
import msdb5.game.card.set.SideDeck;
import msdb5.game.card.set.SideDeckFactory;
import msdb5.game.player.Player;

import java.util.stream.IntStream;

/**
 * Created by nikiforos on 31/08/15.
 */
public class GameTableFactory {

    private static final int NUMBER_OF_PLAYERS = 5;
    private static final int SIDE_DECK_SIZE = 5;

    private final boolean useSideDeck;

    public GameTableFactory(boolean useSideDeck) {
        this.useSideDeck = useSideDeck;
    }

    public GameTable create(Player[] players) {
        GameTable gameTable = new GameTable();
        gameTable.setPlayers(players);

        Deck gameDeck = new DeckFactory().create();
        distributeCardsToPlayers(gameDeck, players, useSideDeck);

        gameTable.getInfo().setSideDeckPresent(useSideDeck);
        SideDeck sideDeck = new SideDeckFactory().create();
        gameTable.setSideDeck(sideDeck);
        if (useSideDeck) {
            sideDeck.addAll(gameDeck);
        }

        return gameTable;
    }

    private void distributeCardsToPlayers(Deck deck, Player[] players, boolean useSideDeck) {
        IntStream.iterate(0, id -> (id + 1) % NUMBER_OF_PLAYERS).
                limit(useSideDeck? deck.size() - SIDE_DECK_SIZE : deck.size()).
                forEach(value -> players[value].drawCard(deck));
    }
}
