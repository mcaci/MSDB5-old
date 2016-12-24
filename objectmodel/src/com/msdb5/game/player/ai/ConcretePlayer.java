package com.msdb5.game.player.ai;

import com.msdb5.game.cardset.Deck;
import com.msdb5.game.cardset.Hand;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.cardset.card.CardNumber;
import com.msdb5.game.cardset.card.CardSuit;
import com.msdb5.game.player.Player;
import com.msdb5.game.player.characteristic.AuctionOnScoreOutOfBoundsException;
import com.msdb5.game.player.info.AuctionInfo;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.player.info.AuctionStatus;

/**
 * Created by nikiforos on 13/02/16.
 */
public abstract class ConcretePlayer extends Player {

    private static final float CHANCE_TO_FOLD = 0.4F;

    @Override
    public AuctionInfo performAuctionAction(int currentAuctionScore) throws AuctionOnScoreOutOfBoundsException {
        if (currentAuctionScore < AuctionScore.MIN_SCORE || currentAuctionScore >= AuctionScore.MAX_SCORE) {
            throw new AuctionOnScoreOutOfBoundsException();
        }

        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (!auctionInfo.getAuctionStatus().hasFolded()) {
            final double randomFlag = Math.random();
            if (randomFlag > CHANCE_TO_FOLD) {
                auctionInfo.setAuctionStatus(AuctionStatus.IN_AUCTION);
                auctionInfo.setAuctionScore(chooseNextScore(this.getHand(), currentAuctionScore));
            } else {
                auctionInfo.setAuctionStatus(AuctionStatus.FOLDED);
            }
        }
        return auctionInfo;
    }

    AuctionScore chooseNextScore(Hand hand, int currentScore) {
        final AuctionScore auctionScore = new AuctionScore();
        final int nextScore = decideNextScore(currentScore);
        auctionScore.setSafeScore(nextScore);
        return auctionScore;
    }

    int decideNextScore(int currentScore) {
        int nextScore = ++currentScore;
        nextScore = Math.max(nextScore, AuctionScore.MIN_SCORE + 1);
        nextScore = Math.min(nextScore, AuctionScore.MAX_SCORE);
        return nextScore;
    }

    @Override
    public Card chooseCompanionCard() {
        // TODO: insert better logic for card choice
        return new Card(CardNumber.DUE, CardSuit.SPADE);
    }

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        return 61;
    }

    @Override
    public void swapCardsWithSideDeck(Deck deck) {
        return; // TODO: real impl
    }
}
