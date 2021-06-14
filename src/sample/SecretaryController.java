package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SecretaryController {
    public SecretaryController() {
    }

    public void show_secretary(Pane pn_secPane) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("Secretary.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }
}
