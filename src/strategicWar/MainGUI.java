package strategicWar;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import sun.tools.jstat.Alignment;

import java.util.Stack;

public class MainGUI{
    private Scene scene;
    private BorderPane masterPane;
    private VBox firstPane;
    private VBox difficultyPane;

    public MainGUI()//EventHandler[] buttonEvents)
    {
        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.CENTER);
        Label title = new Label("Strategic War");
        titlePane.getChildren().add(title);

        this.firstPane = new VBox();
        this.firstPane.setAlignment(Pos.CENTER);
        this.firstPane.setSpacing(25);
        Button playButton = new Button("New Game");
        playButton.setPrefSize(150,75);
        playButton.setOnAction(e -> transitionDifficultyPane());
        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(150,75);
        //exitButton.setOnAction(buttonEvents[1]);
        this.firstPane.getChildren().addAll(playButton, exitButton);

        this.difficultyPane = new VBox();
        this.difficultyPane.setAlignment(Pos.CENTER);
        this.difficultyPane.setSpacing(25);
        Label diffLabel = new Label("Select Difficulty");
        Button easyButton = new Button("Easy");
        easyButton.setPrefSize(150,75);

        Button hardButton = new Button("Hard");
        hardButton.setPrefSize(150,75);

        Button backButton = new Button("Back");
        backButton.setPrefSize(150,75);
        backButton.setOnAction(e -> transitionFirstPane());

        this.difficultyPane.getChildren().addAll(diffLabel, easyButton, hardButton, backButton);


        this.masterPane = new BorderPane();
        this.masterPane.setTop(titlePane);
        this.masterPane.setCenter(this.firstPane);

        this.scene = new Scene(masterPane, 1000, 750);
    }

    public Scene getScene()
    {
        return this.scene;
    }

    private void transitionDifficultyPane()
    {
        this.masterPane.setCenter(this.difficultyPane);
    }


    private void transitionFirstPane()
    {
        this.masterPane.setCenter(this.firstPane);
    }

}
