package at.mysecretary.viewcontroller.settings;

import at.mysecretary.model.PasswordManagement;
import at.mysecretary.model.SerializationFactory;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    /**
     * TextField: TextField where the user enters his first name
     */
    @FXML
    TextField txt_firstName;

    /**
     * TextField: TextField where the user enters his last name
     */
    @FXML
    TextField txt_lastName;

    /**
     * PasswordField: PasswordField where the user has to enter his current password
     */
    @FXML
    PasswordField pwd_currentPassword;

    /**
     * Label: Shows the text "Current Password"
     */
    @FXML
    Label lbl_currentPassword;

    /**
     * PasswordField: PasswordField where the user can enter his new password
     */
    @FXML
    PasswordField pwd_password;

    /**
     * PasswordField: PasswordField where the user has to enter the same password as in the "new password" field
     */
    @FXML
    PasswordField pwd_confirmPassword;

    /**
     * HBox: This hbox is for the new password field
     */
    @FXML
    HBox hbox_password;

    /**
     * HBox: This hbox is for the confirm password field
     */
    @FXML
    HBox hbox_confirmPassword;

    /**
     * Boolean: True if the user wants to create a new password
     */
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
            hbox_password.setVisible(true);
            hbox_confirmPassword.setVisible(true);
            pwd_currentPassword.setDisable(true);

            pwd_password.requestFocus();
            isNewPassword = true;
        }
        // Otherwise
        else {
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
        txt_firstName.setText(HomeController.currentUser.getFirstname());
        txt_firstName.setDisable(true);
        txt_lastName.setText(HomeController.currentUser.getLastname());
        pwd_currentPassword.setText("");
        pwd_currentPassword.setPromptText("");
        pwd_currentPassword.setDisable(false);
        hbox_password.setVisible(false);
        hbox_confirmPassword.setVisible(false);
    }

    /**
     * Gets called if the user presses on the "safe changes" button
     * Safes the new values for the user
     */
    @FXML
    public void safeChanges() {
        HomeController.currentUser.setFirstname(txt_firstName.getText().trim());
        HomeController.currentUser.setLastname(txt_lastName.getText().trim());
        if (isNewPassword && pwd_password.getText().trim().equals(pwd_confirmPassword.getText().trim())) {
            HomeController.currentUser.setPasswordHash(PasswordManagement.encryptPassword(pwd_password.getText().trim()));
        }
        HomeController.currentUser.save();
        SerializationFactory.getInstance().persist();
    }

}
