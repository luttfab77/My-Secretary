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
import java.time.LocalDate;
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

    LocalDate pickedDate;

    /**
     * Sets the appointments as new ArrayList of Appointments.
     * Then loads the CalendarListPerDate Pane into the pane variable, which is defined as global.
     * @param url
     * @param resourceBundle
     */
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

    /**
     * Handler for all the CalendarItem Elements.
     * If clicked, the CalendarListPerDate is shown, using the show_calendarListPerDate() Method
     * from CalendarListPerDateController.
     * The Fields from the next window are also filled.
     */
    public void clickOnSingleCalendarItem()
    {
        calendarListPerDateController.appointments.addAll(appointments);
        calendarListPerDateController.pickedDate = this.pickedDate;
        calendarListPerDateController.fillFields();
        calendarListPerDateController.show_calendarListPerDate(CalendarController.actualPane,pane);
    }
}
