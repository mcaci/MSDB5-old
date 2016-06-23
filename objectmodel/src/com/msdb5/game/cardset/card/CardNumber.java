package com.msdb5.game.cardset.card;

/**
 * Created by nikiforos on 23/08/15.
 */
public enum CardNumber {
    ASSO(10) {
        @Override
        public byte pointsForTheCard() {
            return 11;
        }
    },
    DUE(1),
    TRE(9) {
        @Override
        public byte pointsForTheCard() {
            return 10;
        }
    },
    QUATTRO(2),
    CINQUE(3),
    SEI(4),
    SETTE(5),
    DONNA(6) {
        @Override
        public byte pointsForTheCard() {
            return 2;
        }
    },
    CAVALLO(7) {
        @Override
        public byte pointsForTheCard() {
            return 3;
        }
    },
    RE(8) {
        @Override
        public byte pointsForTheCard() {
            return 4;
        }
    };

    private final int weight;

    CardNumber(int weight) {
        this.weight = weight;
    }

    public byte pointsForTheCard() {
        return 0;
    }

    @Override
    public String toString() {
        return "CardNumber{" +
                "name=" + name() +
                ", weight=" + getWeight() +
                ", points=" + pointsForTheCard() +
                '}';
    }

    public int getWeight() {
        return weight;
    }
}
