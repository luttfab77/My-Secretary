package at.mysecretary.viewcontroller.calendar;

import at.mysecretary.model.Appointment;
import at.mysecretary.model.Calendar;
import at.mysecretary.model.Note;
import at.mysecretary.model.SerializationFactory;
import at.mysecretary.viewcontroller.home.HomeController;
import at.mysecretary.viewcontroller.note.NoteController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    LocalDate pickedDate;


    /**
     * Saves the edited Apponitment into the Calendar using the addAppointment() Method from below.
     * If it exists, it's deleted and added again as new Appointment.
     */
    public void saveAppointment(){
        System.out.println("Save Appointment Button Clicked.");
        if (HomeController.currentUser.getCalendar().getAppointments().contains(iAppointment)) {
            HomeController.currentUser.getCalendar().removeAppointment(iAppointment);
        }
        addAppointment();
    }

    /**
     * Adds the Appointment using the addAppointment() Method from Calendar.
     * If the User doesn't type in a correct Date, the pickedDate will be set.
     * pickedDate is a LocalDate, which is set, when the User clicks on a CalendarListPerDateItem Element
     * from the Gridpane in Calendar.
     */
    private void addAppointment(){
        try
        {
            iAppointment = new Appointment(LocalDate.parse(txt_appointmentstartdate.getText()), LocalDate.parse(txt_appointmentenddate.getText()), txt_appointmenttitle.getText(), txt_appointmentdescription.getText());
            HomeController.currentUser.getCalendar().addAppointment(iAppointment);
            System.out.println(HomeController.currentUser.getCalendar().toString());
            HomeController.currentUser.save();
            SerializationFactory.getInstance().persist();
        }catch (DateTimeParseException dtpE){
            iAppointment = new Appointment(pickedDate, pickedDate, txt_appointmenttitle.getText(), txt_appointmentdescription.getText());
            txt_appointmentstartdate.setText(pickedDate.toString());
            txt_appointmentenddate.setText(pickedDate.toString());
            HomeController.currentUser.getCalendar().addAppointment(iAppointment);
            System.out.println(HomeController.currentUser.getCalendar().toString());
            HomeController.currentUser.save();
            SerializationFactory.getInstance().persist();
            System.out.println("I need a Date. Just a DATE. I will just take the Date, where u clicked on the Calendar. (Was beim Calendar geclickt wurde.)");
        }
    }

    /**
     * Cancels the Appointment.
     * Goes back to the Calendar, where the Gridpane is, using the show_calendar Method from CalendarController.
     */
    public void cancelAppointment(){
        System.out.println("Cancel Appointment Button Clicked.");
        CalendarController.actualPane.getChildren().remove(actualPane);
        CalendarController iCalendarController = new CalendarController();
        iCalendarController.show_calendar(CalendarController.actualPane);
    }

    /**
     * Deletes the actual Appointment, if it exists, using the removeAppointment() Method in Calendar.
     * Then returnst to the CalendarView using the show_calendar Methode from CalendarController.
     */
    public void deleteAppointment(){
        System.out.println("Delete Appointment Button Clicked.");
        HomeController.currentUser.getCalendar().removeAppointment(iAppointment);
        CalendarController iCalendarController = new CalendarController();
        iCalendarController.show_calendar(CalendarController.actualPane);
        SerializationFactory.getInstance().persist();
    }

    /**
     * Shows the Edit window for the picked Appointment.
     * @param pn_secPane
     * @param pane
     */
    public void show_noteEdit(Pane pn_secPane, Pane pane)
    {

        actualPane = pane;

        pn_secPane.getChildren().add(actualPane);
    }

    /**
     * Sets the Title, Description, Startdate and Enddate into the TextFields and TextAreas.
     */
    public void fillFields()
    {
        txt_appointmenttitle.setText(this.iAppointment.getTitle());
        txt_appointmentdescription.setText(this.iAppointment.getDescription());
        txt_appointmentstartdate.setText(this.iAppointment.getStartdate().toString());
        txt_appointmentenddate.setText(this.iAppointment.getEnddate().toString());
    }
}
