package strategicWar;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

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
        title.setId("title-label");
        titlePane.getChildren().add(title);

        this.firstPane = new VBox();
        this.firstPane.setAlignment(Pos.CENTER);
        this.firstPane.setSpacing(25);
        
        Button playButton = new Button("New Game");
        playButton.setPrefSize(300,75);
        playButton.setOnAction(e -> transitionDifficultyPane());
        
        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(150,75);
        exitButton.setOnAction(e -> System.exit(0));
        
        this.firstPane.getChildren().addAll(playButton, exitButton);

        this.difficultyPane = new VBox();
        this.difficultyPane.setAlignment(Pos.CENTER);
        this.difficultyPane.setSpacing(25);
        
        Label diffLabel = new Label("Select Difficulty");
        diffLabel.setId("diff-label");
        Button easyButton = new Button("Easy");
        easyButton.setPrefSize(300,75);
        //TODO Implement easyButton to create a new game
        //easyButton.setOnAction(e -> newGame(EASY);
        
        Button hardButton = new Button("Hard");
        hardButton.setPrefSize(300,75);
        //TODO Implement hardButton to create a new game
        //hardButton.setOnAction(e -> newGame(HARD);
        
        Button backButton = new Button("Back");
        backButton.setPrefSize(300,75);
        backButton.setOnAction(e -> transitionFirstPane());

        this.difficultyPane.getChildren().addAll(diffLabel, easyButton, hardButton, backButton);

        
        this.masterPane = new BorderPane();
        this.masterPane.setTop(titlePane);
        this.masterPane.setCenter(this.firstPane);
        this.masterPane.setBackground(new Background(new BackgroundImage(new Image("strategicWar/background.jpg", true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        this.scene = new Scene(masterPane, 1000, 750);
        this.scene.getStylesheets().add("strategicWar/style.css");
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
