package poker;

import poker.Card;
import junit.framework.TestCase;

public class CardTest extends TestCase {
    public void testCardInit() throws Exception {
        assertEquals("2C", new Card("2C").toString());
        assertEquals("AH", new Card("AH").toString());
    }

    public void testOrdinal() throws Exception {
        assertEquals(3, new Card("3H").getOrdinalValue());
        assertEquals(10, new Card("TD").getOrdinalValue());
        assertEquals(12, new Card("QD").getOrdinalValue());
    }
}
