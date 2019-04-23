package strategicwar;

/**
 * Interface that requires AI's to implement methods that define the game's choices.
 */
public interface WarAI {
    public abstract Card selectBattleCard(Hand hand);
    public abstract Card[] selectPrizeCards(Hand hand, int count);
}
