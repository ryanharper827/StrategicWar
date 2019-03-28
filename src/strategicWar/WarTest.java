package strategicWar;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class WarTest {
//TODO Fix up the tests to account for 10 cards in the war
	@Test
	public void testConstructor() {
		Card player = new Card(1, 1);
		Card ai = new Card(2, 2);
		try {
			War war = new War(player, ai);
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
		War war = new War(player, ai);
		assertTrue(war.getWinner() == 0);
		//Tests Player win
		player = new Card(2, 2);
		ai = new Card(1, 1);
		war = new War(player, ai);
		assertTrue(war.getWinner() == 1);
		
		//Tests War
		player = new Card(1, 1);
		ai = new Card(1, 1);
		war = new War(player, ai);
		assertTrue(war.getWinner() == 2);
	}
	
	@Test
	public void testGetCards() {
		Card player = new Card(1, 1);
		Card ai = new Card(2, 2);
		War war = new War(player, ai);
		war.addAdditional(player, ai);
		war.addAdditional(player, ai);
		war.addAdditional(player, ai);
		war.addAdditional(player, ai);
		ArrayList<Card> cards = war.getCards();
		assertTrue(cards.size() == 10);
	}

}
