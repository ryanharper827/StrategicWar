package strategicWar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainGUI main = new MainGUI();
        primaryStage.setScene(main.getScene());
        primaryStage.show();
    }

    public static void main(String args[])
    {
        launch(args);
    }
}
