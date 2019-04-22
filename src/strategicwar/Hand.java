package strategicwar;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Hand {
	
	ArrayList<Card> hand;
	/**
	 * Default constructor for the Hand class. Hand has no Cards in it.
	 */
	public Hand() {
		hand = new ArrayList<Card>();
	}
	/**
	 * Initializes the Hand with the set of Cards
	 * @param cards Set of Cards to be added into the Hand
	 */
	public void initialize(ArrayList<Card> cards) {
		for(Card c : cards) {
			hand.add(c);
		}
	}
	/**
	 * Returns how many Cards are in the Hand
	 * @return Amount of Cards in the Hand
	 */
	public int cardCount() {
		return hand.size();
	}
	/**
	 * Adds the Card into the end of the Hand
	 * @param card Card to be added
	 */
	public void addCard(Card card) {
		hand.add(card);
	}

	/**
	 * Adds an array of cards to the hand
	 * @param cards
	 */
	public void addCards(Card[] cards)
	{
		for(int i = 0; i < cards.length; i++)
		{
			hand.add(cards[i]);
		}
	}
	
	/**
	 * Takes a number from 0 to 4, removes and returns that Card.
	 * @param i The card number (0 to 4)
	 * @return The Card corresponding to i
	 */
	public Card selectCard(int i) {
		return hand.remove(i);
	}

	/**
	 * Take a number from 0 to 4 and returns that cards numeric value
	 * @param i the card number (0 to 4)
	 * @return The Card value corresponding to i
	 */
	public int getCardValue(int i){ return hand.get(i).getValue();}

	public String getCardImageURL(int i){ return hand.get(i).getImageURL();}

	/**
	 * Picks a random card from the Hand, removes it and returns it.
	 * @return A random card from the Hand
	 */
	public Card pickRandom() {
		Random r = new Random();
		int i = r.nextInt(hand.size());
		return hand.remove(i);
	}
	/**
	 * Returns a string representation of the Hand. Shown as Hand[]
	 * with each card in plain words in the brackets.
	 */
	@Override
	public String toString() {
		String returnString = "Hand[ ";
		
		for(Card c : hand) {
			returnString = returnString + " [" + c.toString() + "] ";
		}
		
		returnString = returnString + "]";
		return returnString;
	}
}
