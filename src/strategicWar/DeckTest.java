package strategicWar;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class DeckTest {

	@Test
	public void testAddCard() {
		Card card = new Card(0,1);
		Deck deck = new Deck(true);
		deck.addCard(card);
		assertTrue(deck.getCards()[0].equals(card));
	}
	
	@Test
	public void testAddCards()
	{
		Card[] cards = new Card[] {new Card(0,1), new Card(0,2)};
		Deck deck = new Deck(true);
		deck.addCards(cards);
		Comparator<Card> comp = Card.getCompByValue();
		for(Card card : cards)
		{
			assertTrue(comp.compare(card, deck.drawCard()) == 0);
		}
	}
	
	@Test
	public void testDrawCard()
	{
		Deck deck = new Deck(false);
		for(int i = 0; i < 52; i++)
		{
			Card c = deck.drawCard();
			assertTrue(c != null);
		}
		Card c = deck.drawCard();
		assertTrue(c == null);
	}
	
	@Test
	public void testSplitDeck()
	{
		Deck deck = new Deck(false);
		Deck[] splitDecks = Deck.splitDeck(deck);
		assertTrue(splitDecks[0].getSize() == 26);
		assertTrue(splitDecks[0].getSize() == 26);
		Card card1 = new Card(0,1);
		Card card2 = new Card(0,2);
		deck = new Deck(new Card[]{card1, card2});
		splitDecks = Deck.splitDeck(deck);
		assertTrue(splitDecks[0].drawCard().equals(card1));
		assertTrue(splitDecks[1].drawCard().equals(card2));
	}

}
