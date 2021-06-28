package at.mysecretary.viewcontroller.passwords;

import at.mysecretary.model.Password;
import at.mysecretary.model.PasswordManagement;
import at.mysecretary.model.PasswordsGenerator;
import at.mysecretary.model.SerializationFactory;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class PasswordGeneratorController implements Initializable {

    // TextField: TextField that contains the association of the new entry
    @FXML
    TextField txt_association;

    // TextField: TextField that contains the username of the new entry
    @FXML
    TextField txt_username;

    // ToggleSwitch: ToggleSwitch for the characters
    // on -> Letters in the password
    // off -> No letters in the password
    @FXML
    ToggleSwitch tgl_characters = new ToggleSwitch();

    // ToggleSwitch: ToggleSwitch for the special characters
    // on -> Special characters in the password
    // off -> No special characters in the password
    @FXML
    ToggleSwitch tgl_specialcharacters = new ToggleSwitch();

    // ToggleSwitch: ToggleSwitch for the numbers
    // on -> Numbers in the password
    // off -> No numbers in the password
    @FXML
    ToggleSwitch tgl_numbers = new ToggleSwitch();

    // Slider: Slider for the password length
    @FXML
    Slider sld_pswdLength = new Slider();

    // TextField: Shows the generated password
    @FXML
    TextField txt_result = new TextField();

    // TextField: Shows the length of the password
    @FXML
    TextField txt_pswdSize = new TextField();

    boolean isLetter = false;
    boolean isNumber = false;
    boolean isSpecialCharacter = false;
    int passwdLength = 8;

    /**
     * Default constructor
     */
    public PasswordGeneratorController() {}

    /**
     * Adds an Listener for the several ToggleSlides
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // If the state of the tgl_characters ToggleSlide changes -> isLetter = true if it was false || isLetter = false if it was true
        tgl_characters.selectedProperty().addListener((observable, oldValue, newValue) -> isLetter = !isLetter);

        // If the state of the tgl_specialcharacters ToggleSlide changes -> isLetter = true if it was false || isLetter = false if it was true
        tgl_specialcharacters.selectedProperty().addListener((observable, oldValue, newValue) -> isSpecialCharacter = !isSpecialCharacter);

        // If the state of the tgl_numbers ToggleSlide changes -> isLetter = true if it was false || isLetter = false if it was true
        tgl_numbers.selectedProperty().addListener((observable, oldValue, newValue) -> isNumber = !isNumber);

    }

    /**
     * Shows the Passwords.fxml file
     */
    public void show_passwords(Pane pn_secPane) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PasswordManagementLogin.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }

    /**
     * Gets called if the user changes the passwordLength Slider
     */
    @FXML
    public void setPasswordLength() {

        passwdLength = (int) sld_pswdLength.getValue();
        txt_pswdSize.setText(String.valueOf(passwdLength));

    }

    /**
     * Gets called if the user presses on the "Generate" button
     * Generates a random password with the chosen settings
     */
    @FXML
    public void generatePassword() {
        // Creating a new PasswordsGenerator
        PasswordsGenerator generator = new PasswordsGenerator(isLetter, isSpecialCharacter, isNumber, passwdLength);
        String generatedPassword;
        generatedPassword = generator.generatePassword(isLetter, isSpecialCharacter, isNumber, passwdLength);
        // GeneratedPassword gets the new text in the txt_result TextField
        txt_result.setText(generatedPassword);
    }

    /**
     * Method gets called when the user presses on the "Add Password" button
     * This method adds the generated password with its values to the user's PasswordManagement
     */
    @FXML
    public void handleGeneratePassword() {
        // Creating a new password with the entered values
        Password iPassword = new Password();
        iPassword.setAssociation(txt_association.getText().trim());
        iPassword.setPasswd(txt_result.getText());
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

    /**
     * Method shows the generate password pane
     */
    public void show_generatePassword(Pane pn_secPane, Pane pane) {
        Pane actualPane = pane;

        pn_secPane.getChildren().add(actualPane);
    }


}
