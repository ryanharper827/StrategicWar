package strategicwar;

import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class GameController {
    @FXML
    private Label pHandSelectLabel0, pHandSelectLabel1, pHandSelectLabel2, pHandSelectLabel3, pHandSelectLabel4,
            victoryLabel,promptLabel,playerScoreLabel,computerScoreLabel,pDiscardLabel,pDeckLabel;
    @FXML
    private ImageView pHandImage0,pHandImage1,pHandImage2,pHandImage3,pHandImage4,pDiscardImage,pDeckImage,pPlayedImage,
            pPrizeImage0,pPrizeImage1,pPrizeImage2,cPlayedImage,cPrizeImage0,cPrizeImage1,cPrizeImage2,cHandImage0,
            cHandImage1,cHandImage2,cHandImage3,cHandImage4;
    private Image cardBackImage;

    private ImageView[] pHandImages, cHandImages, pPrizeImages, cPrizeImages;

    private static final int MAX_HAND_SIZE = 5;
    private static final int NUM_WAR_PRIZES = 2;

    private WarAI ai;
    private boolean playing;
    private Deck playerDeck,aiDeck;
    private Hand playerHand, aiHand;
    private DiscardPile playerPile, aiPile;

    private Card playerCard;
    private Card aiCard;
    private ArrayList<Card> playerPrizes;
    private ArrayList<Card> aiPrizes;

    private Battle currentBattle;
    private War currentWar;
    private boolean warLoop;

    private enum GamePhase{Draw, BattleSelection, Battle, PreWar, War, RoundEnd, GameOver}
    private GamePhase phase;

    /**
     * Start the game
     * @param difficulty
     */
    public void start(int difficulty)
    {
        if(!this.playing)
        {
            switch (difficulty)
            {
                case 1:
                    this.ai = new HardAI();
                    break;
                default:
                    this.ai = new EasyAI();
                    break;
            }
            this.playing = true;
            this.initialize();
            this.drawPhase();
        }
    }

    /**
     * Setup the required game objects for a new game
     */
    private void initialize()
    {
        this.cardBackImage = new Image("/resources/Cards/card_back.png");
        this.pHandImages = new ImageView[5];
        this.pHandImages[0] = this.pHandImage0;
        this.pHandImages[1] = this.pHandImage1;
        this.pHandImages[2] = this.pHandImage2;
        this.pHandImages[3] = this.pHandImage3;
        this.pHandImages[4] = this.pHandImage4;
        this.cHandImages = new ImageView[5];
        this.cHandImages[0] = this.cHandImage0;
        this.cHandImages[1] = this.cHandImage1;
        this.cHandImages[2] = this.cHandImage2;
        this.cHandImages[3] = this.cHandImage3;
        this.cHandImages[4] = this.cHandImage4;
        this.pPrizeImages = new ImageView[3];
        this.pPrizeImages[0] = this.pPrizeImage0;
        this.pPrizeImages[1] = this.pPrizeImage1;
        this.pPrizeImages[2] = this.pPrizeImage2;
        this.cPrizeImages = new ImageView[3];
        this.cPrizeImages[0] = this.cPrizeImage0;
        this.cPrizeImages[1] = this.cPrizeImage1;
        this.cPrizeImages[2] = this.cPrizeImage2;
        this.warLoop = false;
        this.playerPrizes = new ArrayList<Card>();
        this.aiPrizes = new ArrayList<Card>();
        Deck mainDeck = new Deck(false);
        //Took out unnecessary toString calls on mainDeck
        System.out.println("Preshuffle: " + mainDeck + "\nCount: " + mainDeck.getSize());
        mainDeck = Deck.shuffleDeck(mainDeck);
        System.out.println("Postshuffle: " + mainDeck + "\nCount: " + mainDeck.getSize());
        Deck[] decks = Deck.splitDeck(mainDeck);
        this.playerDeck = decks[0];
        this.aiDeck = decks[1];
        System.out.println("Player Deck: " + this.playerDeck + " AI deck: " + this.aiDeck);
        System.out.println("Player count: " + this.playerDeck.getSize() + " AI count: " + this.aiDeck.getSize());
        this.playerHand = new Hand();
        this.aiHand = new Hand();
        this.playerPile = new DiscardPile();
        this.aiPile = new DiscardPile();
        System.out.println("New Game Setup");
    }

    /**
     * DRAW PHASE: Refresh player and AI hands before proceeding to SELECTION PHASE
     */
    private void drawPhase()
    {
        System.out.println("Draw phase");
        this.phase = GamePhase.Draw;
        this.drawCards(this.playerDeck, this.playerPile, this.playerHand);
        System.out.println("Player hand: " + this.playerHand.toString());
        this.drawCards(this.aiDeck, this.aiPile, this.aiHand);
        System.out.println("AI hand: " + this.aiHand.toString());
        this.refreshHandImages();
        this.refreshCounterLabels();
        this.selectionPhase();
    }

    /**
     * SELECTION PHASE: AI selects a card and unlocks player selection
     */
    private void selectionPhase()
    {
        System.out.println("Selection phase");
        this.phase = GamePhase.BattleSelection;
        this.aiCard = this.ai.selectBattleCard(this.aiHand);
        System.out.println("AI selects: " + this.aiCard.toAbbrevString());
        this.promptLabel.setText("Select a Card to Play");
        this.setSelectionActive(true);
    }

    /**
     * Called by the mouseClicked event with the index of the card clicked
     * @param index
     */
    private void determinePlayerSelection(int index)
    {
        this.playerCard = this.playerHand.selectCard(index);
        if(this.phase == GamePhase.BattleSelection)
        {
            this.battlePhase();
        }
        else if(this.phase == GamePhase.PreWar)
        {
            this.warPhase();
        }
    }

    /**
     * BATTLE PHASE: Creates a Battle object and determines a winner or war condition
     */
    private void battlePhase()
    {
        System.out.println("Battle phase");
        this.refreshPrizeImages();
        this.phase = GamePhase.Battle;
        this.promptLabel.setText("");
        this.setSelectionActive(false);
        this.refreshHandImages();
        this.pPlayedImage.setImage(this.playerCard.getCardImage());
        this.cPlayedImage.setImage(this.aiCard.getCardImage());
        this.currentBattle = new Battle(this.playerCard, this.aiCard);
        this.aiCard = null;
        this.playerCard = null;
        System.out.println("Battle: " + this.currentBattle.toString());
        switch (this.currentBattle.getWinner())
        {
            case 0:
                this.victoryLabel.setText("Computer Wins!");
                this.roundEndPhase(0);
                break;
            case 1:
                this.victoryLabel.setText("Player Wins!");
                this.roundEndPhase(1);
                break;
            case 2:
                this.victoryLabel.setText("WAR!");
                preWarPhase();
                break;
        }
    }

    /**
     * PRE-WAR PHASE: preps the variables for war
     */
    private void preWarPhase()
    {
        System.out.println("Pre-War phase");
        this.playerPrizes =  new ArrayList<Card>();
        this.aiPrizes = new ArrayList<Card>();
        if(this.phase == GamePhase.Battle)
        {
         this.playerPrizes.add(this.currentBattle.playerCard);
         this.aiPrizes.add(this.currentBattle.aiCard);
        }
        if(this.playerHand.cardCount() > 2)
        {
            this.playerPrizes.add(this.playerHand.pickRandom());
            this.playerPrizes.add(this.playerHand.pickRandom());
        }
        else
        {
            this.drawCards(this.playerDeck, this.playerPile, this.playerHand);
            this.refreshHandImages();
            this.refreshCounterLabels();
            if(this.playerHand.cardCount() > NUM_WAR_PRIZES)
            {
                this.playerPrizes.add(this.playerHand.pickRandom());
                this.playerPrizes.add(this.playerHand.pickRandom());
            }
            else if (this.playerHand.cardCount() == NUM_WAR_PRIZES)
            {
                this.playerPrizes.add(this.playerHand.pickRandom());
            }
        }
        this.refreshPrizeImages();
        this.refreshHandImages();
        if(this.aiHand.cardCount() > NUM_WAR_PRIZES)
        {
            this.aiPrizes.add(this.aiHand.pickRandom());
            this.aiPrizes.add(this.aiHand.pickRandom());
        }
        else
        {
            this.drawCards(this.aiDeck, this.aiPile, this.aiHand);
            this.refreshHandImages();
            if(this.aiHand.cardCount() > NUM_WAR_PRIZES)
            {
                this.aiPrizes.add(this.aiHand.pickRandom());
                this.aiPrizes.add(this.aiHand.pickRandom());
            }
            else if (this.aiHand.cardCount() == NUM_WAR_PRIZES)
            {
                this.aiPrizes.add(this.aiHand.pickRandom());
            }
        }
        this.refreshPrizeImages();
        this.refreshHandImages();
        this.phase = GamePhase.PreWar;
        this.aiCard = this.ai.selectBattleCard(this.aiHand);
        System.out.println("AI selects: " + this.aiCard.toAbbrevString());
        this.promptLabel.setText("Select a Card to Play");
        this.setSelectionActive(true);
    }

    /**
     * WAR PHASE: Initiates a War object and determines a 
     * winner or war condition
     */
    private void warPhase()
    {
    	System.out.println("War phase");
    	this.setSelectionActive(false);
        this.phase = GamePhase.War;
        victoryLabel.setText("");
        this.pPlayedImage.setImage(this.playerCard.getCardImage());
        this.cPlayedImage.setImage(this.aiCard.getCardImage());
        if(this.warLoop)
        {
            this.currentWar.additionalWar(playerCard, aiCard, playerPrizes, aiPrizes);
        }
        else
        {
            this.currentWar = new War(playerCard, aiCard, playerPrizes, aiPrizes);
        }
        this.aiCard = null;
        this.playerCard = null;
        this.playerPrizes.clear();
        this.aiPrizes.clear();
    	switch (this.currentWar.getWinner())
        {
            case 0:
                this.victoryLabel.setText("Computer Wins!");
                this.warLoop = false;
                this.roundEndPhase(0);
                break;
            case 1:
                this.victoryLabel.setText("Player Wins!");
                this.warLoop = false;
                this.roundEndPhase(1);
                break;
            case 2:
            	this.victoryLabel.setText("WAR!");
            	this.warLoop = true;
                this.preWarPhase();
                break;
        }
    }

    /**
     * ROUND END PHASE: End of a game round, distribute winnings and check win condition.
     * If no victor continue to draw phase.
     */
    private void roundEndPhase(int winner)
    {
        //TODO: Clean up, check for victory condition
        /*try {
            Thread.sleep(2000);
        }catch (java.lang.InterruptedException e)
        {
            System.out.println(e.toString());
        }*/
        if(this.phase == GamePhase.Battle)
        {
            if(winner == 0)
            {
                this.aiPile.addCards(this.currentBattle.getCards());
            }
            else
            {
                this.playerPile.addCards(this.currentBattle.getCards());
            }
        }
        else
        {
            if(winner == 0)
            {
                this.aiPile.addCards(this.currentWar.getCards());
            }
            else
            {
                this.playerPile.addCards(this.currentWar.getCards());
            }
            this.refreshPrizeImages();
        }
        //this.pPlayedImage.setImage(null);
        //this.cPlayedImage.setImage(null);
        this.refreshCounterLabels();
        this.phase = GamePhase.RoundEnd;
        this.drawPhase();
    }

    private void gameOverPhase(int winner)
    {
        this.phase = GamePhase.GameOver;
    }

    private void refreshHandImages()
    {
        for(int i = 0 ; i < this.pHandImages.length; i++)
        {
            this.pHandImages[i].setImage(null);
            this.cHandImages[i].setImage(null);
        }
        for(int i = 0; i < this.playerHand.cardCount(); i++)
        {
            this.pHandImages[i].setImage(this.playerHand.getCardImage(i));
        }
        for(int i = 0; i < this.aiHand.cardCount(); i++)
        {
            this.cHandImages[i].setImage(this.cardBackImage);
        }
    }

    private void refreshPrizeImages()
    {
        for(int i = 0; i < 3; i++)
        {
            this.pPrizeImages[i].setImage(null);
            this.cPrizeImages[i].setImage(null);
        }
        for(int i = 0; i < this.playerPrizes.size(); i++)
        {
            this.pPrizeImages[i].setImage(this.playerPrizes.get(this.playerPrizes.size()-(1+i)).getCardImage());
        }
        for(int i = 0; i < this.aiPrizes.size(); i++)
        {
            this.cPrizeImages[i].setImage(this.aiPrizes.get(this.aiPrizes.size()-(1+i)).getCardImage());
        }
    }

    private void refreshCounterLabels()
    {
        int playeScore = this.playerDeck.getSize() + this.playerHand.cardCount() + this.playerPile.cardCount();
        int aiScore = this.aiDeck.getSize() + this.aiHand.cardCount() + this.aiPile.cardCount();
        this.playerScoreLabel.setText("Player: " + playeScore);
        this.computerScoreLabel.setText("Computer: " + aiScore);
        this.pDeckLabel.setText("" + this.playerDeck.getSize());
        this.pDiscardLabel.setText("" + this.playerPile.cardCount());
    }

    /**
     * Determines number of cards to draw to a specified hand
     * @param deck deck to draw from
     * @param pile pile to draw from
     * @param hand hand to draw to
     */
    private void drawCards(Deck deck, DiscardPile pile, Hand hand)
    {
        int toDraw = MAX_HAND_SIZE - hand.cardCount();
        if(deck.getSize() < toDraw)
        {
            deck.addCards((pile.removeCards().toArray(new Card[pile.cardCount()])));
            if(deck.getSize() < toDraw)
            {
                toDraw = deck.getSize();
            }
        }
        hand.addCards(deck.drawCards(toDraw));
    }

    /**
     * Turns on and off the ability for the player to select cards in their hand
     * @param active
     */
    private void setSelectionActive(boolean active)
    {
        for(int i = 0; i < this.pHandImages.length; i++)
        {
            //No need to change this if there is no image and we are turning them on
            if(active)
            {
                if (this.pHandImages[i].getImage() != null)
                {
                    this.pHandImages[i].setDisable(false);
                }
            }else
            {
                this.pHandImages[i].setDisable(true);
            }
        }
    }

    /**
     * Handles when the mouse enters a selectable card image
     * @param event
     */
    public void mouseEnteredCard(MouseEvent event)
    {
        //System.out.println("Mouse entered " + ((ImageView)event.getSource()).getId());
        ImageView view = (ImageView)event.getSource();
        Label label = null;
        switch (((ImageView)event.getSource()).getId())
        {
            case "pHandImage0":
                label = pHandSelectLabel0;
                break;
            case "pHandImage1":
                label = pHandSelectLabel1;
                break;
            case "pHandImage2":
                label = pHandSelectLabel2;
                break;
            case "pHandImage3":
                label = pHandSelectLabel3;
                break;
            case "pHandImage4":
                label = pHandSelectLabel4;
                break;
        }
        if (view != null && label != null)
        {
            InnerShadow shadow = new InnerShadow();
            shadow.setColor(Color.web("#ff0000"));//"#00ff3b"));
            shadow.setChoke(.4d);
            shadow.setWidth(40d);
            shadow.setHeight(40d);
            shadow.setBlurType(BlurType.GAUSSIAN);
            view.setEffect(shadow);
            label.setVisible(true);
        }
    }

    /**
     * Handles when the mouse exits a selectable card image
     * @param event
     */
    public void mouseExitedCard(MouseEvent event)
    {
        //System.out.println("Mouse exited " + ((ImageView)event.getSource()).getId());
        ImageView view = (ImageView)event.getSource();
        Label label = null;
        switch (((ImageView)event.getSource()).getId())
        {
            case "pHandImage0":
                label = pHandSelectLabel0;
                break;
            case "pHandImage1":
                label = pHandSelectLabel1;
                break;
            case "pHandImage2":
                label = pHandSelectLabel2;
                break;
            case "pHandImage3":
                label = pHandSelectLabel3;
                break;
            case "pHandImage4":
                label = pHandSelectLabel4;
                break;
        }
        if (view != null && label != null)
        {
            view.setEffect(null);
            label.setVisible(false);
        }
    }

    /**
     * Handles when the mouse clicks a selectable card image
     * @param event
     */
    public void mouseClickedCard(MouseEvent event)
    {
        System.out.println("Mouse clicked " + ((ImageView)event.getSource()).getId());
        switch (((ImageView)event.getSource()).getId())
        {
            case "pHandImage0":
                this.determinePlayerSelection(0);
                break;
            case "pHandImage1":
                this.determinePlayerSelection(1);
                break;
            case "pHandImage2":
                this.determinePlayerSelection(2);
                break;
            case "pHandImage3":
                this.determinePlayerSelection(3);
                break;
            case "pHandImage4":
                this.determinePlayerSelection(4);
                break;
        }
    }
}
