package com.msdb5.game.player;

import com.msdb5.game.cardset.Hand;
import com.msdb5.game.player.info.AuctionInfo;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.player.info.AuctionStatus;

/**
 * Created by nikiforos on 11/09/15.
 * L'Ambiguo: Sarebbe necessario un intero trattato per cercare di spiegare bene la psiche (molto labile)
 * di questo strano essere vivente. Le sue caratteristiche principali sono due: non la smette un attimo di parlare
 * (e incazzarsi senza motivo) e cerca di confondere in tutti i modi chi invece dovrebbe aiutare.
 * Quando è un Compagno le sue sono principalmente mosse da Non-Compagno e viceversa, con ,come conseguenza,
 * la distruzione mentale degli altri giocatori (il Dubbioso in testa). Il giorno in cui qualcuno scoprirà la sua
 * tecnica di gioco probabilmente sarà stato tutto vanificato dall'apocalisse del 2012.
 */
public class Ambiguo extends Player {

    private static final float CHANCE_TO_FOLD = 0.4F;

    public AuctionInfo performAuctionAction(int currentScore) {
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (!auctionInfo.getAuctionStatus().hasFolded()) {
            final double randomFlag = Math.random();
            if (randomFlag > CHANCE_TO_FOLD) {
                auctionInfo.setAuctionStatus(AuctionStatus.IN_AUCTION);
                auctionInfo.setAuctionScore(chooseNextScore(this.getHand(), currentScore));
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
        nextScore = Math.max(nextScore, AuctionScore.MIN_SCORE);
        nextScore = Math.min(nextScore, AuctionScore.MAX_SCORE);
        return nextScore;
    }
}
