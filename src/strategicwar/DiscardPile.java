package strategicwar;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a pile of discarded cards in a WarGame.
 */
public class DiscardPile {
	ArrayList<Card> pile;
	/**
	 * Default constructor for the DiscardPile class. Initializes empty.
	 */
	public DiscardPile() {
		pile = new ArrayList<Card>();
	}

	/**
	 * Returns the amount of Card objects in the DiscardPile
	 * @return number of Cards in this DiscardPile
	 */
	public int cardCount() {
		return pile.size();
	}
	
	/**
	 * Inserts the Card object at the top of the DiscardPile.
	 * For adding multiple cards, see addCards.
	 * @param card The Card object to be added to the DiscardPile
	 */
	public void addCard(Card card) {
		pile.add(card);
		
	}

	/**
	 * Returns the top card in the DiscardPile (most recently added) 
	 * without removing it.
	 * @return Returns the top Card in the DiscardPile
	 */
	public Card peek() {
		return pile.get(pile.size() - 1);
	}

	/**
	 * Adds all Cards from a collection of Card objects to the DiscardPile.
	 * @param cards Collection of Cards to be added to the DiscardPile
	 */
	public void addCards(Collection<Card> cards) {
		for(Card c : cards) {
			pile.add(c);
		}
		
	}
	
	/**
	 * Removes all cards from the DiscardPile and returns an ArrayList of Card objects.
	 * For removing individual Cards, see removeCard
	 * @return the cards the pile contains
	 */
	public ArrayList<Card> removeCards() {
		ArrayList<Card> removed = new ArrayList<Card>();
		int size = pile.size();
		for(int i = 0; i < size; i++) {
			removed.add(pile.remove(size - 1 - i));
		}
		return removed;		
	}
	/**
	 * Removes the top card from the DiscardPile and returns it.
	 * @return Returns the top Card of the DiscardPile
	 */
	public Card removeCard() {
		if(pile.isEmpty()) {
			return null;
		}
		return pile.remove(pile.size() - 1);
	}
	
	/**
	 * Returns a string representation of the DiscardPile. Shown as DiscardPile[]
	 * with each card in plain words in the brackets.
	 */
	public String toString() {
		String returnString = "DiscardPile[ ";
		
		for(Card c : pile) {
			returnString = returnString + " [" + c.toString() + "] ";
		}
		
		returnString = returnString + "]";
		return returnString;
	}
}
