package strategicwar;

/**
 * EasyAI that implements the WarAI interface.
 */
public class EasyAI implements WarAI {
    /**
     * This AI selects the lowest card to battle
     * @param hand AI's hand
     * @return Card
     */
    public Card selectBattleCard(Hand hand) {
        int lowestValue = hand.getCardValue(0);
        int index = 0;
        for(int i = 1; i < hand.cardCount(); i++)
        {
            if (hand.getCardValue(i) < lowestValue)
            {
                lowestValue = hand.getCardValue(i);
                index = i;
            }
        }
        return hand.selectCard(index);
    }

    /**
     * This AI selects the highest cards as prizes
     * @param hand AI's hand
     * @param count Number of prizes
     * @return Prize Cards
     */
    public Card[] selectPrizeCards(Hand hand, int count) {
        Card[] prizes = new Card[count];
        for(int j = 0; j < count; j++)
        {
            int highestValue = hand.getCardValue(0);
            int index = 0;
            for (int i = 1; i < hand.cardCount(); i++)
            {
                if (hand.getCardValue(i) > highestValue)
                {
                    highestValue = hand.getCardValue(i);
                    index = i;
                }
            }
            prizes[j] = hand.selectCard(index);
        }
        return prizes;
    }
}
