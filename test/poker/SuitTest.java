package poker;

import poker.Suit;
import junit.framework.TestCase;

public class SuitTest extends TestCase {
    public void testToString() throws Exception {
        assertEquals("C", Suit.CLUBS.toString());
    }

    public void testFromString() throws Exception {
        assertEquals(Suit.CLUBS, Suit.forString("C"));
    }
}
