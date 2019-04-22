package strategicwar;

import java.util.ArrayList;

/**
 * Used to create decks with 52 cards
 */
public class DeckFactory {
    private DeckFactory(){}
    public static DeckFactory getInstance(){return instance;}
    private static DeckFactory instance = new DeckFactory();

    /**
     * Create a deck with 52 standard playing cards
     * @return a full 52 card deck
     */
    public Deck createDeck()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for(int suite = 0; suite < 4; suite++)
        {
            for(int value= 2; value <= 14; value++)
            {
                Card card = new Card(suite, value);
                cards.add(card);
            }
        }
        Deck deck = new Deck(cards);
        return deck;
    }
}
