package game.elements.player.impl.msdb5;

import game.elements.player.Player;
import game.elements.player.strategy.auction.IAuctionAction;
import game.elements.player.strategy.auction.msdb5.AuctionAction_Rialzatore;

/**
 * Created by nikiforos on 11/09/15.
 * Il Rialzatore: Questo personaggio ha un solo scopo nella vita...alzare la posta iniziale senza alcun motivo plausibile.
 * Allora, per chi non lo sapesse nella briscola in 5 si parte da un minimo punteggio di 60 ad un massimo di 120 come posta iniziale;
 * bene, questo losco individuo deve sempre, e dico sempre, partire con un minimo di 80 per poi ad ogni rilancio
 * saltare a nove a nove. La cosa innervosisce gli altri giocatori (specialmente il Dubbioso)
 * e provoca un senso misto di stizza e vomito in tutti i presenti.
 * Sfortunatamente gioca in casa quindi non può essere contraddetto.
 */
public class Rialzatore extends Player {
    public Rialzatore(IAuctionAction auctionAction) {
        super(new AuctionAction_Rialzatore());
    }
}
