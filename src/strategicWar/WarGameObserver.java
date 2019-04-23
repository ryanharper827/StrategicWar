package strategicwar;

/**
 * Used by any class that wants to observe changes in a WarGame.
 */
public interface WarGameObserver {
    public void victorUpdated();
    public void handsUpdated();
    public void deckUpdated();
    public void pileUpdated();
    public void scoresUpdated();
    public void prizesUpdated();
    public void battleCardsUpdated();
    public void phaseUpdated();
}
