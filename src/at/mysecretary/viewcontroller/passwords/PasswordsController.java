
package at.mysecretary.viewcontroller.passwords;

import at.mysecretary.model.PasswordsGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.controlsfx.control.ToggleSwitch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PasswordsController implements Initializable {

        public PasswordsController() {
        }

        @FXML
        ToggleSwitch tgl_characters = new ToggleSwitch();
        @FXML
        ToggleSwitch tgl_specialcharacters = new ToggleSwitch();
        @FXML
        ToggleSwitch tgl_numbers = new ToggleSwitch();
        @FXML
        TextField txt_result = new TextField();
        @FXML
        Slider sld_pswdLength = new Slider();
        @FXML
        TextField txt_pswdSize = new TextField();
        @FXML
        ImageView img_star1 = new ImageView();
        @FXML
        ImageView img_star2 = new ImageView();
        @FXML
        ImageView img_star3 = new ImageView();
        @FXML
        ImageView img_star4 = new ImageView();
        @FXML
        ImageView img_star5 = new ImageView();

        boolean isLetter = false;
        boolean isNumber = false;
        boolean isSpecialCharacter = false;
        int passwdLength = 8;

        public void show_passwords(Pane pn_secPane) {
            Pane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Passwords.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            pn_secPane.getChildren().add(newLoadedPane);
        }

        @FXML
        public void setPswdSize() throws FileNotFoundException{

            passwdLength = (int) sld_pswdLength.getValue();

            txt_pswdSize.setText(String.valueOf((int)passwdLength));

        }

        @FXML
        public void generatePassword() throws FileNotFoundException {
            PasswordsGenerator generator = new PasswordsGenerator(isLetter, isSpecialCharacter, isNumber, passwdLength);
            String generatedPassword;
            generatedPassword = generator.generatePassword(isLetter, isSpecialCharacter, isNumber, passwdLength);
            txt_result.setText(generatedPassword);
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            tgl_characters.selectedProperty().addListener((observable, oldValue, newValue) -> {
                isLetter = !isLetter;
            });

        tgl_specialcharacters.selectedProperty().addListener((observable, oldValue, newValue) -> {
            isSpecialCharacter = !isSpecialCharacter;
        });

        tgl_numbers.selectedProperty().addListener((observable, oldValue, newValue) -> {
            isNumber = !isNumber;
        });

    }
}
