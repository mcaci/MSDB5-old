package game.elements.player.auction;

import game.elements.player.strategy.auction.IAuctionPersonality;

/**
 * Created by nikiforos on 04/09/15.
 */
public class Score implements Comparable<Score> {

    public static final byte MIN_SCORE = IAuctionPersonality.MIN_AUCTION_SCORE;
    public static final byte MAX_SCORE = IAuctionPersonality.MAX_AUCTION_SCORE;

    private byte score = MIN_SCORE;

    public byte getScore() {
        return score;
    }

    private void setScore(byte score) {
        this.score = score;
    }

    public void setSafeScore(int score) {
        byte scoreLocal = (byte) score;
        if (!(MIN_SCORE <= scoreLocal && scoreLocal <= MAX_SCORE)) {
            scoreLocal = (byte) Math.max(scoreLocal, MIN_SCORE);
            scoreLocal = (byte) Math.min(scoreLocal, MAX_SCORE);
        }
        this.setScore(scoreLocal);
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
