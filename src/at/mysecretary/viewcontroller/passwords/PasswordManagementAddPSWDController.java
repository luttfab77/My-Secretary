package at.mysecretary.viewcontroller.passwords;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PasswordManagementAddPSWDController {

    @FXML
    TextField txt_association;

    @FXML
    TextField txt_password;

    @FXML
    TextField txt_username;

    public void show_addPassword(Pane pn_secPane, Pane pane) {
        Pane actualPane = pane;

        pn_secPane.getChildren().add(actualPane);
    }
}
