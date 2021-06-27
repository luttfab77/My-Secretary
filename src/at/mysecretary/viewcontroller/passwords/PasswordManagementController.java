package at.mysecretary.viewcontroller.passwords;

import at.mysecretary.model.Password;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PasswordManagementController implements Initializable {

    @FXML
    TableView tvw_passwords;

    public TableColumn<Password,String> tcolumn_association;
    public TableColumn <Password, String> tcolumn_password;
    public TableColumn <Password, String> tcolumn_username;

    ObservableList<Password> observableList = FXCollections.observableArrayList();


    Pane actualPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < HomeController.currentUser.getPm().getPasswords().size(); i++) {

            System.out.println(HomeController.currentUser.getPm().getPasswords().get(i).getAssociation());
            observableList.add(new Password(HomeController.currentUser.getPm().getPasswords().get(i).getAssociation(),null, HomeController.currentUser.getPm().getPasswords().get(i).getPasswd(), HomeController.currentUser.getPm().getPasswords().get(i).getUsr()));

        }
        System.out.println(observableList);

        tcolumn_association.setCellValueFactory(new PropertyValueFactory<>("Association"));
        tcolumn_password.setCellValueFactory(new PropertyValueFactory<>("Passwd"));
        tcolumn_username.setCellValueFactory(new PropertyValueFactory<>("Usr"));
        tvw_passwords.setItems(observableList);
    }

    public void fillFields() {
        tvw_passwords.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void show_passwordManagement(Pane pn_secPane, Pane pane) {
        actualPane = pane;

        pn_secPane.getChildren().add(actualPane);
    }

    public void addPassword(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PasswordManagementAddPSWD.fxml"));
        Pane addPasswordPane = null;
        try
        {
            addPasswordPane = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        PasswordManagementAddPSWDController passwordManagementAddPSWDController = fxmlLoader.getController();
        passwordManagementAddPSWDController.show_addPassword(actualPane, addPasswordPane);
    }

    public void generatePassword(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Passwords.fxml"));
        Pane generatePasswordPane = null;
        try
        {
            generatePasswordPane = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        PasswordGeneratorController passwordGeneratorController = fxmlLoader.getController();
        //passwordManagementController.fillFields();
        passwordGeneratorController.show_generatePassword(actualPane, generatePasswordPane);
    }
}
