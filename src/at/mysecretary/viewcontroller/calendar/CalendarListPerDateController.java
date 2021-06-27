package at.mysecretary.viewcontroller.calendar;

import at.mysecretary.model.Appointment;
import at.mysecretary.model.Note;
import at.mysecretary.viewcontroller.home.HomeController;
import at.mysecretary.viewcontroller.note.NoteEditController;
import at.mysecretary.viewcontroller.note.NoteListItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CalendarListPerDateController implements Initializable
{
    @FXML
    Button btn_appointmentadd,btn_appointmentretreat;

    @FXML
    VBox vbox_appointmentlist;

    ArrayList<Appointment> appointments;
    Pane actualPane;
    LocalDate pickedDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        btn_appointmentadd.setStyle(btn_appointmentadd.getStyle()+ "\n" +
                "-fx-background-image: url('/at/mysecretary/images/icons8_plus_50px.png');\n" +
                "-fx-background-size: 22px 22px;\n" +
                "-fx-background-repeat: no-repeat;\n" +
                "-fx-background-position: center;");
        btn_appointmentretreat.setStyle(btn_appointmentretreat.getStyle()+ "\n" +
                "-fx-background-image: url('/at/mysecretary/images/icons8_Sign_Out_32px.png');\n" +
                "-fx-background-size: 22px 22px;\n" +
                "-fx-background-repeat: no-repeat;\n" +
                "-fx-background-position: center;");
        appointments = new ArrayList<Appointment>();
        fillFields();
    }


    public void add_Appointment(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CalendarAppointmentEdit.fxml"));
        Pane pane = null;
        try
        {
            pane = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        CalendarAppointmentEditController calendarAppointmentEditController = fxmlLoader.getController();

        calendarAppointmentEditController.iAppointment = new Appointment();
        calendarAppointmentEditController.iAppointment.setStartdate(pickedDate);
        calendarAppointmentEditController.iAppointment.setEnddate(pickedDate);
        calendarAppointmentEditController.iAppointment.setUsername(HomeController.currentUser.getUsername());
        calendarAppointmentEditController.iAppointment.setDescription("");
        calendarAppointmentEditController.iAppointment.setTitle("");
        calendarAppointmentEditController.pickedDate = pickedDate;
        calendarAppointmentEditController.fillFields();
        calendarAppointmentEditController.show_noteEdit(actualPane,pane);
    }

    public void fillFields(){
        FXMLLoader fxmlLoader = null;
        Node[] nodes = new Node[appointments.size()];
        CalendarListPerDateItemController controller = null;

        for (int i = 0; i < nodes.length; i++) {
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("CalendarListPerDateItem.fxml"));
                nodes[i] = fxmlLoader.load();
                nodes[i].setId(nodes[i].getId()+i);

                controller = fxmlLoader.getController();
                controller.setTitleDates(appointments.get(i).getTitle(),appointments.get(i).getStartdate().toString(),appointments.get(i).getEnddate().toString());
                controller.appointment = appointments.get(i);
                controller.pickedDate = pickedDate;


                vbox_appointmentlist.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void show_calendarListPerDate(Pane pn_secPane, Pane pane){
        actualPane = pane;
        pn_secPane.getChildren().add(actualPane);
    }

    public void onClickRetreat(){
        System.out.println("Retreat Button Clicked.");
        CalendarController.actualPane.getChildren().remove(actualPane);
        CalendarController iCalendarController = new CalendarController();
        iCalendarController.show_calendar(CalendarController.actualPane);
    }
}
