package poker;

public enum Rank {
    BLANK(""), // just for ordinal
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String stringRep;

    private Rank(String stringRep) {
        this.stringRep = stringRep;
    }

    public String toString() {
        return stringRep;
    };

    public static Rank forString(String str) throws Exception {
        for (Rank rank : Rank.values()) {
            if (rank.toString().equalsIgnoreCase(str)) {
                return rank;
            }
        }

        throw new Exception("No such rank as " + str);
    }
}
