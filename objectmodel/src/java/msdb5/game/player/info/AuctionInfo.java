package msdb5.game.player.info;

/**
 * Created by nikiforos on 04/09/15.
 */
public class AuctionInfo {

    private AuctionScore auctionScore;
    private AuctionStatus auctionStatus;

    public AuctionInfo() {
        auctionScore = new AuctionScore();
        auctionStatus = AuctionStatus.NOT_STARTED;
    }

    public AuctionScore getAuctionScore() {
        return auctionScore;
    }

    public void setAuctionScore(AuctionScore auctionScore) {
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
