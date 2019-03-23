package strategicWar;

import static org.junit.Assert.*;

import org.junit.*;

class CardTest {

	@Test
	void testToString() {
		String cardName = "Queen of Hearts";
		Card card = new Card(0,12);
		assertTrue(cardName.compareTo(card.toString()));
	}
}
