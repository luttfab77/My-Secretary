package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NoteController {
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_note;
    @FXML
    private TextField txt_title;
    @FXML
    private TextArea txt_description;

    public void addNotes(){

    }

    public void cancelNotes(){

    }

    public NoteController() {
    }

    public void show_note(Pane pn_secPane) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("Note.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }
}
