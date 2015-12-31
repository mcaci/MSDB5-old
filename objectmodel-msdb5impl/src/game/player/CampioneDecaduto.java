package game.player;

import game.elements.cardset.Hand;
import game.elements.player.Player;
import game.elements.player.auction.info.AuctionInfo;
import game.elements.player.auction.info.AuctionScore;
import game.elements.player.auction.info.AuctionStatus;

/**
 * Created by nikiforos on 11/09/15.
 * Il Campione Decaduto / l'Accappottato: La doppia designazione di questo mistico Personaggio è dovuta alle sue regali origini.
 * In tempi remoti era il padrone incontrastato del gioco e si sapeva già dall'inizio che avrebbe vinto tutto ciò
 * che c'era da vincere, ma da un po' di tempo a questa parte il suo titolo nobiliare ha perso di valore.
 * Tenta in tutti i modi di raggiungere gli antichi fasti ma nonostante per ben due volte con la mano quasi servita
 * cerchi di fare Cappotto (ovvero chiamare la posta a 120), il poveraccio verrà a sua volta Accappottato per colpa
 * di un Re di spade che lo farà abdicare miseramente. I suoi ex sudditi lo compiangono ancora.
 */
public class CampioneDecaduto extends Player {


    private static final float CHANCE_TO_FOLD = 0.4F;

    @Override
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
        nextScore = Math.max(nextScore, MIN_AUCTION_SCORE);
        nextScore = Math.min(nextScore, MAX_AUCTION_SCORE);
        return nextScore;
    }
}
