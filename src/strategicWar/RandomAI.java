package strategicwar;

/**
 *  RandomAI that implements the WarAI interface.
 */
public class RandomAI implements WarAI {
    /**
     * This AI selects a random card to battle
     * @param hand AI's hand
     * @return Card
     */
    public Card selectBattleCard(Hand hand) {
        return hand.pickRandom();
    }

    /**
     * This AI selects random cards as prizes
     * @param hand AI's hand
     * @param count Number of prizes
     * @return Prize Cards
     */
    public Card[] selectPrizeCards(Hand hand, int count) {
        Card[] prizes = new Card[count];
        for(int j = 0; j < count; j++)
        {
            prizes[j] = hand.pickRandom();
        }
        return prizes;
    }
}
