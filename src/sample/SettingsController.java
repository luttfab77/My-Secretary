package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SettingsController {
    public SettingsController() {
    }

    public void show_settings(Pane pn_secPane) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }
}
