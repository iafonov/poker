package poker;

public enum Suit {
	DIAMONDS, CLUBS, HEARTS, SPADES;
	
	public String toString() {		
		return this.name().substring(0, 1);
	};
	
	public static Suit forString(String str) throws Exception {
		for (Suit suit : Suit.values()) {
			if (suit.toString().equalsIgnoreCase(str)) {
				return suit;
			}
		}
		
		throw new Exception("No such suit for" + str);
	}
}
