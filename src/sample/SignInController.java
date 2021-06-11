package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
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
