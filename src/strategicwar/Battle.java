package strategicwar;

import java.util.ArrayList;

/**
 * Represents a battle in the WarGame.
 */
public class Battle {
	protected Card playerCard;
	protected Card aiCard;
	
	/**
	 * Initializes the Battle with the player card and AI card
	 * @param player The player card
	 * @param ai The AI card
	 */
	public Battle(Card player, Card ai) {
		playerCard = player;
		aiCard = ai;
	}
	/**
	 * Calculates the winner of the Battle, where 0 is a win 
	 * for the AI and 1 is a win for the Player
	 * @return Returns 0 for AI win, 1 for player win and 2 for War
	 */
	public int getWinner() {
		if(playerCard.getValue() == aiCard.getValue()) {
			return 2;
		}
		if(playerCard.getValue() > aiCard.getValue()) {
			return 1;
		}
		else {return 0;}
	}

	/**
	 * Returns all the cards involved in the battle as an ArrayList
	 * @return Returns all cards involved in the battle as an ArrayList
	 */
	public ArrayList<Card> getCards() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(playerCard);
		cards.add(aiCard);
		return cards;
	}

	public String toString()
	{
		return "Player: " + this.playerCard.toAbbrevString() + " AI:" +
				this.aiCard.toAbbrevString() + " winner: " + this.getWinner();
	}

}
