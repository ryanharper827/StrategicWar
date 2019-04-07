package strategicwar;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testToString() {
		String cardName = "Queen of Hearts";
		Card card = new Card(0,12);
		assertTrue(cardName.compareTo(card.toString()) == 0);
	}

}
