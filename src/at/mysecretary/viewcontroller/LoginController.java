package at.mysecretary.viewcontroller;

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

    /**
     * Stage for the Login Windows
     */
    static Stage stage = new Stage();

    /**
     * vbox
     */
    @FXML
    private VBox vbox;

    /**
     * Parent for the fxml
     */
    private Parent fxml;

    /**
     * Counter of current amount of logins
     */
    static int counterLogins = 0;


    /**
     * Method gets called every time this class gets opened
     */
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        createSignInTransition();
    }

    /**
     * Gets called if the user presses on the SignIn button
     * Sets a transition and shows the SignIn.fxml file
     */
    @FXML
    private void openSignin() {
        createSignInTransition();
    }

    /**
     * Gets called if the user presses on the SignUp button
     * Sets a transition and shows the SignUp.fxml file
     */
    @FXML
    private void openSignup(){
        TranslateTransition transition = setTransition();
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

    /**
     * Shows the login stage
     */
    public void showLogin() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("MySecretary");
        stage.getIcons().add(new Image("/at/mysecretary/images/Logos.png"));
        stage.setScene(scene);

        if (counterLogins == 0) {
            stage.initStyle(StageStyle.TRANSPARENT);
            counterLogins = counterLogins + 1;
        }
        stage.show();
    }

    /**
     * Creates a Transition for the SignIn.fxml file
     */
    private void createSignInTransition() {
        TranslateTransition transition = setTransition();
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

    /**
     * Sets a TranslateTransition
     * @return new TranslateDuration that lasts for 1 second
     */
    private TranslateTransition setTransition() {
        return new TranslateTransition(Duration.seconds(1), vbox);
    }

    /**
     * Closes the login stage
     */
    public static void closeLogin() {
        stage.close();
    }


}