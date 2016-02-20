package com.msdb5.game.player.ia.player;

import com.msdb5.game.cardset.Hand;
import com.msdb5.game.player.info.AuctionInfo;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.player.info.AuctionStatus;

/**
 * Created by nikiforos on 11/09/15.
 * Il Rialzatore: Questo personaggio ha un solo scopo nella vita...alzare la posta iniziale senza alcun motivo plausibile.
 * Allora, per chi non lo sapesse nella briscola in 5 si parte da un minimo punteggio di 60 ad un massimo di 120 come posta iniziale;
 * bene, questo losco individuo deve sempre, e dico sempre, partire con un minimo di 80 per poi ad ogni rilancio
 * saltare a nove a nove. La cosa innervosisce gli altri giocatori (specialmente il Dubbioso)
 * e provoca un senso misto di stizza e vomito in tutti i presenti.
 * Sfortunatamente gioca in casa quindi non può essere contraddetto.
 */
public class Rialzatore extends ConcretePlayer {

    @Override
    public AuctionInfo performAuctionAction(int currentScore) {
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (!auctionInfo.getAuctionStatus().hasFolded()) {
            auctionInfo.setAuctionStatus(AuctionStatus.IN_AUCTION);
            auctionInfo.setAuctionScore(chooseNextScore(this.getHand(), currentScore));
        }
        return auctionInfo;
    }

    int decideNextScore(int currentScore) {
        int nextScore = ++currentScore;
        nextScore = Math.max(nextScore, AuctionScore.MIN_SCORE + 1);
        nextScore = Math.min(nextScore, AuctionScore.MAX_SCORE);
        return nextScore;
    }

    AuctionScore chooseNextScore(Hand hand, int currentScore) {
        final AuctionScore auctionScore = new AuctionScore();
        final int nextScore = decideNextScore(currentScore);
        auctionScore.setSafeScore(nextScore);
        return auctionScore;
    }

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        return 80;
    }
}
