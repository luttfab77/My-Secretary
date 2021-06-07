package sample;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    static Stage stage = new Stage();

    @FXML
    private VBox vbox;
    private Parent fxml;




    @Override
    public void initialize (URL url, ResourceBundle rb) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), vbox);
        transition.setToX(vbox.getLayoutX() * 20);
        transition.play();
        transition.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignIn.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ignored) { }
        });
    }
    @FXML
    private void openSignin() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), vbox);
        transition.setToX(vbox.getLayoutX() * 20);
        transition.play();
        transition.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignIn.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ignored) { }
        });
    }
    @FXML
    private void openSignup(){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), vbox);
        transition.setToX(0);
        transition.play();
        transition.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch(IOException ignored) { }
        });
    }

    public void showLogin() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("MySecretary");
        stage.getIcons().add(new Image("/images/Logos.png"));
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void closeLogin() {
        stage.close();
    }

}
