package at.mysecretary.viewcontroller;

import at.mysecretary.model.PasswordManagement;
import at.mysecretary.model.SerializationFactory;
import at.mysecretary.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class SignUpController {

    /**
     * Dashboard controller
     */
    DashboardController dashboardController;

    /**
     * TextField: Contains the username typed in by the user
     */
    @FXML
    private TextField txt_username;

    /**
     * PasswordField: Contains the password typed in by the user
     */
    @FXML
    private PasswordField pwd_password;

    /**
     * PasswordField: Contains the password typed in by the user and has to be the same as in the other PasswordField
     */
    @FXML
    private PasswordField pwd_passwordConfirm;

    /**
     * This method trys to create a new User and sign him up
     */
    @FXML
    private void signUpUser() throws IOException {
        User newUser = new User();
        newUser.setUsername(txt_username.getText().toLowerCase().trim());
        // If the entered username is empty he gets remembered to enter an username
        if (txt_username.getText().trim().equals("")) {
            txt_username.setPromptText("You have to enter a valid username!");
            resetFields();
        }
        // If one of the PasswordFields is empty the user gets remembered to type in some passwords
        else if (pwd_password.getText().trim().equals("") || pwd_passwordConfirm.getText().trim().equals("")) {
            txt_username.setPromptText("You have to enter a password!");
            resetFields();
        }
        // If the typed in User already exists the user gets remembered to choose an other username
        else if (SerializationFactory.getInstance().exists(newUser)) {
            txt_username.setPromptText("This username is already taken!");
            resetFields();
        }
        // If the entered Password doesn't equal the confirmed Password
        else if (!pwd_password.getText().trim().equals(pwd_passwordConfirm.getText().trim())) {
            txt_username.setPromptText("Password doesn't match!");
            resetFields();
        }
        // If the user doen't already exist and he typed in both passwords correct a new User gets created
        else if (pwd_password.getText().trim().equals(pwd_passwordConfirm.getText().trim())) {
            // New user gets created and saved
            newUser.setPasswordHash(PasswordManagement.encryptPassword(pwd_password.getText().trim()));
            System.out.println("Konnte erstellt werden");
            newUser.save();
            SerializationFactory.getInstance().persist();
            // Login stage gets closed
            LoginController.closeLogin();
            // Setting the current user for the home controller
            HomeController.currentUser = newUser;
            // Showing the dashboard controller
            dashboardController = new DashboardController();
            dashboardController.showDashboard();
        }
    }

    /**
     * This method resets all the fields to an empty text
     */
    @FXML
    private void resetFields() {
        txt_username.setText("");
        pwd_password.setText("");
        pwd_passwordConfirm.setText("");
    }

    /**
     * If the user presses the "ENTER" key he tries to call the method "signInUser"
     */
    @FXML
    public void checkEnter(){
        pwd_passwordConfirm.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                try {
                    signUpUser();
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