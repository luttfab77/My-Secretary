package at.mysecretary.viewcontroller;

import at.mysecretary.model.Note;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NoteListItemController implements Initializable
{

    @FXML
    Label lbl_title;
    @FXML
    Label lbl_description;


    Note iNote;

    FXMLLoader fxmlLoader = null;
    NoteEditController noteEditController = null;
    Pane pane = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        fxmlLoader = new FXMLLoader(getClass().getResource("NoteEdit.fxml"));
        try
        {
            pane = fxmlLoader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        noteEditController = fxmlLoader.getController();
    }


    public void clickOnNoteListItem(){

        noteEditController.iNote = this.iNote;
        noteEditController.fillFields();
        noteEditController.show_noteEdit(NoteController.actualPane,pane);

    }

    public void setTitleDescription(String title, String description){
        lbl_title.setText(title);
        lbl_description.setText(description);
    }
}
