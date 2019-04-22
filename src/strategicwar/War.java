package strategicwar;

import java.util.ArrayList;

public class War extends Battle{
	ArrayList<Card> playerPrizes;
	ArrayList<Card> aiPrizes;
	/**
	 * Initializes the War object and places the player and
	 * AI cards into their respective internal storage.
	 * @param player Player card from the Battle that resulted in this War
	 * @param ai AI card from the Battle that resulted in this War
	 */
	public War(Card player, Card ai, ArrayList<Card> playerPrizes, ArrayList<Card> aiPrizes)
	{
		super(player, ai);
		this.playerPrizes = new ArrayList<Card>();
		this.aiPrizes = new ArrayList<Card>();
		for(Card c : playerPrizes) {
			this.playerPrizes.add(c);
		}
		for(Card c : aiPrizes) {
			this.aiPrizes.add(c);
		}
	}
	
	/**
	 * 
	 * @param player Player's battle Card
	 * @param ai AI's battle Card
	 * @param playerPrizes Additional player prize Cards
	 * @param aiPrizes Additional AI prize Cards 
	 */
	public void additionalWar(Card player, Card ai, ArrayList<Card> playerPrizes, ArrayList<Card> aiPrizes)
	{
		this.playerPrizes.add(this.playerCard);
		this.aiPrizes.add(this.aiCard);
		this.playerPrizes.addAll(playerPrizes);
		this.aiPrizes.addAll(aiPrizes);
		this.aiCard = ai;
		this.playerCard = player;
	}

	
	/**
	 * Get all Cards from the War
	 */
	@Override
	public ArrayList<Card> getCards()
	{
		ArrayList<Card> returnCards = new ArrayList<Card>();
		returnCards.add(this.aiCard);
		returnCards.add(this.playerCard);
		returnCards.addAll(this.playerPrizes);
		returnCards.addAll(this.aiPrizes);
		return returnCards;
	}
}
