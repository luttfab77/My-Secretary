package at.mysecretary.viewcontroller.passwords;

import at.mysecretary.model.*;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.controlsfx.control.ToggleSwitch;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class PasswordsController {

    // PasswordManagementController as reference
    PasswordManagementController passwordManagementController = new PasswordManagementController();

    // PasswordField: PasswordField where the user has to enter his password
    @FXML
    PasswordField pwd_password;

    // TextFiel: TextField where the user has to enter his username
    @FXML
    TextField txt_username;

    static Pane actualPane;

    /**
     * Shows the PasswordManagementLogin.fxml file
     *
     */
    public void show_passwords(Pane pn_secPane) {
        actualPane = pn_secPane;
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PasswordManagementLogin.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }

    /**
     * This method trys to sign in the user
     */
    @FXML
    private void signInUser() throws IOException {

        // Creating a new User with the entered username and password
        User passwordUser = new User();
        passwordUser.setUsername(txt_username.getText().toLowerCase());
        passwordUser.setPasswordHash(PasswordManagement.encryptPassword(pwd_password.getText().trim()));

        // If the username equals the current user and the password typed in belongs to the user
        if ((HomeController.currentUser.getUsername().equals(passwordUser.getUsername())) && (HomeController.currentUser.getPasswordHash().equals(passwordUser.getPasswordHash()))) {

            // Showing the PasswordManagement.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PasswordManagement.fxml"));
            Pane pane = null;
            try
            {
                pane = fxmlLoader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
            PasswordManagementController passwordManagementController = fxmlLoader.getController();
            passwordManagementController.fillFields();
            passwordManagementController.show_passwordManagement(actualPane, pane);
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


}