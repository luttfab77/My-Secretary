package at.mysecretary.viewcontroller.calendar;

import at.mysecretary.model.Appointment;
import at.mysecretary.viewcontroller.note.NoteEditController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarItemController implements Initializable
{

    ArrayList<Appointment> appointments;

    @FXML
    VBox vbox_appointmentTitles;

    @FXML
    Label lbl_date;


    FXMLLoader fxmlLoader = null;
    CalendarListPerDateController calendarListPerDateController = null;
    Pane pane = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        appointments = new ArrayList<Appointment>();

        fxmlLoader = new FXMLLoader(getClass().getResource("CalendarListPerDate.fxml"));
        try
        {
            pane = fxmlLoader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        calendarListPerDateController = fxmlLoader.getController();
    }

    public void clickOnSingleCalendarItem()
    {
        calendarListPerDateController.appointments.addAll(appointments);
        calendarListPerDateController.fillFields();
        calendarListPerDateController.show_calendarListPerDate(CalendarController.actualPane,pane);
    }
}
