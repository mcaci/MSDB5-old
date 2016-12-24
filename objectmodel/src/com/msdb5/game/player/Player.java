package com.msdb5.game.player;

import com.msdb5.game.cardset.CardPile;
import com.msdb5.game.cardset.CardSet;
import com.msdb5.game.cardset.Hand;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.factory.cardset.HandFactory;
import com.msdb5.game.player.characteristic.IPersonalityForPreparation;
import com.msdb5.game.player.characteristic.IPersonalityInGame;
import com.msdb5.game.player.info.AuctionInfo;
import com.msdb5.game.player.info.AuctionStatus;
import com.msdb5.game.player.info.ScoreCountInfo;

import java.util.Collections;


/**
 * Created by nikiforos on 30/08/15.
 */
public abstract class Player implements IPersonalityForPreparation, IPersonalityInGame {

    private final AuctionInfo auctionInfo = new AuctionInfo();
    private final Hand hand = new HandFactory(Hand.EMPTY_HAND_SIZE).createHand();
    private final CardSet cardPile = new CardPile();
    private final ScoreCountInfo postGameInfo = new ScoreCountInfo();

    public Hand getHand() {
        return this.hand;
    }

    public AuctionInfo getAuctionInfo() {
        return auctionInfo;
    }

    @Override
    public String toString() {
        return "Player{" +
                "auctionInfo=" + auctionInfo +
                ", hand=" + hand +
                ", cardPile=" + cardPile +
                ", postGameInfo=" + postGameInfo +
                '}';
    }

    public boolean hasActed() {
        return auctionInfo.getAuctionStatus().actionWasDone();
    }

    public boolean isWinner() {
        return auctionInfo.getAuctionStatus().isWinner();
    }

    public boolean hasFolded() {
        return auctionInfo.getAuctionStatus().hasFolded();
    }

    public byte tellAuctionScore() {
        return this.auctionInfo.getAuctionScore().getScore();
    }

    public void setAsAuctionWinner() {
        this.getAuctionInfo().setAuctionStatus(AuctionStatus.AUCTION_WINNER);
    }

    public byte tellScore() {
        return this.postGameInfo.getScore();
    }

    public void setScore(byte score) {
        this.postGameInfo.setScore(score);
    }

    public void addToCardPile(Card... cards) {
        Collections.addAll(this.cardPile.getCardSet(), cards);
    }

    public CardSet getCardPile() {
        return this.cardPile;
    }
}
