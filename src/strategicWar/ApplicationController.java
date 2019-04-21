package strategicwar;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.AudioClip;


/**
 * A controller for the entire application. It processes the FXML files, 
 * audio, and starts the JavaFX window.
 * @authors ryanharper, jaredpeterson
 *
 */
public class ApplicationController extends Application {
    private Stage stage;
    private Scene mainScene;
    private AudioClip mainMusic;
    private Scene gameScene;
    private AudioClip gameMusic;
    private AudioClip uiClip;

    private GameController gameController;

    @FXML
    private Pane firstPane;

    @FXML
    private Pane difficultyPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent main = fxmlLoader.load();
        ApplicationController applicationController = fxmlLoader.getController();
        fxmlLoader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent game = fxmlLoader.load();
        GameController gameController = fxmlLoader.getController();
        Scene mainScene = new Scene(main);
        Scene gameScene = new Scene(game);
        applicationController.initialize(primaryStage, mainScene, gameScene, gameController);
    }

    public static void main(String args[])
    {
        Application.launch(ApplicationController.class, args);
    }
    /**
     * Sets the music, 
     * @param stage
     * @param mainScene
     * @param gameScene
     * @param gameController
     */
    public void initialize(Stage stage, Scene mainScene, Scene gameScene, GameController gameController)
    {
        this.stage = stage;
        this.mainScene = mainScene;
        this.uiClip = new AudioClip(getClass().getResource("../resources/sound/play_card.mp3").toExternalForm());
        this.mainMusic = new AudioClip(getClass().getResource("../resources/music/Dangerous.mp3").toExternalForm());
        this.mainMusic.setCycleCount(AudioClip.INDEFINITE);
        this.mainMusic.play();
        this.gameMusic = new AudioClip(getClass().getResource("../resources/music/Plans_in_Motion.mp3").toExternalForm());
        this.gameMusic.setCycleCount(AudioClip.INDEFINITE);
        this.gameScene = gameScene;
        this.gameController = gameController;
        this.gameController.setDelegate(this);
        this.stage.setTitle("Strategic War");
        this.stage.setScene(mainScene);
        this.stage.show();
    }

    public void playGamePressed()
    {
        this.uiClip.play();
        System.out.println("play game pressed");
        this.setDifficultyPaneVisible();
    }

    public void backPressed()
    {
        this.uiClip.play();
        System.out.println("back pressed");
        this.setMainPaneVisible();
    }

    private void setMainPaneVisible()
    {
        this.difficultyPane.setVisible(false);
        this.firstPane.setVisible(true);
    }

    private void setDifficultyPaneVisible()
    {
        this.firstPane.setVisible(false);
        this.difficultyPane.setVisible(true);
    }

    public void quitPressed()
    {
        this.uiClip.play();
        System.out.println("quit pressed");
        this.closeApplication();
    }

    public void easyPressed()
    {
        this.uiClip.play();
        System.out.println("easy pressed");
        this.startGame(0);
    }

    public void hardPressed()
    {
        this.uiClip.play();
        System.out.println("hard pressed");
        this.startGame(1);
    }

    private void startGame(int difficulty)
    {
        System.out.println("starting game with " + difficulty + " level of difficulty");
        this.transitionToGame();
        this.gameController.start(difficulty);
    }

    public void transitionToMenu()
    {
        this.gameMusic.stop();
        this.mainMusic.play();
        this.stage.setScene(this.mainScene);
        this.setMainPaneVisible();
    }

    public void transitionToGame()
    {
        this.mainMusic.stop();
        this.gameMusic.play();
        this.stage.setScene(this.gameScene);
    }

    public  void closeApplication()
    {
        System.exit(0);
    }

}
