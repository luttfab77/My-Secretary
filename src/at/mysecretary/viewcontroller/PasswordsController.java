
package at.mysecretary.viewcontroller;

import at.mysecretary.model.PasswordsGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PasswordsController {

        public PasswordsController() {
        }


        @FXML
        ToggleButton tb_letter = new ToggleButton();
        @FXML
        ToggleButton tb_specialChar = new ToggleButton();
        @FXML
        ToggleButton tb_number = new ToggleButton();
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
        @FXML
        TextField txt_letterStatus = new TextField();
        @FXML
        TextField txt_specialCharStatus = new TextField();
        @FXML
        TextField txt_numberStatus = new TextField();

        boolean truthLetter = false;
        boolean truthNumber = false;
        boolean truthSpecialChar = false;
        double passwdLength = 8;

        public void show_passwords(Pane pn_secPane) {
            Pane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("Passwords.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            pn_secPane.getChildren().add(newLoadedPane);
        }

        @FXML
        public void setLetter() throws FileNotFoundException {

            if(tb_letter.isSelected()){
                truthLetter = true;
                txt_letterStatus.setText("ON");
            }else{
                truthLetter = false;
                txt_letterStatus.setText("OFF");
            }

            System.out.println(truthLetter);

            PasswordsGenerator generatorLetter = new PasswordsGenerator(truthLetter, truthSpecialChar, truthNumber, passwdLength, txt_result, img_star1, img_star2, img_star3, img_star4, img_star5);
        }

        @FXML
        public void setSpecialChar() throws FileNotFoundException {

            if(tb_specialChar.isSelected()){
                truthSpecialChar = true;
                txt_specialCharStatus.setText("ON");
            }else{
                truthSpecialChar = false;
                txt_specialCharStatus.setText("OFF");
            }

            PasswordsGenerator generatorLetter = new PasswordsGenerator(truthLetter, truthSpecialChar, truthNumber, passwdLength, txt_result, img_star1, img_star2, img_star3, img_star4, img_star5);
        }

        @FXML
        public void setNumber() throws FileNotFoundException{

            if(tb_number.isSelected()){
                truthNumber = true;
                txt_numberStatus.setText("ON");
            }else{
                truthNumber = false;
                txt_numberStatus.setText("OFF");
            }

            PasswordsGenerator generatorLetter = new PasswordsGenerator(truthLetter, truthSpecialChar, truthNumber, passwdLength, txt_result, img_star1, img_star2, img_star3, img_star4, img_star5);
        }

        @FXML
        public void setPswdSize() throws FileNotFoundException{

            passwdLength = sld_pswdLength.getValue();

            txt_pswdSize.setText(String.valueOf((int)passwdLength));

            PasswordsGenerator generatorLetter = new PasswordsGenerator(truthLetter, truthSpecialChar, truthNumber, passwdLength, txt_result, img_star1, img_star2, img_star3, img_star4, img_star5);
        }
    }
