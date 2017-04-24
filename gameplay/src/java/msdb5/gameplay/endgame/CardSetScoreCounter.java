package msdb5.gameplay.endgame;

import msdb5.game.card.Card;
import msdb5.game.card.CardNumber;
import msdb5.game.card.set.CardSet;

import java.util.Collection;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by nikiforos on 24/04/17.
 */
public class CardSetScoreCounter extends RecursiveTask<Integer> {

    private CardSet<? extends Collection<Card>> cards;
    private int start;
    private int end;

    public CardSetScoreCounter(CardSet<? extends Collection<Card>> cards) {
        this(0, cards.size(), cards);
    }

    private CardSetScoreCounter(int start, int end, CardSet<? extends Collection<Card>> cards) {
        this.cards = cards;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum;
        if (end - start <= 2) {
            sum = cards.getCardSet().stream().skip(start).limit(end - start).
                    map(Card::getCardNumber).mapToInt(CardNumber::getPoints).sum();
        } else {
            int middle = start + (end - start) / 2;
            final ForkJoinTask<Integer> forkedTask = new CardSetScoreCounter(start, middle, this.cards).fork();
            final Integer resultFromCurrentTask = new CardSetScoreCounter(middle, end, this.cards).compute();
            sum = resultFromCurrentTask + forkedTask.join();
        }
        return sum;
    }
}
