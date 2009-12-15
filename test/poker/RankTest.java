package poker;

import poker.Rank;
import junit.framework.TestCase;

public class RankTest extends TestCase {
	public void testToString() throws Exception {
		assertEquals("J", Rank.JACK.toString());
	}
	
	public void testForString() throws Exception {
		assertEquals(Rank.JACK, Rank.forString("J"));
	}
}
