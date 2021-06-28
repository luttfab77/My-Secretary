package at.mysecretary.viewcontroller.home;

import at.mysecretary.model.Appointment;
import at.mysecretary.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    /**
     * User that is currently logged in
     */
    public static User currentUser;

    /**
     * Vbox that lists all appointments
     */
    @FXML
    private VBox pnl_items;


    /**
     * Label, that shows a Welcome message including the logged in username
     */
    @FXML
    Label lbl_username;


    /**
     * Label, that shows the amount of passwords the user has saved
     */
    @FXML
    Label lbl_passwords;


    /**
     * Label, that shows the amount of appointments the user has
     */
    @FXML
    Label lbl_appointments;


    /**
     * Label, that shows the amount of notes the user has created
     */
    @FXML
    Label lbl_notes;


    /**
     * Default Constructor to create an Object in DashboardController to invoke the method with
     */
    public HomeController() {
    }


    /**
     * Method that gets called everytime the Class is used
     * Calls the Method fillFields() to fill the above mentioned Labels
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillFields();
    }


    /**
     * Method that shows the Panel, that was chosen in the Dashboard, in this case the Home-Screen
     * @param pn_secPane is the "right side of the dashboard" which the new Panel is inserted into
     */
    public void show_home(Pane pn_secPane) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../home/Home.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }


    /**
     * Method, that fills the Home Screen with appointments
     * And the labels mentioned above
     */
    private void fillFields() {

        Node[] nodes = new Node[currentUser.getCalendar().getAppointments().size()];
        int i=0;
        for (Appointment appointment: HomeController.currentUser.getCalendar().getAppointments()) {
            if (appointment.getStartdate().equals(LocalDate.now())) {
                try {
                    final int j = i;
                    nodes[i] = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../dashboard/DashboardTermin.fxml")));

                    nodes[i].setOnMouseEntered(event -> {
                        nodes[j].setStyle("-fx-border-radius: 5;" + "-fx-border-color: #f42d76");
                    });
                    nodes[i].setOnMouseExited(event -> {
                        nodes[j].setStyle("-fx-background-color: #38383e");
                    });
                    pnl_items.getChildren().add(nodes[i]);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            i++;
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


