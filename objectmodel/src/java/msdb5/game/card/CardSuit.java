package msdb5.game.card;

/**
 * Created by nikiforos on 23/08/15.
 */
public enum CardSuit {
    ORO, COPPE, SPADE, BASTONI;

  @Override
    public String toString() {
        return "CardSuit{" + this.name() + "}";
    }
}
