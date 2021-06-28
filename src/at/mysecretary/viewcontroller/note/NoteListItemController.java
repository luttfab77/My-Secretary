package at.mysecretary.viewcontroller.note;

import at.mysecretary.model.Note;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
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


    /**
     * Sets up the NoteEditController and loads the window (Pane) into the pane, which is declared as global.
     * @param url
     * @param resourceBundle
     */
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


    /**
     * This is the Handler for the HBox, on which the user can click.
     * Then he is redirected to the NoteEdit window, with the show_noteEdit() Method from NoteEditController.
     */
    public void clickOnNoteListItem(){

        noteEditController.iNote = this.iNote;
        noteEditController.fillFields();
        noteEditController.show_noteEdit(NoteController.actualPane,pane);

    }

    /**
     * Sets The title and Description for the Single NoteListItem, shich is shown in the List.
     * @param title
     * @param description
     */
    public void setTitleDescription(String title, String description){
        lbl_title.setText(title);
        lbl_description.setText(description);
    }
}
