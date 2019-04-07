package strategicwar;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DiscardPileTest {

	@Test
	public void testConstructor() {
		DiscardPile pile = new DiscardPile();
		assertTrue(pile.cardCount() == 0);
	}
	
	@Test
	public void testAddCard() {
		DiscardPile pile = new DiscardPile();
		Card card = new Card(0, 0);
		pile.addCard(card);
		assertTrue(pile.peek() == card);
	}
	
	@Test
	public void testAddCards() {
		DiscardPile pile = new DiscardPile();
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i = 0; i < 4; i++) {
			cards.add(new Card(i,i));
		}
		pile.addCards(cards);
		assertTrue(pile.cardCount() == 4);
	}
	
	@Test
	public void testRemoveCards() {
		DiscardPile pile = new DiscardPile();
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i = 0; i < 4; i++) {
			cards.add(new Card(i,i));
		}
		pile.addCards(cards);
		pile.removeCards();
		assertTrue(pile.cardCount() == 0);
	}
}
