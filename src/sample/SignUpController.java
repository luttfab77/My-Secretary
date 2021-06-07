package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {

    DashboardController dashboardController = new DashboardController();

    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField pwd_password;
    @FXML
    private PasswordField pwd_passwordConfirm;

    @FXML
    private void signUpUser() throws IOException {
        User newUser = new User();
        newUser.setUsername(txt_username.getText());
        if (txt_username.getText().equals("")) {
            txt_username.setText("You have to enter a valid username!");
        }
        else if (pwd_password.getText().equals("") || pwd_passwordConfirm.getText().equals("")) {
            txt_username.setText("You have to enter a password!");
        }
        else if (SerializationFactory.getInstance().exists(newUser)) {
            txt_username.setText("This username is already taken!");
        }
        else if (pwd_password.getText().equals(pwd_passwordConfirm.getText())) {
            newUser.setPasswordHash(PasswordManagement.encryptPassword(pwd_password.getText()));
            System.out.println("Konnte erstellt werden");
            newUser.save();
            SerializationFactory.getInstance().persist();
            LoginController.closeLogin();
            dashboardController.showDashboard();
        }
    }

}
