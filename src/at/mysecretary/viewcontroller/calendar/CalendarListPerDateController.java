package at.mysecretary.viewcontroller.calendar;

import at.mysecretary.model.Appointment;
import at.mysecretary.viewcontroller.home.HomeController;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarListPerDateController implements Initializable
{
    @FXML
    Button btn_appointmentadd;

    @FXML
    VBox vbox_appointmentlist;

    ArrayList<Appointment> appointments;
    Pane actualPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        appointments = new ArrayList<Appointment>();
        fillFields();
    }


    public void add_Appointment(){
        for (int i = 0; i < appointments.size(); i++)
        {
            System.out.println(appointments.get(i).toString());
        }
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
}
