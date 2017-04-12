package msdb5.game.player.info;

import msdb5.game.player.Player;

/**
 * Created by nikiforos on 04/09/15.
 */
public class AuctionInfo {

    private int auctionScore;
    private AuctionStatus auctionStatus;

    public AuctionInfo() {
        auctionScore = Player.MIN_AUCTION_SCORE;
        auctionStatus = AuctionStatus.NOT_STARTED;
    }

    public int getAuctionScore() {
        return auctionScore;
    }

    public void setAuctionScore(int auctionScore) {
        this.auctionScore = auctionScore;
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    @Override
    public String toString() {
        return "AuctionInfo{" +
                "auction=" + auctionScore +
                ", auctionStatus=" + auctionStatus +
                '}';
    }

}
