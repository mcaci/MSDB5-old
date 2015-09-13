package game.player.auction;

/**
 * Created by nikiforos on 04/09/15.
 */
public class AuctionStance {

    private Score score;
    private Status status;

    public AuctionStance() {
        score = new Score();
        status = Status.NOT_STARTED;
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
        return "AuctionStance{" +
                "score=" + score +
                ", status=" + status +
                '}';
    }
}
