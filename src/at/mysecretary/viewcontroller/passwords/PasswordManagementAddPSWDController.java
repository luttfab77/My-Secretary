package at.mysecretary.viewcontroller.passwords;

import at.mysecretary.model.Password;
import at.mysecretary.model.SerializationFactory;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PasswordManagementAddPSWDController {

    // TextField: TextField that contains the association of the new entry
    @FXML
    TextField txt_association;

    // TextField: TextField that contains the password of the new entry
    @FXML
    TextField txt_password;

    // TextField: TextField that contains the username of the new entry
    @FXML
    TextField txt_username;

    /**
     * Method shows the add Password pane
     */
    public void show_addPassword(Pane pn_secPane, Pane pane) {
        Pane actualPane = pane;

        pn_secPane.getChildren().add(actualPane);
    }

    /**
     * Method gets called when the user presses on the "Add Password" button
     * This method adds the entered password with its values to the user's PasswordManagement
     */
    @FXML
    public void handleAddPassword() {
        // Creating a new password with the entered values
        Password iPassword = new Password();
        iPassword.setAssociation(txt_association.getText().trim());
        iPassword.setPasswd(txt_password.getText());
        iPassword.setUsr(txt_username.getText().trim());

        // Adding the created password to the user's PasswordManagement
        HomeController.currentUser.getPm().addPassword(iPassword);

        // Saving the user
        HomeController.currentUser.save();
        SerializationFactory.getInstance().persist();
        // Show the password management login again
        PasswordsController passwordsController = new PasswordsController();
        passwordsController.show_passwords(PasswordsController.actualPane);

    }
}
