package strategicwar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.AudioClip;


/**
 * A controller for the entire application. It processes the FXML files, 
 * audio, and starts the JavaFX window.
 *
 */
public class ApplicationController extends Application {
    private Stage stage;
    private Scene mainScene;
    private AudioClip mainMusic;
    private Scene gameScene;
    private AudioClip gameMusic;
    private GameGUIController gameGUIController;
    private MainMenuController mainMenuController;


    /**
     * Starts the application, loads the FXML files
     * @param primaryStage the primary javafx Stage
     * @throws Exception if the FXML resources are not found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent main = fxmlLoader.load();
        MainMenuController mainMenuController = fxmlLoader.getController();
        fxmlLoader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent game = fxmlLoader.load();
        GameGUIController gameGUIController = fxmlLoader.getController();
        Scene mainScene = new Scene(main);
        Scene gameScene = new Scene(game);
        this.initialize(primaryStage, mainScene, gameScene, mainMenuController, gameGUIController);
    }

    /**
     * Main method for the entire application
     * @param args command line arguments
     */
    public static void main(String args[])
    {
        Application.launch(ApplicationController.class, args);
    }

    /**
     * initializes the AppController with all of its relevant variables
     * @param stage the primary stage
     * @param mainScene the main scene loaded by fxml
     * @param gameScene the game scene loaded by fxml
     * @param mainMenuController the MainMenuController from the main scene
     * @param gameGUIController the GameGUIController from the game scene
     */
    public void initialize(Stage stage, Scene mainScene, Scene gameScene,
                           MainMenuController mainMenuController, GameGUIController gameGUIController)
    {
        this.stage = stage;
        this.mainMusic = new AudioClip(getClass().getResource("../resources/music/Dangerous.mp3").toExternalForm());
        this.gameMusic = new AudioClip(getClass().getResource("../resources/music/Plans_in_Motion.mp3").toExternalForm());
        this.mainMusic.setCycleCount(AudioClip.INDEFINITE);
        this.gameMusic.setCycleCount(AudioClip.INDEFINITE);
        this.mainScene = mainScene;
        this.gameScene = gameScene;
        this.mainMenuController = mainMenuController;
        this.mainMenuController.setApplicationController(this);
        this.gameGUIController = gameGUIController;
        this.gameGUIController.setApplicationController(this);
        this.stage.setTitle("Strategic War");
        this.stage.setScene(mainScene);
        this.stage.show();
        this.mainMusic.play();
    }


    /**
     * called by the button action methods to start a new game
     * @param difficulty the difficulty of the game
     */
    private void startGame(int difficulty)
    {
        System.out.println("starting game with " + difficulty + " level of difficulty");
        WarGame.getInstance().start(difficulty);
    }

    /**
     * Transitions scenes to the menu scene
     */
    public void transitionToMenu()
    {
        this.gameMusic.stop();
        this.mainMusic.play();
        this.stage.setScene(this.mainScene);
    }

    /**
     * Transition scenes to the game scene
     * @param difficulty the difficutly to start the game
     */
    public void transitionToGame(int difficulty)
    {
        this.mainMusic.stop();
        this.gameMusic.play();
        this.stage.setScene(this.gameScene);
        this.gameGUIController.intitialize();
        this.startGame(difficulty);
    }

    /**
     * Closes the application
     */
    public void closeApplication()
    {
        System.exit(0);
    }

}
