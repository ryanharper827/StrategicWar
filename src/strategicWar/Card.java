package strategicWar;

import java.util.Comparator;

/**
 * Represents a standard card in a deck of playing cards.
 *
 */
public class Card {
	private int suite;
	private int value;
	
	/**
	 * Instantiate a new card.
	 * @param suite The integer representation of the suite (0=Hearts, 1=Spades, 2=Diamonds, 3=Clubs).
	 * @param value The integer value of the card, must be greater than or equal to one and less than fourteen.
	 */
	public Card(int suite, int value)
	{
		this.suite = suite;
		this.value = value;
	}
	
	/**
	 * Returns the integer value of the card.
	 * @return value of the card
	 */
	public int getValue()
	{
		return this.value;
	}
	
	/**
	 * Returns the integer representation of the card suite
	 * @return the suite of the card
	 */
	public int getSuite()
	{
		return this.suite;
	}
	
    /**
     * Get Card value comparator
     * @return Comparator that sorts by value
     */
    public static Comparator<Card> getCompByValue()
    {
        return new 
        Comparator<Card>() {
            public int compare(Card s1, Card s2)
            {
            	if(s1.getValue() > s2.getValue())
            	{
            		return 1;
            	}
            	else if(s1.getValue() < s2.getValue())
            	{
            		return -1;
            	}
            	else 
            	{
            		return 0;
            	}
            }
        };
    }
    
    /**
     * Get an abbreviated string representation of the card
     * @return A string representation of the card.
     */
    public String toAbbrevString()
    {
		String string = "";

		if(this.value <= 10 && this.value != 1)
		{
			string += this.value;
		}
		else
		{
			switch(this.value)
			{
			case 1:
				string += "A";
				break;
			case 11:
				string += "J";
				break;
			case 12:
				string += "Q";
				break;
			case 13:
				string += "K";
				break;
			}
		}
		switch(this.suite)
		{
		case 0:
			string += "H";
			break;
		case 1:
			string += "S";
			break;
		case 2:
			string += "D";
			break;
		case 3:
			string += "C";
		}
		return string;
    }
	
	/**
	 * Gets the string representation of the card. useful for debugging.
	 * @return string representation of card
	 */
	public String toString()
	{
		String suite = "";
		String value = "";
		switch(this.suite)
		{
		case 0:
			suite = "Hearts";
			break;
		case 1:
			suite = "Spades";
			break;
		case 2:
			suite = "Diamonds";
			break;
		case 3:
			suite = "Clubs";
		}
		if(this.value <= 10 && this.value != 1)
		{
			value = ""+this.value;
		}
		else
		{
			switch(this.value)
			{
			case 1:
				value = "Ace";
				break;
			case 11:
				value = "Jack";
				break;
			case 12:
				value = "Queen";
				break;
			case 13:
				value = "King";
				break;
			}
		}
		return value + " of " + suite;
	}
}
