package at.mysecretary.viewcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CalendarController {
    public CalendarController() {
    }

    public void show_calendar(Pane pn_secPane) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);


    }
}
