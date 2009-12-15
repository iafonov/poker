package poker;

public class Card implements Comparable<Card> {
    private Suit _suit;
    private Rank _rank;

    // Rank then Suit
    public Card(String cardStr) throws Exception {
        try {
            _rank = Rank.forString(cardStr.substring(0, 1));
            _suit = Suit.forString(cardStr.substring(1, 2));
        } catch (Exception e) {
            throw new Exception("No such card " + cardStr);
        }
    }

    public int getOrdinalValue() {
        return _rank.ordinal();
    }

    @Override
    public String toString() {
        return _rank.toString() + _suit.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return getOrdinalValue() == ((Card) obj).getOrdinalValue();
    }

    @Override
    public int compareTo(Card c) {
        return new Integer(getOrdinalValue()).compareTo(c.getOrdinalValue());
    }

    public Rank getRank() {
        return _rank;
    }

    public Suit getSuit() {
        return _suit;
    }

    public boolean is(Rank rank) {
        return _rank.equals(rank);
    }

    public boolean is(Suit suit) {
        return _suit.equals(suit);
    }
}
