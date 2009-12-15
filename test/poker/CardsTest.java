package poker;

import poker.Cards;
import poker.Hand;
import junit.framework.TestCase;

//DIAMONDS, CLUBS, HEARTS, SPADES;
public class CardsTest extends TestCase {
	public void testCreateCards() throws Exception {
		assertEquals("3C 3D", new Cards("3C 3D").toString());		
	}
	
	public void testRoyalFlush() throws Exception {
		assertEquals(Hand.ROYAL_FLUSH, new Cards("TD JD QD KD AD").getHand());
		assertEquals(Hand.ROYAL_FLUSH, new Cards("3C TD JD QD KD AD 5H").getHand());
		assertEquals(Hand.ROYAL_FLUSH, new Cards("TD JD QD KD AD 9D").getHand());		
	}
	
	public void testStraightFlush() throws Exception {
		assertEquals(Hand.STRAIGHT_FLUSH, new Cards("9D TD JD QD KD").getHand());
	}
	
	public void testStraight() throws Exception {
		assertEquals(Hand.STRAIGHT, new Cards("9C TD JD QC KD AD 2H").getHand());
		assertEquals(new Cards("TD JD QC KD AD"), new Cards("9C TD JD QC KD AD 2H").getStraight());
		
		assertEquals(Hand.STRAIGHT, new Cards("AD 2D 3C 4D 5D").getHand());
		assertEquals(new Cards("AD 2D 3C 4D 5D"), new Cards("AD 2D 3C 4D 5D").getStraight());
		
		assertEquals(Hand.STRAIGHT, new Cards("6C AD 2D 3C 4D 5D").getHand());
		assertEquals(new Cards("2D 3C 4D 5D 6C"), new Cards("6C AD 2D 3C 4D 5D").getStraight());		
	}
	
	public void testFlush() throws Exception {
		assertEquals(Hand.FLUSH, new Cards("3C 2H 3D TD JD QD KD").getHand());		
		assertEquals(Hand.FLUSH, new Cards("3C 2H 4H TH JH QD KH").getHand());		
		assertEquals(Hand.FLUSH, new Cards("3D TD JD QD KD").getHand());
	}
	
	public void testFourOfAKind()  throws Exception {
		assertEquals(Hand.FOUR_OF_A_KIND, new Cards("3C 2D 2C 2H 2S 4D").getHand());
		assertEquals(Hand.FOUR_OF_A_KIND, new Cards("TD AD AC AH AS 7D").getHand());
	}
	
	public void testFullHouse()  throws Exception {
		assertEquals(Hand.FULL_HOUSE, new Cards("3C 2D 2C 3H 3S 6D").getHand());
		assertEquals(Hand.FULL_HOUSE, new Cards("AC AD 2C 3H 2S 2D").getHand());
	}
	
	public void testThreeOfAKind()  throws Exception {
		assertEquals(Hand.THREE_OF_A_KIND, new Cards("3C 2D TC 2H 2S 4D").getHand());		
	}
	
	public void testTwoPairs()  throws Exception {
		assertEquals(Hand.TWO_PAIR, new Cards("3C 2D TC 2H 3S 4D").getHand());
		assertEquals(Hand.TWO_PAIR, new Cards("3C 2D TC 4H 3S 4D").getHand());
	}
	
	public void testPair()  throws Exception {
		assertEquals(Hand.ONE_PAIR, new Cards("3C 2D TC AH 3S 4D").getHand());
		assertEquals(Hand.ONE_PAIR, new Cards("3C 2D TC AH 3S 4D").getHand());
	}
	
	public void testNoPair() throws Exception {
		assertEquals(Hand.NO_PAIR, new Cards("2S JD QD KD AD").getHand());
		assertEquals(Hand.NO_PAIR, new Cards("3C TD JD 2S KD AD 5H").getHand());
		assertEquals(Hand.NO_PAIR, new Cards("TD JC 4C KD AD 9D").getHand());
		
		assertEquals(Hand.NO_PAIR, new Cards("AD 2D 3C 4D 6D").getHand());
	}
}
