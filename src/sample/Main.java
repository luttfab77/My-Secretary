package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoginController loginController = new LoginController();
        loginController.showLogin();
    }

    @Override
    public void init() throws Exception {
        SerializationFactory.getInstance().restore();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        SerializationFactory.getInstance().persist();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
