package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Cards implements Cloneable, Iterable<Card> {
	private List<Card> _cards = new ArrayList<Card>();
	
	public Cards() {
	}
	
	public Cards(List<Card> cards) {
		_cards = cards;
	}
	
	public Cards(String cards) throws Exception {
		for (String cardStr : cards.trim().split("\\p{Space}")) {			
			_cards.add(new Card(cardStr));			
		}			
	}					

	@Override
	public Cards clone() {
		Cards cards = new Cards();
		cards._cards = new ArrayList<Card>(_cards);
		return cards;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Card card : this) {
			result += card + " ";
		}		
		return result.trim();
	}		
	
	@Override
	public boolean equals(Object obj) {
		Cards cards = (Cards) obj;
		
		if (size() != cards.size()) {
			return false;
		}
		
		for (Card card : this) {
			if (!cards.contains(card)) {
				return false;
			}
		}
			
		return true;		
	}
	
	@Override
	public Iterator<Card> iterator() {	
		return _cards.iterator();		
	}
	
	public Hand getHand() {
		if (hasRoyalFlush()) {
			return Hand.ROYAL_FLUSH;
		}
		
		if (hasStraight() && hasFlush()) {
			return Hand.STRAIGHT_FLUSH;
		}
		
		if (hasStraight()) {
			return Hand.STRAIGHT;
		}
		
		if (hasFlush()) {
			return Hand.FLUSH;
		}
		
		if (hasFourOfAKind()) {
			return Hand.FOUR_OF_A_KIND;
		}
		
		if (hasFullHouse()) {
			return Hand.FULL_HOUSE;
		}
		
		if (hasThreeOfAKind()) {
			return Hand.THREE_OF_A_KIND;
		}
		
		if (hasTwoPairs()) {
			return Hand.TWO_PAIR;
		}
		
		if (hasPair()) {
			return Hand.ONE_PAIR;
		}
		
		return Hand.NO_PAIR;
	}
	
	private boolean hasTwoPairs() {
		if (hasPair()) {
			return getWithout(getNOfAKind(2)).hasPair();
		}
		
		return false;
	}

	private boolean hasFullHouse() {
		if (hasThreeOfAKind()) {
			return getWithout(getNOfAKind(3)).hasPair();
		}
		
		return false;
	}

	private boolean hasPair() {
		return getNOfAKind(2).notEmpty();
	}

	private Cards getWithout(Cards cards) {
		Cards result = new Cards();
		
		for (Card card : this) {
			if (!cards.contains(card)) {
				result.add(card);
			}
		}
		
		return result;
	}	

	private boolean hasThreeOfAKind() {
		return getNOfAKind(3).notEmpty();
	}

	private boolean hasFourOfAKind() {		
		return getNOfAKind(4).notEmpty();
	}

	private Cards getNOfAKind(int n) {
		for (Card card : this) {
			if (Collections.frequency(_cards, card) == n) {
				return getSliceByRank(card.getRank());
			}
		}
		
		return new Cards();
	}	

	private boolean hasStraight() {				
		return getStraight().notEmpty();
	}	

	public Cards getStraight() {
		Cards sortedWithHighAce = getSorted().withHighAce();
		Cards sortedWithLowAce = getSorted();
		
		if (size() >= 5) {
			for (int startCard = size()- 5; startCard >= 0; startCard--) {
				Cards straight = sortedWithHighAce.getStraightStartingFrom(startCard);
				if (straight.size() == 5) {
					return straight;
				}
				
				straight = sortedWithLowAce.getStraightStartingFrom(startCard);
				if (straight.size() == 5) {
					return straight;
				}				
			}
		}
		
		return new Cards();
	}

	private Cards getStraightStartingFrom(int startCard) {
		int endCard = startCard;
		int startOrdinal = _cards.get(startCard).getOrdinalValue();
		for (int i = startCard; i < startCard + 5; i++) {
			if (_cards.get(i).getOrdinalValue() == startOrdinal + (i - startCard)) {
				endCard++;
			} else {
				break;
			}
		}
		
		// High ace workaround
		if (endCard < size()) {
			if (_cards.get(endCard - 1).is(Rank.KING) && _cards.get(endCard).is(Rank.ACE)) {
				endCard++;
			}
		}
		
		return new Cards(_cards.subList(startCard, endCard));
	}

	private boolean hasRoyalFlush() {
		if (size() >= 5) {			
			Cards highestFive = getSorted().withHighAce().getLast(5);
			return highestFive.getLastCard().is(Rank.ACE) && highestFive.hasFlush() && highestFive.hasStraight();
		}
		
		return false;
	}			

	private boolean hasFlush() {
		return getFlush().notEmpty();
	}

	public Cards getFlush() {		
		for (Suit suit : Suit.values()) {
			Cards cards = getSliceBySuit(suit);
			
			if (cards.size() >= 5) {
				return cards;
			}
		}
		
		return new Cards();				
	}

	private Cards getSliceByRank(Rank rank) {
		Cards result = new Cards();
		
		for (Card card : this) {
			if (card.is(rank)) {
				result.add(card);
			}
		}
		
		return result;
	}
	
	private Cards getSliceBySuit(Suit suit) {
		Cards result = new Cards();
		
		for (Card card : this) {
			if (card.is(suit)) {
				result.add(card);
			}
		}
		
		return result;
		
	}

	private Cards getLast(int count) {
		Cards result = clone();
		result._cards = _cards.subList(size() - count, size());
		return result;
	}

	private Cards withHighAce() {
		Cards result = clone();		
		Collections.rotate(result._cards, - result.getSliceByRank(Rank.ACE).size());
		
		return result;
	}
	
	private Cards getSorted() {
		Cards result = clone();
		Collections.sort(result._cards);
		return result;
	}
	
	private Card getLastCard() {
		return _cards.get(size() - 1);
	}
	
	private int size() {
		return _cards.size();
	}		
	
	private boolean notEmpty() {
		return size() != 0;
	}
	
	private void add(Card card) {
		_cards.add(card);
	}
	
	private boolean contains(Card card) {
		return _cards.contains(card);
	}	
}
