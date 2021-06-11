package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class SignUpController {

    DashboardController dashboardController;

    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField pwd_password;
    @FXML
    private PasswordField pwd_passwordConfirm;

    @FXML
    private void signUpUser() throws IOException {
        User newUser = new User();
        newUser.setUsername(txt_username.getText().toLowerCase());
        if (txt_username.getText().equals("")) {
            txt_username.setPromptText("You have to enter a valid username!");
            resetFields();
        }
        else if (pwd_password.getText().equals("") || pwd_passwordConfirm.getText().equals("")) {
            txt_username.setPromptText("You have to enter a password!");
            resetFields();
        }
        else if (SerializationFactory.getInstance().exists(newUser)) {
            txt_username.setPromptText("This username is already taken!");
            resetFields();
        }
        else if (!pwd_password.getText().equals(pwd_passwordConfirm.getText())) {
            txt_username.setPromptText("Password doesn't match!");
            resetFields();
        }
        else if (pwd_password.getText().equals(pwd_passwordConfirm.getText())) {
            newUser.setPasswordHash(PasswordManagement.encryptPassword(pwd_password.getText()));
            System.out.println("Konnte erstellt werden");
            newUser.save();
            SerializationFactory.getInstance().persist();
            LoginController.closeLogin();
            DashboardController.currentUser = newUser;
            dashboardController = new DashboardController();
            dashboardController.showDashboard();
        }
    }

    @FXML
    private void resetFields() {
        txt_username.setText("");
        pwd_password.setText("");
        pwd_passwordConfirm.setText("");
    }
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
}