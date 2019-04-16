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

    private ImageView[] pHandImages, cHandImages;

    private static final int MAX_HAND_SIZE = 5;

    private WarAI ai;
    private boolean playing;
    private Deck playerDeck,aiDeck;
    private Hand playerHand, aiHand;
    private DiscardPile playerPile, aiPile;

    private Card playerCard;
    private Card aiCard;

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
        //TODO: there will need to be some sort of switch to determine what kind of selection happened
        // i.e. battle selection, war prize selection, war battle selection.
        this.playerCard = this.playerHand.selectCard(index);
        this.battlePhase();
    }

    /**
     * BATTLE PHASE: Creates a Battle object and determines a winner or war condition
     */
    private void battlePhase()
    {
        System.out.println("Battle phase");
        this.promptLabel.setText("");
        this.setSelectionActive(false);
        this.refreshHandImages();
        this.pPlayedImage.setImage(this.playerCard.getCardImage());
        this.cPlayedImage.setImage(this.aiCard.getCardImage());
        Battle battle = new Battle(this.playerCard, this.aiCard);
        System.out.println("Battle: " + battle.toString());
        switch (battle.getWinner())
        {
            case 0:
                this.victoryLabel.setText("Computer Wins!");
                break;
            case 1:
                this.victoryLabel.setText("Player Wins!");
                break;
            case 2:
                this.victoryLabel.setText("WAR!");
                ArrayList<Card> playerPrizes = new ArrayList<Card>();
            	ArrayList<Card> aiPrizes = new ArrayList<Card>();
            	int playerCardCount = playerHand.cardCount();
            	int aiCardCount = aiHand.cardCount();
            	for(int i = 0; i < playerCardCount; i++) {
            		playerPrizes.add(playerHand.pickRandom());
            	}
            	for(int i = 0; i < aiCardCount; i++) {
            		aiPrizes.add(aiHand.pickRandom());
            	}
            	drawCards(playerDeck, playerPile, playerHand);            	
            	drawCards(aiDeck, aiPile, aiHand);
                warPhase(playerPrizes, aiPrizes);
                break;
        }
        roundEndPhase();
    }
    /**
     * WAR PHASE: Initiates a War object and determines a 
     * winner or war condition (Recursive call is a possibility)
     * @param aiPrizes 
     * @param playerPrizes 
     */
    private void warPhase(ArrayList<Card> playerPrizes, ArrayList<Card> aiPrizes)
    {
    	System.out.println("War phase");
    	victoryLabel.setText("");
    	War war = new War(playerCard, aiCard, playerPrizes, aiPrizes);
    	switch (war.getWinner())
        {
            case 0:
                this.victoryLabel.setText("Computer Wins!");
                break;
            case 1:
                this.victoryLabel.setText("Player Wins!");
                break;
            case 2:
            	this.victoryLabel.setText("WAR!");
            	for(int i = 0; i < 4; i++) {
            		playerPrizes.add(playerHand.pickRandom());
            	}
            	for(int i = 0; i < 4; i++) {
            		aiPrizes.add(aiHand.pickRandom());
            	}
            	drawCards(playerDeck, playerPile, playerHand);            	
            	drawCards(aiDeck, aiPile, aiHand);
                warPhase(playerPrizes, aiPrizes);
                break;
        }
    	
    }

    /**
     * ROUND END PHASE: End of a game round, distribute winnings and check win condition.
     * If no victor continue to draw phase.
     */
    private void roundEndPhase()
    {
        //TODO: Clean up, check for victory condition
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
            deck.addCards((Card[])pile.removeCards().toArray());
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
