package strategicWar;

import java.util.ArrayList;

public class War extends Battle{
	//TODO Implement getWinner override by using last added cards in arrays
	ArrayList<Card> playerCards;
	ArrayList<Card> aiCards;
	//TODO Finish commenting methods
	/**
	 * Initializes the War object and places the player and
	 * AI cards into their respective internal storage.
	 * @param player Player card from the Battle that resulted in this War
	 * @param ai AI card from the Battle that resulted in this War
	 */
	public War(Card player, Card ai) {
		super(player, ai);
		playerCards = new ArrayList<Card>();
		playerCards.add(playerCard);
		aiCards = new ArrayList<Card>();
		playerCards.add(aiCard);
	}
	
	public void addAdditional(Card player, Card ai) {
		playerCards.add(player);
		aiCards.add(ai);
	}

	
	@Override
	public ArrayList<Card> getCards() {
		ArrayList<Card> returnCards = new ArrayList<Card>();
		returnCards.addAll(playerCards);
		returnCards.addAll(aiCards);
		return returnCards;
	}
}
