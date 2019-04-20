package strategicwar;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class ApplicationController extends Application {
    private Stage stage;
    private Scene mainScene;
    private Scene gameScene;

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

    public void initialize(Stage stage, Scene mainScene, Scene gameScene, GameController gameController)
    {
        this.stage = stage;
        this.mainScene = mainScene;
        this.gameScene = gameScene;
        this.gameController = gameController;
        this.gameController.setDelegate(this);
        this.stage.setTitle("Strategic War");
        this.stage.setScene(mainScene);
        this.stage.show();
    }

    public void playGamePressed()
    {
        System.out.println("play game pressed");
        this.setDifficultyPaneVisible();
    }

    public void backPressed()
    {
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
        System.out.println("quit pressed");
        this.closeApplication();
    }

    public void easyPressed()
    {
        System.out.println("easy pressed");
        this.startGame(0);
    }

    public void hardPressed()
    {
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
        this.stage.setScene(this.mainScene);
        this.setMainPaneVisible();
    }

    public void transitionToGame()
    {
        this.stage.setScene(this.gameScene);
    }

    public  void closeApplication()
    {
        System.exit(0);
    }

}
