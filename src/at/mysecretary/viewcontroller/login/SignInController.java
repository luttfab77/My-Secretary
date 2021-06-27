package at.mysecretary.viewcontroller.login;

import at.mysecretary.model.SerializationFactory;
import at.mysecretary.model.User;
import at.mysecretary.viewcontroller.dashboard.DashboardController;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable
{

    // User: User that signed in
    User signedinUser;

    // Dashboard controller
    DashboardController dashboardController;

    // TextField: Contains the username typed in by the user
    @FXML
    TextField txt_username;

    // PasswordField: Contains the password typed in by the user
    @FXML
    PasswordField pwd_password;

    // ImageView: Shows the exit button
    @FXML
    ImageView img_exit;


    /**
     * Fills the ImageView with an Image
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        img_exit.setImage(new Image("/at/mysecretary/images/icons8_exit_30px.png"));
    }

    /**
     * This method trys to sign in the user
     */
    @FXML
    private void signInUser() throws IOException {
        // If the username exists and the password typed in belongs to the user
        if (SerializationFactory.getInstance().exists(SerializationFactory.getInstance().selectUserByUsernamePassword(txt_username.getText().toLowerCase().trim(), pwd_password.getText().trim()))) {
            // Signing in with the user typed in
            signedinUser = SerializationFactory.getInstance().selectUserByUsernamePassword(txt_username.getText().toLowerCase().trim(), pwd_password.getText().trim());
            System.out.printf("Signed in as %s\n", signedinUser.getUsername());
            // Closing the login controller
            LoginController.closeLogin();
            // Setting the current user for the home controller
            HomeController.currentUser = signedinUser;
            // Showing the dashboard controller
            dashboardController = new DashboardController();
            dashboardController.showDashboard();
        }
        // Otherwise the actor gets a notification that he entered the wrong username and / or password
        else {
            txt_username.setText("");
            txt_username.setPromptText("Wrong username and / or password!");
            pwd_password.setText("");
        }
    }

    /**
     * If the user presses the "ENTER" key he tries to call the method "signInUser"
     */
    @FXML
    public void checkEnter(){
        pwd_password.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                try {
                    signInUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @FXML
    public void exit(){
        Platform.exit();
    }
}
