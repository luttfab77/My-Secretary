package at.mysecretary.viewcontroller.calendar;

import at.mysecretary.model.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CalendarAppointmentEditController
{
    @FXML
    Button btn_appointmentsave, btn_appointmentdelete, btn_appointmentcancel;

    @FXML
    TextField txt_appointmentstartdate, txt_appointmentenddate, txt_appointmenttitle;

    @FXML
    TextArea txt_appointmentdescription;


    Appointment iAppointment;
    Pane actualPane;


    public void show_noteEdit(Pane pn_secPane, Pane pane)
    {

        actualPane = pane;

        pn_secPane.getChildren().add(actualPane);
    }

    public void fillFields()
    {
        txt_appointmenttitle.setText(this.iAppointment.getTitle());
        txt_appointmentdescription.setText(this.iAppointment.getDescription());
        txt_appointmentstartdate.setText(this.iAppointment.getStartdate().toString());
        txt_appointmentenddate.setText(this.iAppointment.getEnddate().toString());
    }
}
