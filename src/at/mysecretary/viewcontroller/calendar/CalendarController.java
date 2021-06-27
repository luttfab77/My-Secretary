package at.mysecretary.viewcontroller.calendar;

import at.mysecretary.viewcontroller.home.HomeController;
import at.mysecretary.viewcontroller.note.NoteListItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;
import java.util.ResourceBundle;

public class CalendarController implements Initializable
{
    @FXML
    Label lbl_yearmonth;

    @FXML
    Button btn_nextmonth,btn_prevmonth;

    @FXML
    GridPane gp_calendar;

    public static Pane actualPane;
    LocalDate actualYearMonth;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        actualYearMonth = LocalDate.now();
        lbl_yearmonth.setText(actualYearMonth.getYear()+" "+actualYearMonth.getMonth());
        fillFields();
    }


    public CalendarController() {
    }

    public void show_calendar(Pane pn_secPane) {
        actualPane = pn_secPane;

        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Calendar.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        actualPane.getChildren().add(newLoadedPane);
    }

    private void fillFields() {

        gp_calendar.getChildren().removeAll(gp_calendar.getChildren());

        YearMonth yearMonthObject = YearMonth.of(actualYearMonth.getYear(), actualYearMonth.getMonth().getValue());
        int daysInMonth = yearMonthObject.lengthOfMonth();
        int gprow = 0,gpcol = 0;


        FXMLLoader fxmlLoader = null;
        Node[] nodes = new Node[daysInMonth];
        CalendarItemController controller = null;


        for (int i = 0; i < nodes.length; i++) {
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("CalendarItem.fxml"));
                nodes[i] = fxmlLoader.load();
                nodes[i].setId(nodes[i].getId()+i);
                controller = fxmlLoader.getController();


                //Adds The Appointments to single CalendarItem, if the date matches.
                for (int j = 0; j < HomeController.currentUser.getCalendar().getAppointments().size(); j++)
                {
                    if (HomeController.currentUser.getCalendar().getAppointments().get(j).getStartdate().equals(
                            LocalDate.of(actualYearMonth.getYear(), actualYearMonth.getMonth(), (i + 1))))
                    {
                        if (HomeController.currentUser.getCalendar().getAppointments().get(j).getTitle().equals(""))
                        {
                            controller.vbox_appointmentTitles.getChildren().add(new Label("No Title..."));
                        }
                        else
                        {
                            controller.vbox_appointmentTitles.getChildren().add(new Label(HomeController.currentUser.getCalendar().getAppointments().get(j).getTitle()));
                        }
                        controller.appointments.add(HomeController.currentUser.getCalendar().getAppointments().get(j));
                    }
                }


                //Set date for single CalendarItems.
                controller.lbl_date.setText((i+1)+"."+actualYearMonth.getMonth().getValue()+".");
                controller.pickedDate = LocalDate.of(actualYearMonth.getYear(),actualYearMonth.getMonth(),(i+1));

                //Dings - adding Nodes (CalendarItems) to the Gridpane
                if (gpcol == 7)
                {
                    gprow++;
                    gpcol = 0;
                }
                gp_calendar.add(nodes[i],gpcol,gprow);
                gpcol++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void clickOnNextMonth(){
        actualYearMonth = actualYearMonth.plusMonths(1);
        lbl_yearmonth.setText(actualYearMonth.getYear()+" "+actualYearMonth.getMonth());
        fillFields();
    }

    @FXML
    public void clickOnPrevMonth(){
        actualYearMonth = actualYearMonth.minusMonths(1);
        lbl_yearmonth.setText(actualYearMonth.getYear()+" "+actualYearMonth.getMonth());
        fillFields();
    }
}
