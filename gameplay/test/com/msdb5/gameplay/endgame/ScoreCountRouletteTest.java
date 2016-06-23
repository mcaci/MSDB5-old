package com.msdb5.gameplay.endgame;

import com.msdb5.game.cardset.CardSet;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.factory.cardset.DeckFactory;
import com.msdb5.game.factory.table.PreparedGameTableFactory;
import com.msdb5.game.player.Player;
import com.msdb5.game.player.ia.player.*;
import com.msdb5.game.table.GameTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 18/02/16.
 */
public class ScoreCountRouletteTest {

//    private ScoreCountRoulette scoreCountRouletteTest;
    private GameTable inputOutputGameTable;

    @Before
    public void setUp() throws Exception {
        Player[] testPlayers = {new Ambiguo(), new BuonCompagno(), new CampioneDecaduto(), new Dubbioso(), new Rialzatore()};
        assignMockCardPilesToPlayers(testPlayers);
        inputOutputGameTable = new PreparedGameTableFactory(true).create(testPlayers);
        ScoreCountRoulette scoreCountRouletteTest = new ScoreCountRoulette();
        scoreCountRouletteTest.executeOn(inputOutputGameTable);

    }

    private void assignMockCardPilesToPlayers(Player[] testPlayers) {
        CardSet cardSet = new DeckFactory().createDeck();
        for (Card card : cardSet.getCardSet()) {
            int playerIndex = (int) (Math.random() * testPlayers.length);
            testPlayers[playerIndex].addToCardPile(card);
        }
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("scoreCountRouletteTest: gameTable after the score count");
        System.out.println(inputOutputGameTable);
    }

    @Test
    public void testSinglePlayersScore() throws Exception {
        Player[] players = inputOutputGameTable.getPlayers();
        for (Player player: players) {
            assertTrue(player.tellScore() >= 0);
            assertTrue(player.tellScore() <= 120);
        }
    }

    @Test
    public void testOverallScore() throws Exception {
        Player[] players = inputOutputGameTable.getPlayers();
        byte overallScore = 0;
        for (Player player: players) {
            overallScore += player.tellScore();
        }
        assertEquals(120, overallScore);
    }

    @Test
    public void testScoreBeingSumOfValues() throws Exception {
        Player[] players = inputOutputGameTable.getPlayers();
        byte playerScore = players[0].tellScore();
        byte sumOfPoints = getSumOfPoints(players[0]);
        assertEquals("The score counted is not the sum of all the points of the card collected", playerScore, sumOfPoints);
    }

    private byte getSumOfPoints(Player player) {
        byte sumOfPoints = 0;
        CardSet cardPile = player.getCardPile();
        Collection<Card> cards = cardPile.getCardSet();
        assertNotNull(cards);
        for (Card card : cards) {
            sumOfPoints += card.pointsForTheCard();
        }
        return sumOfPoints;
    }
}