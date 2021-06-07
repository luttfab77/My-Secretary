package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController {

    DashboardController dashboardController = new DashboardController();

    @FXML
    TextField txt_username;
    @FXML
    PasswordField pwd_password;

    @FXML
    private void signInUser() throws IOException {
        User signedinUser = SerializationFactory.getInstance().selectUserByUsernamePassword(txt_username.getText(), pwd_password.getText());
        LoginController.closeLogin();
        dashboardController.showDashboard();
    }

}
