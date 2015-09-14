package game.player.auction;

import strategy.auction.IAuctionAction;

/**
 * Created by nikiforos on 04/09/15.
 */
public class Score implements Comparable<Score> {

    public static final byte MIN_SCORE = IAuctionAction.MIN_AUCTION_SCORE;
    public static final byte MAX_SCORE = IAuctionAction.MAX_AUCTION_SCORE;

    private byte score = MIN_SCORE;

    public byte getScore() {
        return score;
    }

    public void setScore(byte score) {
        this.score = score;
    }

    public void setSafeScore(int score) {
        byte scoreLocal = (byte) score;
        if (MIN_SCORE <= scoreLocal && scoreLocal <= MAX_SCORE) {
            this.setScore(scoreLocal);
        } else {
            throw new IllegalArgumentException(score + " is not between " + MIN_SCORE + " and " + MAX_SCORE);
        }
    }

    public void incrementScore(int by) {
        this.setSafeScore(this.getScore() + by);
    }

    @Override
    public String toString() {
        return "Score{" +
                "auction=" + score +
                '}';
    }


    @Override
    public int compareTo(Score other) {
        return Byte.compare(this.score, other.score);
    }
}
