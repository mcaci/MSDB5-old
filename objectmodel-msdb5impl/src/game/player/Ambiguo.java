package game.player;

import game.elements.player.Player;
import game.elements.player.strategy.auction.IAuctionPersonality;
import game.elements.player.strategy.auction.msdb5.AuctionPersonality_Ambiguo;

/**
 * Created by nikiforos on 11/09/15.
 * L'Ambiguo: Sarebbe necessario un intero trattato per cercare di spiegare bene la psiche (molto labile)
 * di questo strano essere vivente. Le sue caratteristiche principali sono due: non la smette un attimo di parlare
 * (e incazzarsi senza motivo) e cerca di confondere in tutti i modi chi invece dovrebbe aiutare.
 * Quando è un Compagno le sue sono principalmente mosse da Non-Compagno e viceversa, con ,come conseguenza,
 * la distruzione mentale degli altri giocatori (il Dubbioso in testa). Il giorno in cui qualcuno scoprirà la sua
 * tecnica di gioco probabilmente sarà stato tutto vanificato dall'apocalisse del 2012.
 */
public class Ambiguo extends Player {
    public Ambiguo(IAuctionPersonality auctionAction) {
        super(new AuctionPersonality_Ambiguo());
    }
}
