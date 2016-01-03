package game.cardset.card;

/**
 * All methods + briscola info are unused
 * Created by nikiforos on 23/08/15.
 */
@Deprecated
public enum EnhancedCardSuit {
    ORO, COPPE, SPADE, BASTONI;

    private boolean isBriscola = false;

    public boolean isBriscola() {
        return isBriscola;
    }

    public void resetBriscola() {
        final EnhancedCardSuit[] enhancedCardSuits = EnhancedCardSuit.values();
        for (int i = 0; i < enhancedCardSuits.length; i++) {
            final EnhancedCardSuit EnhancedCardSuit = enhancedCardSuits[i];
            EnhancedCardSuit.isBriscola = false;
        }
    }

    public void setAsBriscola() {
        final EnhancedCardSuit[] enhancedCardSuits = EnhancedCardSuit.values();
        for (int i = 0; i < enhancedCardSuits.length; i++) {
            final EnhancedCardSuit EnhancedCardSuit = enhancedCardSuits[i];
            EnhancedCardSuit.isBriscola = (this == EnhancedCardSuit);
        }
    }

    @Override
    public String toString() {
        return "EnhancedCardSuit{" + this.name() + ", " +
                "isBriscola=" + isBriscola +
                '}';
    }
}
