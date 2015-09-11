package game.elements;

/**
 * Created by nikiforos on 23/08/15.
 */
public enum CardSuit {
    ORO, COPPE, SPADE, BASTONI;

    private boolean isBriscola = false;

    public boolean isBriscola() {
        return isBriscola;
    }

    public void setAsBriscola() {
        final CardSuit[] cardSuits = CardSuit.values();
        for (int i = 0; i < cardSuits.length; i++) {
            final CardSuit cardSuit = cardSuits[i];
            cardSuit.isBriscola = this == cardSuit;
        }
    }

}
