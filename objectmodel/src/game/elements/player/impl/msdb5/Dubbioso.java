package game.elements.player.impl.msdb5;

import game.elements.player.Player;
import game.elements.player.strategy.auction.IAuctionAction;
import game.elements.player.strategy.auction.msdb5.AuctionAction_Dubbioso;

/**
 * Created by nikiforos on 11/09/15.
 * Il Dubbioso: Questo giocatore non ha grandissima esperienza di briscola ma ha dalla sua quel giusto mix di fortuna,
 * carichi e dubbi. Ogni sua mossa è decisiva, la tensione si taglia con un grissino e la quantità di sudore emesso
 * farebbe funzionare persino una centrale Nucleare. Due volte su tre capita in situazioni nelle quali fai una mossa
 * e sei spacciato o ne fai un'altra e sei portato in trionfo, e questo lo rende particolarmente nervoso.
 * Il picco massimo si raggiunge in un caso di Cappotto durante il quale con una sola sua mossa si decide l'intera partita:
 * il suo prossimo regalo di compleanno sarà un cappio portatile, completo di Boia gonfiabile.
 */
public class Dubbioso extends Player {
    public Dubbioso(IAuctionAction auctionAction) {
        super(new AuctionAction_Dubbioso());
    }
}
