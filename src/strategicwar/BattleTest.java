package strategicwar;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;

/**
 * Designed to test the Battle class.
 */
public class BattleTest {
	
	//@Rule
	//public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();
	
	@Test
	public void testConstructor() {
		Card player = new Card(1, 1);
		Card ai = new Card(2, 2);
		try {
			Battle battle = new Battle(player, ai);
			assertTrue(true);
		}
		catch(Exception e) {
			fail("Constructor threw exception.");
		}
	}
	
	@Test
	public void testGetWinner() {
		//Tests AI win
		Card player = new Card(1, 1);
		Card ai = new Card(2, 2);
		Battle battle = new Battle(player, ai);
		assertTrue(battle.getWinner() == 0);
		//Tests Player win
		player = new Card(2, 2);
		ai = new Card(1, 1);
		battle = new Battle(player, ai);
		assertTrue(battle.getWinner() == 1);
		
		//Tests War
		player = new Card(1, 1);
		ai = new Card(1, 1);
		battle = new Battle(player, ai);
		assertTrue(battle.getWinner() == 2);
	}
	
	@Test
	public void testGetCards() {
		Card player = new Card(1, 1);
		Card ai = new Card(2, 2);
		Battle battle = new Battle(player, ai);
		ArrayList<Card> cards = battle.getCards();
		assertTrue(cards.size() == 2);
	}
}
