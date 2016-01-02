package game.player;

import game.cardset.Hand;
import game.player.info.AuctionInfo;
import game.player.info.AuctionScore;
import game.player.info.AuctionStatus;

/**
 * Created by nikiforos on 11/09/15.
 * Il Buon Compagno: Nessuno si aspetta chissà cosa da lui, considerando che nelle partite precedenti ha alternato
 * qualcosa di buono a moltissime cose non buone. Ma proprio per questo motivo risulterà il vincitore incontrastato
 * della serata, con evidente stupore di tutti i presenti (lui compreso). La sua serata inizia nel migliore dei modi
 * e al primo turno fa una mossa così contorta, ma così contorta, che neanche lui sa bene cosa abbia fatto.
 * La sua fortuna è quella di non avere mai chissà quali carte ma sempre quelle essenziali per essere appunto
 * un buon compagno. L'unico problema di questo giocatore (che poi sono io) è che a briscola in 5 non si gioca a soldi,
 * quindi questo è il solo motivo della sua vittoria. Sta ancora maledicendo chi ha inventato il Poker Texano.
 */
public class BuonCompagno extends Player {
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
        nextScore = Math.max(nextScore, AuctionScore.MIN_SCORE);
        nextScore = Math.min(nextScore, AuctionScore.MAX_SCORE);
        return nextScore;
    }
}
