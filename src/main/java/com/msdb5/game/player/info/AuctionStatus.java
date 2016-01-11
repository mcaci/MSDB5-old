package com.msdb5.game.player.info;

/**
 * Created by nikiforos on 04/09/15.
 */
public enum AuctionStatus {
    NOT_STARTED, IN_AUCTION, FOLDED, AUCTION_WINNER;

    public boolean actionWasDone() {
        return !(this == NOT_STARTED);
    }

    public boolean isWinner() {
        return this == AUCTION_WINNER;
    }

    public boolean hasFolded() {
        return this == FOLDED;
    }

}
