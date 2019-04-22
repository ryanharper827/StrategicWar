package strategicwar;

import java.util.ArrayList;

public class DeckFactory {
    private DeckFactory(){}
    public static DeckFactory getInstance(){return instance;}
    private static DeckFactory instance = new DeckFactory();
    public Deck createDeck()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for(int suite = 0; suite < 4; suite++)
        {
            for(int value= 1; value <= 13; value++)
            {
                Card card = new Card(suite, value);
                cards.add(card);
            }
        }
        Deck deck = new Deck(cards);
        return deck;
    }
}
