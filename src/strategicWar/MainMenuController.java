package strategicwar;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

/**
 * Controls the GUI functionality of the main menu scene.
 */
public class MainMenuController {
    private ApplicationController applicationController;
    private AudioClip uiClip;
    @FXML
    private Pane firstPane;
    @FXML
    private Pane difficultyPane;

    public MainMenuController()
    {
        this.uiClip = new AudioClip(getClass().getResource("../resources/sound/play_card.mp3").toExternalForm());
    }

    /**
     * Set this instance's ApplicationController
     * @param applicationController the ApplicationController instance
     */
    public void setApplicationController(ApplicationController applicationController)
    {
        this.applicationController = applicationController;
    }

    /**
     * Called when the playGameButton is pressed
     */
    public void playGamePressed()
    {
        this.uiClip.play();
        System.out.println("play game pressed");
        this.setDifficultyPaneVisible();
    }

    /**
     * Called when the back button is pressed
     */
    public void backPressed()
    {
        this.uiClip.play();
        System.out.println("back pressed");
        this.setMainPaneVisible();
    }

    /**
     * Called to make the main pane visible
     */
    private void setMainPaneVisible()
    {
        this.difficultyPane.setVisible(false);
        this.firstPane.setVisible(true);
    }

    /**
     * called to make the difficulty pane visible
     */
    private void setDifficultyPaneVisible()
    {
        this.firstPane.setVisible(false);
        this.difficultyPane.setVisible(true);
    }

    /**
     * Called when the quit button is pressed
     */
    public void quitPressed()
    {
        this.uiClip.play();
        System.out.println("quit pressed");
        this.applicationController.closeApplication();
    }

    /**
     * Called when the easy difficulty button is pressed
     */
    public void easyPressed()
    {
        this.uiClip.play();
        System.out.println("easy pressed");
        this.setMainPaneVisible();
        this.applicationController.transitionToGame(0);
    }

    /**
     * called when the hard difficulty button is pressed
     */
    public void hardPressed()
    {
        this.uiClip.play();
        System.out.println("hard pressed");
        this.setMainPaneVisible();
        this.applicationController.transitionToGame(1);
    }


    /**
     * called when the random difficulty button is pressed
     */
    public void randomPressed()
    {
        this.uiClip.play();
        System.out.println("random pressed");
        this.setMainPaneVisible();
        this.applicationController.transitionToGame(2);
    }
}
