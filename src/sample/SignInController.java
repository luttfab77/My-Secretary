package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController {

    User signedinUser;
    DashboardController dashboardController;

    @FXML
    TextField txt_username;
    @FXML
    PasswordField pwd_password;

    @FXML
    private void signInUser() throws IOException {
        if (SerializationFactory.getInstance().exists(SerializationFactory.getInstance().selectUserByUsernamePassword(txt_username.getText().toLowerCase(), pwd_password.getText()))) {
            signedinUser = SerializationFactory.getInstance().selectUserByUsernamePassword(txt_username.getText().toLowerCase(), pwd_password.getText());
            System.out.printf("Signed in as %s\n", signedinUser.getUsername());
            LoginController.closeLogin();
            DashboardController.currentUser = signedinUser;
            dashboardController = new DashboardController();
            dashboardController.showDashboard();
        }
        else {
            txt_username.setText("");
            txt_username.setPromptText("Wrong username or password!");
            pwd_password.setText("");
        }
    }

}
