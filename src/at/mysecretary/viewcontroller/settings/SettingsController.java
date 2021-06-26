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

    @FXML
    TextField txt_firstName;

    @FXML
    TextField txt_lastName;

    @FXML
    PasswordField pwd_currentPassword;

    @FXML
    Label lbl_currentPassword;

    @FXML
    PasswordField pwd_password;

    @FXML
    PasswordField pwd_confirmPassword;

    @FXML
    HBox hbox_password;

    @FXML
    HBox hbox_confirmPassword;

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

    private void fillFields() {
        txt_firstName.setText(HomeController.currentUser.getFirstname());
        txt_lastName.setText(HomeController.currentUser.getLastname());
    }


    @FXML
    public void editFirstName() {
        txt_firstName.setDisable(false);
        txt_firstName.requestFocus();
    }


    @FXML
    public void editLastName() {
        txt_lastName.setDisable(false);
        txt_lastName.requestFocus();
    }

    @FXML
    public void showChangePassword() {

        if (Objects.equals(PasswordManagement.encryptPassword(pwd_currentPassword.getText()), HomeController.currentUser.getPasswordHash())) {
            hbox_password.setVisible(true);
            hbox_confirmPassword.setVisible(true);
            pwd_currentPassword.setDisable(true);

            pwd_password.requestFocus();
            isNewPassword = true;
        }
        else {
            pwd_currentPassword.setText("");
            pwd_currentPassword.setPromptText("Wrong Password!");
        }

    }

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
