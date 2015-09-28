package game.elements.player.impl.msdb5;

import game.elements.player.Player;
import game.elements.player.strategy.auction.IAuctionAction;
import game.elements.player.strategy.auction.msdb5.AuctionAction_BuonCompagno;

/**
 * Created by nikiforos on 11/09/15.
 * Il Buon Compagno: Nessuno si aspetta chissà cosa da lui, considerando che nelle partite precedenti ha alternato
 * qualcosa di buono a moltissime cose non buone. Ma proprio per questo motivo risulterà il vincitore incontrastato
 * della serata, con evidente stupore di tutti i presenti (lui compreso). La sua serata inizia nel migliore dei modi
 * e al primo turno fa una mossa così contorta, ma così contorta, che neanche lui sa bene cosa abbia fatto.
 * La sua fortuna è quella di non avere mai chissà quali carte ma sempre quelle essenziali per essere appunto
 * un buon compagno. L'unico problema di questo giocatore (che poi sono io) è che a briscola in 5 non si gioca a soldi,
 * quindi questo è il solo motivo della sua vittoria. Sta ancora maledicendo chi ha inventato il Poker Texano.
 */
public class BuonCompagno extends Player {
    public BuonCompagno(IAuctionAction auctionAction) {
        super(new AuctionAction_BuonCompagno());
    }
}
