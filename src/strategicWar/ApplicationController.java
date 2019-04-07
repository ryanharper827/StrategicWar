package strategicwar;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class ApplicationController extends Application {
    private Scene mainScene;
    private Scene gameScene;

    @FXML
    private Pane firstPane;

    @FXML
    private Pane difficultyPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        this.mainScene = new Scene(root);
        primaryStage.setTitle("Strategic War");
        primaryStage.setScene(this.mainScene);
        primaryStage.show();
    }

    public static void main(String args[])
    {
        launch(args);
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
        //this.gameController.Start(difficulty);
    }

}
