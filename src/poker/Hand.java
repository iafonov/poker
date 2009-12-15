package poker;

public enum Hand {
    NO_PAIR,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    STRAIGHT, FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH,
    ROYAL_FLUSH;

    public String toString() {
        return name().replaceAll("_", " ").toLowerCase();
    };
}
