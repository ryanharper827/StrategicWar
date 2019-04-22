package strategicwar;

public class RandomAI extends WarAI {


    /**
     * This AI selects a random card to battle
     * @param hand AI's hand
     * @return Card
     */
    @Override
    public Card selectBattleCard(Hand hand) {
        return hand.pickRandom();
    }

    /**
     * This AI selects random cards as prizes
     * @param hand AI's hand
     * @param count Number of prizes
     * @return Prize Cards
     */
    @Override
    public Card[] selectPrizeCards(Hand hand, int count) {
        Card[] prizes = new Card[count];
        for(int j = 0; j < count; j++)
        {
            prizes[j] = hand.pickRandom();
        }
        return prizes;
    }
}
