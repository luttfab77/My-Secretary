package at.mysecretary.viewcontroller;

import at.mysecretary.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
    public static User currentUser;

    @FXML
    private VBox pnl_items;


    @FXML
    Label lbl_username;
    @FXML
    Label lbl_passwords;
    @FXML
    Label lbl_appointments;
    @FXML
    Label lbl_notes;

    public HomeController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillFields();
    }

    public void show_home(Pane pn_secPane) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }

    private void fillFields() {

        Node[] nodes = new Node[currentUser.getCalendar().getAppointments().size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("DashboardTermin.fxml"));

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-border-radius: 5;"+"-fx-border-color: #f42d76");
                });
                nodes[i].setOnMouseExited(event ->{
                    nodes[j].setStyle("-fx-background-color: #38383e");
                });
                pnl_items.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String username = currentUser.getUsername().substring(0,1);

        if (currentUser.getFirstname().equals("") && currentUser.getLastname().equals("")) {
            lbl_username.setText("Welcome, " + currentUser.getUsername().replaceFirst(username,username.toUpperCase()));
        }
        else {
            lbl_username.setText("Welcome, " + currentUser.getFirstname().replaceFirst(currentUser.getFirstname().substring(0, 1), currentUser.getFirstname().substring(0, 1).toUpperCase()) + " " + currentUser.getLastname().replaceFirst(currentUser.getLastname().substring(0, 1), currentUser.getLastname().substring(0, 1).toUpperCase()));
        }
        lbl_passwords.setText(String.valueOf(currentUser.getPm().getPasswords().size()));
        lbl_appointments.setText(String.valueOf(currentUser.getCalendar().getAppointments().size()));
        lbl_notes.setText(String.valueOf(currentUser.getNotes().size()));
    }

}


