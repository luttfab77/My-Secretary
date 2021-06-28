package at.mysecretary.viewcontroller.note;

import at.mysecretary.model.Note;
import at.mysecretary.model.SerializationFactory;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class NoteEditController
{
    @FXML
    private TextField txt_notetitle;
    @FXML
    private TextArea txt_notedescription;


    Note iNote;
    Pane actualPane;


    /**
     * Saves the edited Note.
     * If it exists it's going to be removed and then added.
     * Otherwise, it just goes ahead to Method addNote() from below.
     */
    public void saveNote(){
        System.out.println("Save Note Button Clicked.");
        if (HomeController.currentUser.getNotes().contains(iNote)) {
            HomeController.currentUser.removeNote(iNote);
        }
        addNote();
    }

    /**
     * Adds a Note, usind the addNote() Method in User.
     */
    private void addNote(){
        iNote = new Note(txt_notedescription.getText(),txt_notetitle.getText());
        HomeController.currentUser.addNote(iNote);
        System.out.println(HomeController.currentUser.toString());
        HomeController.currentUser.save();
        SerializationFactory.getInstance().persist();
    }

    /**
     * Brings one back to the NoteList, using the show_note() Mehtod in NoteController.
     */
    public void cancelNote(){
        System.out.println("Cancel Note Button Clicked.");
        NoteController.actualPane.getChildren().remove(actualPane);
        NoteController iNoteController = new NoteController();
        iNoteController.show_note(NoteController.actualPane);
    }

    /**
     * Deletes the Note, if it exists, using the removeNote() Method in User.
     */
    public void deleteNote(){
        System.out.println("Delete Note Button Clicked.");
        HomeController.currentUser.removeNote(iNote);
        NoteController iNoteController = new NoteController();
        iNoteController.show_note(NoteController.actualPane);
        SerializationFactory.getInstance().persist();
    }


    /**
     * Fills the Title and Description with the details in the given Note.
     */
    public void fillFields()
    {
        txt_notetitle.setText(this.iNote.getTitle());
        txt_notedescription.setText(this.iNote.getDescription());
    }


    /**
     * Shows the NoteEdit window as a Pane in the specified NoteController part.
     * As Parameter there's the Pane, in which the new Pane should be loaded.
     * @param pn_secPane, pane
     */
    public void show_noteEdit(Pane pn_secPane, Pane pane) {

        actualPane = pane;

        pn_secPane.getChildren().add(actualPane);
    }
}
