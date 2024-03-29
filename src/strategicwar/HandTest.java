package strategicwar;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;

/**
 * Designed to test the Hand class
 */
public class HandTest {
	@Rule
	public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

	@Test
	public void testConstructor() {
		Hand hand = new Hand();
		assertTrue(hand.cardCount() == 0);
	}
	
	@Test
	public void testInitialize() {
		Hand hand = new Hand();
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i = 0; i < 4; i++) {
			cards.add(new Card(i,i + 1));
		}
		hand.initialize(cards);
		assertTrue(hand.cardCount() == 4);
	}
	
	@Test
	public void testAddCard() {
		Hand hand = new Hand();
		hand.addCard(new Card(1, 1));
		assertTrue(hand.cardCount() == 1);
		assertTrue(hand.selectCard(0).getSuite() == 1);
	}
	
	@Test
	public void testCardCount() {
		Hand hand = new Hand();
		hand.addCard(new Card(1, 1));
		assertTrue(hand.cardCount() == 1);
	}
	
	@Test
	public void testSelectCard() {
		Hand hand = new Hand();
		hand.addCard(new Card(1, 1));
		assertTrue(hand.selectCard(0).getSuite() == 1);
	}
	
	@Test
	public void testPickRandom() {
		Hand hand = new Hand();
		for(int i = 0; i < 4; i++) {
			hand.addCard(new Card(i,i + 1));
		}
		Card c = hand.pickRandom();
		assertTrue(hand.cardCount() == 3);
	} 
}
