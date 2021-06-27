package at.mysecretary.viewcontroller.calendar;

import at.mysecretary.model.Appointment;
import at.mysecretary.model.Calendar;
import at.mysecretary.viewcontroller.note.NoteController;
import at.mysecretary.viewcontroller.note.NoteEditController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalendarListPerDateItemController implements Initializable
{
    @FXML
    Label lbl_title,lbl_startdate,lbl_enddate;


    FXMLLoader fxmlLoader = null;
    CalendarAppointmentEditController calendarAppointmentEditController = null;
    Pane pane = null;

    Appointment appointment;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        fxmlLoader = new FXMLLoader(getClass().getResource("CalendarAppointmentEdit.fxml"));
        try
        {
            pane = fxmlLoader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        calendarAppointmentEditController = fxmlLoader.getController();
    }

    public void setTitleDates(String title, String startdate, String enddate){
        lbl_title.setText(title);
        lbl_startdate.setText(startdate);
        lbl_enddate.setText(enddate);
    }

    public void clickOnCalendarListPerDateItem(){
        calendarAppointmentEditController.iAppointment = this.appointment;
        calendarAppointmentEditController.fillFields();
        calendarAppointmentEditController.show_noteEdit(CalendarController.actualPane,pane);
    }
}
