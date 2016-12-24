package com.msdb5.game.player.info;

/**
 * Created by nikiforos on 04/09/15.
 */
public class AuctionScore implements Comparable<AuctionScore> {

    public static final byte MIN_SCORE = 60;
    public static final byte MAX_SCORE = 120;

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
        return "AuctionScore{" +
                "auction=" + score +
                '}';
    }


    @Override
    public int compareTo(AuctionScore other) {
        return Byte.compare(this.score, other.score);
    }
}
