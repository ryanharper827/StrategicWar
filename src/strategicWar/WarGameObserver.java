package strategicwar;

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
