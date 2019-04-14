package strategicwar;

/**
 * Abstract AI class that requires AI's to implement methods that define the game's choices.
 */
public abstract class WarAI {
    public abstract Card selectBattleCard(Hand hand);
    public abstract Card[] selectPrizeCards(Hand hand, int count);
}
