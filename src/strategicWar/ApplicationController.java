package strategicwar;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class ApplicationController extends Application {
    private static Stage stage;
    private static Scene mainScene;
    private static Scene gameScene;

    @FXML
    private Pane firstPane;

    @FXML
    private Pane difficultyPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent main = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent game = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        mainScene = new Scene(main);
        gameScene = new Scene(game);
        stage.setTitle("Strategic War");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String args[])
    {
        Application.launch(ApplicationController.class, args);
    }

    public void playGamePressed()
    {
        System.out.println("play game pressed");
        this.firstPane.setVisible(false);
        this.difficultyPane.setVisible(true);
    }

    public void backPressed()
    {
        System.out.println("back pressed");
        this.difficultyPane.setVisible(false);
        this.firstPane.setVisible(true);
    }

    public void quitPressed()
    {
        System.out.println("quit pressed");
        System.exit(0);
    }

    public void easyPressed()
    {
        System.out.println("easy pressed");
        this.startGame(0);
    }

    public  void hardPressed()
    {
        System.out.println("hard pressed");
        this.startGame(1);
    }

    private void startGame(int difficulty)
    {
        System.out.println("starting game with " + difficulty + " level of difficulty");
        //TODO: add GameControllerInitialization
        //this.gameController.Start(difficulty);
        ApplicationController.stage.setScene(ApplicationController.gameScene);
    }
}
