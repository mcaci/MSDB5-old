package msdb5.gameplay.pregame;

import msdb5.game.player.Player;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mcaci on 4/21/17.
 */
public class AtomicAuctionTable {

    public static final int AUCTION_BASE = 60;
    public static final int AUCTION_MAX = 120;

    private AtomicInteger auctionValue;
    private Player[] players;

    public AtomicAuctionTable(AtomicInteger auctionValue, Player... players) {
        this.auctionValue = auctionValue;
        this.players = players;
    }

    public AtomicInteger getAuctionValue() {
        return auctionValue;
    }

    public Player[] getPlayers() {
        return players;
    }

}
