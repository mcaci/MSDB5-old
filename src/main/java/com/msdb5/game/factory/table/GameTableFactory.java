package com.msdb5.game.factory.table;

import com.msdb5.game.cardset.Deck;
import com.msdb5.game.factory.cardset.DeckFactory;
import com.msdb5.game.player.Player;
import com.msdb5.game.table.GameTable;


/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactory {

    public GameTable create(Player[] players) {
        GameTable gameTable = new GameTable();

        Deck deck = createDeck();
        gameTable.setDeck(deck);

        gameTable.setPlayers(players);

        return gameTable;
    }

    private Deck createDeck() {
        DeckFactory deckFactory = new DeckFactory();
        return deckFactory.createDeck();
    }

}
