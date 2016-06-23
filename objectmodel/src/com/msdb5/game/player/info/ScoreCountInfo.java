package com.msdb5.game.player.info;

/**
 * Created by nikiforos on 18/02/16.
 */
public class ScoreCountInfo {
    private byte score = 0;

    public byte getScore() {
        return score;
    }

    public void setScore(byte score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreCountInfo{" +
                "score=" + score +
                '}';
    }
}
