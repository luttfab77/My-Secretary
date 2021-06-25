package at.mysecretary.viewcontroller;

import at.mysecretary.model.Note;
import at.mysecretary.model.SerializationFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NoteEditController
{
    @FXML
    private TextField txt_notetitle;
    @FXML
    private TextArea txt_notedescription;


    Note iNote;
    Pane actualPane;


    public void saveNote(){
        System.out.println("Save Note Button Clicked.");
        if (HomeController.currentUser.getNotes().contains(iNote)) {
            HomeController.currentUser.removeNote(iNote);
        }
        addNote();
    }

    private void addNote(){
        Note cacheNote = new Note(txt_notedescription.getText(),txt_notetitle.getText());
        HomeController.currentUser.addNote(cacheNote);
        System.out.println(HomeController.currentUser.toString());
        HomeController.currentUser.save();
        SerializationFactory.getInstance().persist();
    }

    public void cancelNote(){
        System.out.println("Cancel Note Button Clicked.");
        NoteController.actualPane.getChildren().remove(actualPane);
        NoteController iNoteController = new NoteController();
        iNoteController.show_note(NoteController.actualPane);
    }

    public void deleteNote(){
        System.out.println("Delete Note Button Clicked.");
        HomeController.currentUser.removeNote(iNote);
        NoteController iNoteController = new NoteController();
        iNoteController.show_note(NoteController.actualPane);
        SerializationFactory.getInstance().persist();
    }



    public void fillFields()
    {
        txt_notetitle.setText(this.iNote.getTitle());
        txt_notedescription.setText(this.iNote.getDescription());
    }

    public void show_noteEdit(Pane pn_secPane, Pane pane) {

        actualPane = pane;

        pn_secPane.getChildren().add(actualPane);
    }
}
