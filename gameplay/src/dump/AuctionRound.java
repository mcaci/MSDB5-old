package dump;

import game.player.Player;
import game.player.auction.Status;

/**
 * Created by nikiforos on 01/09/15.
 */
public class AuctionRound {

    public void performAuctionRoulette(Player[] players) {

        for (int i = 0; i < players.length; i++) {
            players[i].getAuctionStance().setStatus(Status.FOLDED);
            players[i].getAuctionStance().getScore().setSafeScore(60 + i);
        }
        players[4].getAuctionStance().setStatus(Status.AUCTION_WINNER);

    }
}
