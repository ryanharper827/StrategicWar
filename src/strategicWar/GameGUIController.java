package strategicwar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controls the functions of the GameScene and its various dialogs
 */
public class GameGUIController implements WarGameObserver{

    private WarGame game;
    private ApplicationController applicationController;

    @FXML
    private Label pHandSelectLabel0, pHandSelectLabel1, pHandSelectLabel2, pHandSelectLabel3, pHandSelectLabel4,
            victoryLabel,promptLabel,playerScoreLabel,computerScoreLabel,pDiscardLabel,pDeckLabel;
    @FXML
    private ImageView pHandImage0,pHandImage1,pHandImage2,pHandImage3,pHandImage4,pDiscardImage,pDeckImage,pPlayedImage,
            pPrizeImage0,pPrizeImage1,pPrizeImage2,cPlayedImage,cPrizeImage0,cPrizeImage1,cPrizeImage2,cHandImage0,
            cHandImage1,cHandImage2,cHandImage3,cHandImage4;
    private Image cardBackImage;

    private ImageView[] pHandImages, cHandImages, pPrizeImages, cPrizeImages;

    private Dialog gameOverMenu;
    private Dialog aboutMenu;

    @FXML
    private Label victorLabel;

    private AudioClip uiClip;
    private AudioClip rifleClip;
    private AudioClip explosionClip;
    private AudioClip sirenClip;
    private AudioClip shuffleClip;

    /**
     * Set this instance's ApplicationController
     * @param applicationController
     */
    public void setApplicationController(ApplicationController applicationController)
    {
        this.applicationController = applicationController;
    }

    /**
     * Get a new instance of a GameGUIController
     */
    public GameGUIController()
    {
        this.game = WarGame.getInstance();
        this.game.addObserver(this);
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            this.gameOverMenu = new Dialog();
            this.gameOverMenu.getDialogPane().setContent(root);
            loader = new FXMLLoader(getClass().getResource("About.fxml"));
            loader.setController(this);
            root = loader.load();
            this.aboutMenu = new Dialog();
            this.aboutMenu.getDialogPane().setContent(root);
        }
        catch (IOException exception)
        {
            System.out.println("ERROR LOADING FXML: " + exception.toString());
            System.exit(1);
        }
        this.cardBackImage = new Image("/resources/Cards/card_back.png");
        this.uiClip = new AudioClip(getClass().getResource("../resources/sound/play_card.mp3").toExternalForm());
        this.rifleClip = new AudioClip(getClass().getResource("../resources/sound/rifleshot.mp3").toExternalForm());
        this.rifleClip.setVolume(.2d);
        this.explosionClip = new AudioClip(getClass().getResource("../resources/sound/explosion.mp3").toExternalForm());
        this.explosionClip.setVolume(.5d);
        this.sirenClip = new AudioClip(getClass().getResource("../resources/sound/siren.mp3").toExternalForm());
        this.shuffleClip = new AudioClip(getClass().getResource("../resources/sound/shuffle.mp3").toExternalForm());
    }

    /**
     * Initialize the components that are only available on scene load
     */
    public void intitialize()
    {
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
            this.uiClip.play();
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
     * WarGameObserver method: handles game phase changes
     */
    public void phaseUpdated()
    {
        switch (this.game.getPhase())
        {
            case BattleSelection:
                this.promptLabel.setText("Select a Card to Play");
                this.setSelectionActive(true);
                break;
            case Battle:
                this.promptLabel.setText("");
                this.setSelectionActive(false);
                break;
            case PreWar:
                this.promptLabel.setText("Select a Card to Play");
                this.setSelectionActive(true);
                break;
            case War:
                this.promptLabel.setText("");
                this.setSelectionActive(false);
                break;
            case WinnerAI:
                this.victorLabel.setText("YOU LOSE!");
                this.gameOverMenu.show();
                break;
            case WinnerPlayer:
                this.victorLabel.setText("YOU WIN!");
                this.gameOverMenu.show();
                break;
        }
    }

    /**
     * WarGameObserver method: handles when battle cards change
     */
    public void battleCardsUpdated()
    {
        Card playerCard = this.game.getPlayerCard();
        Card aiCard = this.game.getAICard();
        if(playerCard != null)
        {
            this.pPlayedImage.setImage(playerCard.getCardImage());
        }
        else
        {
            this.pPlayedImage.setImage(null);
        }
        if(aiCard != null)
        {
            this.cPlayedImage.setImage(aiCard.getCardImage());
        }
        else
        {
            this.cPlayedImage.setImage(null);
        }
    }

    /**
     * WarGameObserver method: handles when hands are updated
     */
    public void handsUpdated()
    {
        Hand playerHand = this.game.getPlayerHand();
        Hand aiHand = this.game.getAIHand();
        for(int i = 0 ; i < this.pHandImages.length; i++)
        {
            this.pHandImages[i].setImage(null);
            this.cHandImages[i].setImage(null);
        }
        for(int i = 0; i < playerHand.cardCount(); i++)
        {
            this.pHandImages[i].setImage(playerHand.getCardImage(i));
        }
        for(int i = 0; i < aiHand.cardCount(); i++)
        {
            this.cHandImages[i].setImage(this.cardBackImage);
        }
    }

    /**
     * WarGameObserver method: handles when prize cards are updated
     */
    public void prizesUpdated()
    {
        ArrayList<Card> playerPrizes = this.game.getPlayerPrizes();
        ArrayList<Card> aiPrizes = this.game.getPlayerPrizes();
        for(int i = 0; i < 3; i++)
        {
            this.pPrizeImages[i].setImage(null);
            this.cPrizeImages[i].setImage(null);
        }
        for(int i = 0; i < playerPrizes.size(); i++)
        {
            this.pPrizeImages[i].setImage(playerPrizes.get(playerPrizes.size()-(1+i)).getCardImage());
        }
        for(int i = 0; i < aiPrizes.size(); i++)
        {
            this.cPrizeImages[i].setImage(aiPrizes.get(aiPrizes.size()-(1+i)).getCardImage());
        }
    }

    /**
     * WarGameObserver method: handles when player DiscardPile is updated
     */
    public void pileUpdated()
    {
        int pileCount = this.game.getPlayerPileCount();
        if(pileCount > 0)
        {
            this.pDiscardLabel.setText("" + pileCount);
            this.pDiscardLabel.setVisible(true);
            this.pDiscardImage.setImage(this.cardBackImage);
        }
        else
        {
            this.pDiscardLabel.setVisible(false);
            this.pDiscardImage.setImage(null);
        }
    }

    /**
     * WarGameObserver method: handles when the player deck is updated
     */
    public void deckUpdated()
    {
        int deckCount = this.game.getPlayerDeckCount();
        if(deckCount > 0)
        {
            if(!this.pDeckLabel.isVisible())
                this.shuffleClip.play();
            this.pDeckLabel.setText("" + deckCount);
            this.pDeckLabel.setVisible(true);
            this.pDeckImage.setImage(this.cardBackImage);
        }
        else
        {
            this.pDeckLabel.setVisible(false);
            this.pDeckImage.setImage(null);
        }
    }

    /**
     * WarGameObserver method: handles when the scores are updated
     */
    public void scoresUpdated()
    {
        int playeScore = this.game.getPlayerScore();
        int aiScore = this.game.getComputerScore();
        this.playerScoreLabel.setText("Player: " + playeScore);
        this.computerScoreLabel.setText("Computer: " + aiScore);
    }

    /**
     * WarGameObserver method: handles when a battle or war result occurs
     */
    public void victorUpdated()
    {
        int lastVictor = this.game.getLastVictor();
        switch (lastVictor)
        {
            case 0 :
               this.victoryLabel.setText("Computer Wins!");
                this.explosionClip.play();
                break;
            case 1:
                this.victoryLabel.setText("Player Wins!");
                this.rifleClip.play();
                break;
            case 2:
                this.victoryLabel.setText("War!");
                this.sirenClip.play();
                break;
            case -1:
                this.victoryLabel.setText("");
                break;
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
                this.game.playerSelectedCard(0);
                break;
            case "pHandImage1":
                this.game.playerSelectedCard(1);
                break;
            case "pHandImage2":
                this.game.playerSelectedCard(2);
                break;
            case "pHandImage3":
                this.game.playerSelectedCard(3);
                break;
            case "pHandImage4":
                this.game.playerSelectedCard(4);
                break;
        }
    }

    /**
     * Handles when the mouse clicks the Quit Game
     * menu option under File
     */
    public void quitGamePressed()
    {
        System.out.println("quit game pressed");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit the game?",
                ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES)
        {
            this.game.setPlaying(false);
            this.applicationController.transitionToMenu();
        }
    }

    /**
     * Handles when the mouse clicks the Quit Application
     * menu option under File
     */
    public void quitAppPressed()
    {
        System.out.println("quit app pressed");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close Strategic War?",
                ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES)
        {
            this.applicationController.closeApplication();
        }
    }

    /**
     * Handles when the mouse clicks the About
     * menu option under Help
     */
    public void aboutPressed()
    {
        System.out.println("about pressed");
        this.aboutMenu.show();
    }

    /**
     * Handles the press of the ok button on the about menu
     */
    public void okPressed()
    {
        this.uiClip.play();
        System.out.println("ok pressed");
        Stage s = (Stage) this.aboutMenu.getDialogPane().getScene().getWindow();
        s.hide();
    }


    /**
     * Handles when the mouse clicks the About
     * menu option under Help
     */
    public void retryPressed()
    {
        this.uiClip.play();
        System.out.println("retry pressed");
        Stage s = (Stage) this.gameOverMenu.getDialogPane().getScene().getWindow();
        s.hide();
        this.game.start(this.game.getCurrentDifficulty());
    }


    /**
     * Handles when the mouse clicks the About
     * menu option under Help
     */
    public void menuPressed()
    {
        this.uiClip.play();
        System.out.println("menu pressed");
        Stage s = (Stage) this.gameOverMenu.getDialogPane().getScene().getWindow();
        s.hide();
        this.applicationController.transitionToMenu();
    }


    /**
     * Handles when the mouse clicks the About
     * menu option under Help
     */
    public void quitMenuPressed()
    {
        this.uiClip.play();
        System.out.println("quit pressed");
        this.applicationController.closeApplication();
    }
}
