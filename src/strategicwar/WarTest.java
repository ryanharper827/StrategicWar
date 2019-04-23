package strategicwar;

import static org.junit.Assert.*;

import java.util.ArrayList;

//import org.junit.Rule;
import org.junit.Test;

/**
 * Designed to test the War class.
 */
public class WarTest {
	//@Rule
	//public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();
	
	@Test
	public void testConstructor() {
		Card player = new Card(1, 1);
		Card ai = new Card(2, 2);
		ArrayList<Card> playerPrizes = new ArrayList<Card>();
		playerPrizes.add(new Card(3,3));
		ArrayList<Card> aiPrizes = new ArrayList<Card>();
		aiPrizes.add(new Card(0,4));
		try {
			War war = new War(player, ai, playerPrizes, aiPrizes);
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
		ArrayList<Card> aiPrizes = new ArrayList<Card>();
		ArrayList<Card> playerPrizes = new ArrayList<Card>();
		aiPrizes.add(new Card(0,4));
		playerPrizes.add(new Card(3,3));
		War war = new War(player, ai, playerPrizes, aiPrizes);
		assertTrue(war.getWinner() == 0);
		//Tests Player win
		player = new Card(2, 2);
		ai = new Card(1, 1);
		war = new War(player, ai, playerPrizes, aiPrizes);
		assertTrue(war.getWinner() == 1);
		
		//Tests War
		player = new Card(1, 1);
		ai = new Card(1, 1);
		war = new War(player, ai, playerPrizes, aiPrizes);
		assertTrue(war.getWinner() == 2);
	}
	
	@Test
	public void testGetCards() {
		Card player = new Card(1, 1);
		Card ai = new Card(2, 2);
		Card player2 = new Card(1, 2);
		Card ai2 = new Card(2, 3);
		ArrayList<Card> aiPrizes = new ArrayList<Card>();
		ArrayList<Card> playerPrizes = new ArrayList<Card>();
		aiPrizes.add(new Card(0,4));
		playerPrizes.add(new Card(3,3));
		ArrayList<Card> aiPrizes2 = new ArrayList<Card>();
		ArrayList<Card> playerPrizes2 = new ArrayList<Card>();
		aiPrizes.add(new Card(0,5));
		playerPrizes.add(new Card(3,4));
		War war = new War(player, ai, playerPrizes, aiPrizes);
		war.additionalWar(player2, ai2, playerPrizes2, aiPrizes2);
		ArrayList<Card> cards = war.getCards();
		assertTrue(cards.size() == 8);
	}

}
