package msdb5.gameplay.pregame;

import msdb5.game.player.Player;
import msdb5.game.player.info.AuctionStatus;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
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

    public void executeAuctionRound() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(getPlayers().length);
        for (Player player : players) {
            executorService.submit(() -> {
                while (player.getAuctionStatusFor(this::isActive)) {
                    player.actsOnAuction(this.auctionValue, player.getFoldingDecision(), player.getChooseNextScoreFunction());
                }
            });
        }
    }

    public boolean isActive(AuctionStatus status) {
        return status == AuctionStatus.NOT_STARTED || status == AuctionStatus.IN_AUCTION;
    }

    public String toString() {
        return "AtomicAuctionTable{" +
                "auctionValue=" + auctionValue +
                ", players=" + Arrays.toString(players) +
                '}';
    }
}
