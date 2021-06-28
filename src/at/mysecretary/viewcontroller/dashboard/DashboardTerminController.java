package at.mysecretary.viewcontroller.dashboard;

import at.mysecretary.model.Appointment;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardTerminController implements Initializable {
    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_description;

    @FXML
    private Label lbl_startDate;

    @FXML
    private Label lbl_endDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Appointment appointment: HomeController.currentUser.getCalendar().getAppointments()) {
                lbl_name.setText(appointment.getTitle());
                lbl_description.setText(appointment.getDescription());
                lbl_startDate.setText(appointment.getStartdate().toString());
        }
    }
}
