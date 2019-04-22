package strategicwar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cards;

	/**
	 * Instantiate an empty deck of cards.
	 */
	public Deck()
	{
		this.cards = new ArrayList<Card>();
	}


	/**
	 * Instantiate a deck of cards with an array of cards.
	 * @param cards An array of cards
	 */
	public Deck(Card[] cards)
	{
		this.cards = new ArrayList<Card>();
		for(Card card : cards)
		{
			this.cards.add(card);
		}
	}


	/**
	 * Instantiate a deck of cards with an arraylist of cards.
	 * @param cards An arraylist of cards
	 */
	public Deck(ArrayList<Card> cards)
	{
		this.cards = cards;
	}
	
	/**
	 * Add cards to the deck
	 * @param cards An array of cards
	 */
	public void addCards(Card[] cards)
	{
		for(Card card : cards)
		{
			this.cards.add(card);
		}
	}
	
	/**
	 * Add a card to the deck
	 * @param card The card to add
	 */
	public void addCard(Card card)
	{
		this.cards.add(card);
	}
	
	/**
	 * Get this decks array of cards.
	 * @return The decks array of cards
	 */
	public Card[] getCards()
	{
		return this.cards.toArray(new Card[this.cards.size()]);
	}
	
	/**
	 * Get the number of cards in the deck.
	 * @return The number of cards in the deck.
	 */
	public int getSize()
	{
		return this.cards.size();
	}
	
	/**
	 * Draw a card off the deck. Will return null if deck is empty.
	 * @return The card on top of the deck
	 */
	public Card drawCard()
	{
		if(this.cards.size() > 0)
		{
			Card card = this.cards.get(0);
			this.cards.remove(0);
			return card;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Draws the number of cards specified
	 * @param count number of cards to draw
	 * @return Card[] with drawn cards
	 */
	public Card[] drawCards(int count)
	{
		Card[] cards = new Card[count];
		for(int i = 0; i < count; i++)
		{
			cards[i] = this.drawCard();
		}
		return cards;
	}
	
	public String toString()
	{
		String string = "Deck[";
		for(int i = 0; i < this.cards.size(); i++)
		{
			string += this.cards.get(i).toAbbrevString();
			if(i != this.cards.size()-1)
				string += ",";
		}
		string += "]";
		return string;
	}
	
	/**
	 * Split a deck of cards into two decks.
	 * @param deck The deck to be split.
	 * @return Array of two decks.
	 */
	public static Deck[] splitDeck(Deck deck)
	{
		Card[] cards = deck.getCards();
		int count = cards.length/2;
		Deck[] decks = new Deck[2];
		decks[0] = new Deck(Arrays.copyOfRange(cards, 0, count));
		decks[1] = new Deck(Arrays.copyOfRange(cards, count, cards.length));
		return decks;
	}
	
	/**
	 * Shuffle a deck of cards.
	 * @param deck the deck to be shuffled
	 * @return a shuffled version of the given deck
	 */
	public static Deck shuffleDeck(Deck deck)
	{
		ArrayList<Card> shuffledCards = (ArrayList<Card>) deck.cards.clone();
		Collections.shuffle(shuffledCards);
		return new Deck(shuffledCards.toArray(new Card[shuffledCards.size()]));
	}

}
