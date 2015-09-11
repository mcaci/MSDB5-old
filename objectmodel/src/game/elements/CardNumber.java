package game.elements;

/**
 * Created by nikiforos on 23/08/15.
 */
public enum CardNumber {
    ASSO {
        @Override
        public int pointsForTheCard() {
            return 11;
        }
    },
    DUE,
    TRE {
        @Override
        public int pointsForTheCard() {
            return 10;
        }
    },
    QUATTRO,
    CINQUE,
    SEI,
    SETTE,
    DONNA {
        @Override
        public int pointsForTheCard() {
            return 2;
        }
    },
    CAVALLO {
        @Override
        public int pointsForTheCard() {
            return 3;
        }
    },
    RE {
        @Override
        public int pointsForTheCard() {
            return 4;
        }
    };

    public int pointsForTheCard() {
        return 0;
    }

    public int valueOfTheCard() {
        int value = 0;
        if (this.pointsForTheCard() > 0) {
            value = this.pointsForTheCard() + 10;
        } else {
            value = this.ordinal();
        }
        return value;
    }

    @Override
    public String toString() {
        return "CardNumber{" +
                "name=" + name() +
                ", points=" + pointsForTheCard() +
                ", value=" + valueOfTheCard() +
                '}';
    }

}
