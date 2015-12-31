package game.elements.player.auction.info;

/**
 * Created by nikiforos on 04/09/15.
 */
public class AuctionInfo {

    private Score score;
    private Status status;

    public AuctionInfo() {
        score = new Score();
        status = Status.NOT_STARTED;
    }

    public void update(AuctionInfo newInfo) {
        this.setScore(newInfo.getScore());
        this.setStatus(newInfo.getStatus());
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AuctionInfo{" +
                "auction=" + score +
                ", status=" + status +
                '}';
    }

}
