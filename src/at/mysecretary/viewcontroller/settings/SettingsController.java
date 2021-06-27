package at.mysecretary.viewcontroller.settings;

import at.mysecretary.model.PasswordManagement;
import at.mysecretary.model.SerializationFactory;
import at.mysecretary.themain.Main;
import at.mysecretary.viewcontroller.dashboard.DashboardController;
import at.mysecretary.viewcontroller.home.HomeController;
import at.mysecretary.viewcontroller.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    /*
        Author: Luttenberger Fabian
        Created on: 26.06.2021
        Changed on: 27.06.2021
        Description: Controller class for the Settings.fxml file
                     Handles all fields and updates the user if he changes e.g his first name
     */

    // TextField: TextField where the user enters his first name
    @FXML
    TextField txt_firstName;

    // TextField: TextField where the user enters his last name
    @FXML
    TextField txt_lastName;

    // PasswordField: PasswordField where the user has to enter his current password
    @FXML
    PasswordField pwd_currentPassword;

    // Label: Shows the text "Current Password"
    @FXML
    Label lbl_currentPassword;

    // PasswordField: PasswordField where the user can enter his new password
    @FXML
    PasswordField pwd_password;

    // PasswordField: PasswordField where the user has to enter the same password as in the "new password" field
    @FXML
    PasswordField pwd_confirmPassword;

    // HBox: This hbox is for the new password field
    @FXML
    HBox hbox_password;

    // HBox: This hbox is for the confirm password field
    @FXML
    HBox hbox_confirmPassword;

    @FXML
    Button btn_deleteAccount;

    @FXML
    HBox hbox_delete;

    @FXML
    PasswordField pwd_deletePassword;

    @FXML
    Button btn_finalizeDelete;

    // Boolean: True if the user wants to create a new password
    boolean isNewPassword;


    /**
     * Default constructor
     */
    public SettingsController () {
    }

    /**
     * Method gets called every time this class gets opened
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillFields();
    }


    /**
     * Shows the Settings.fxml file
     */
    public void show_settings(Pane pn_secPane) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }

    /**
     * Assigns the TextFields for the first and last name with the specific values
     */
    private void fillFields() {
        txt_firstName.setText(HomeController.currentUser.getFirstname());
        txt_lastName.setText(HomeController.currentUser.getLastname());
    }


    /**
     * Allows the user to edit his first name
     */
    @FXML
    public void editFirstName() {
        txt_firstName.setDisable(false);
        txt_firstName.requestFocus();
    }

    /**
     * Allows the user to edit his last name
     */
    @FXML
    public void editLastName() {
        txt_lastName.setDisable(false);
        txt_lastName.requestFocus();
    }

    /**
     * Shows the hbox for the new password and the confirm password view
     */
    @FXML
    public void showChangePassword() {

        // If the entered password equals the password of the user
        if (Objects.equals(PasswordManagement.encryptPassword(pwd_currentPassword.getText()), HomeController.currentUser.getPasswordHash())) {
            // Shows the hboxes for the new password and the confirm password field
            hbox_password.setVisible(true);
            hbox_confirmPassword.setVisible(true);
            // Disables the current password field
            pwd_currentPassword.setDisable(true);

            // Automatically focusses the new password field
            pwd_password.requestFocus();
            isNewPassword = true;
        }
        // Otherwise
        else {
            // Resets the text for the current password and reminds the user, that he entered a wrong password
            pwd_currentPassword.setText("");
            pwd_currentPassword.setPromptText("Wrong Password!");
        }

    }

    /**
     * Gets called if the user presses on the "discard changes" button
     * Resets each field
     */
    @FXML
    public void discardChanges() {
        // Sets the text for the first name (First name of the current user)
        txt_firstName.setText(HomeController.currentUser.getFirstname());
        // Disables the TextField
        txt_firstName.setDisable(true);
        // Sets the text for the last name (Last name of the current user)
        txt_lastName.setText(HomeController.currentUser.getLastname());
        // Disables the TextField
        txt_lastName.setDisable(true);
        // Resets the text for the current password field
        pwd_currentPassword.setText("");
        pwd_currentPassword.setPromptText("");
        pwd_currentPassword.setDisable(false);
        // Makes the hbox for the new password and confirm password invisible
        hbox_password.setVisible(false);
        hbox_confirmPassword.setVisible(false);
    }

    /**
     * Gets called if the user presses on the "safe changes" button
     * Safes the new values for the user
     */
    @FXML
    public void saveChanges() {
        // The new first name of the current user is the entered first name
        HomeController.currentUser.setFirstname(txt_firstName.getText().trim());
        // The new last name of the current user is the entered last name
        HomeController.currentUser.setLastname(txt_lastName.getText().trim());
        // If the user wants a new password and the new password and confirm password are the same
        if (isNewPassword && pwd_password.getText().trim().equals(pwd_confirmPassword.getText().trim())) {
            // Update the user's password
            HomeController.currentUser.setPasswordHash(PasswordManagement.encryptPassword(pwd_password.getText().trim()));
        }
        // Save the user
        HomeController.currentUser.save();
        SerializationFactory.getInstance().persist();
    }

    /**
     * Gets called if the user presses on the "Delete Account" button
     * Makes the button "delete account" invisible and shows the delete hbox
     */
    @FXML
    public void handleDelete() {

        btn_deleteAccount.setVisible(false);
        hbox_delete.setVisible(true);

    }

    /**
     * Permanently checks if the entered password is correct
     * If it is correct the final Delete Account button gets shown
     */
    public void checkPassword() {
        btn_finalizeDelete.setVisible(HomeController.currentUser.getPasswordHash().equals(PasswordManagement.encryptPassword(pwd_deletePassword.getText().trim())));
    }

    /**
     * If the user clicks on the final "Delete Account" button the current user gets removed and the login stage gets called
     * @throws IOException
     */
    public void deleteAccount() throws IOException {
        System.out.println("Deleting user " + HomeController.currentUser.getUsername());
        HomeController.currentUser.remove();
        SerializationFactory.getInstance().persist();
        DashboardController.closeDashboard();
        LoginController loginController = new LoginController();
        loginController.showLogin();
    }

}
