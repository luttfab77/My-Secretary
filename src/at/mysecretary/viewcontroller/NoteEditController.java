package at.mysecretary.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NoteEditController
{

    @FXML
    private Button btn_notecancel;
    @FXML
    private Button btn_notedelete;
    @FXML
    private Button btn_notesave;
    @FXML
    private TextField txt_notetitle;
    @FXML
    private TextArea txt_notedescription;


    public void saveNote(){
        System.out.println("Save Note Button Clicked.");
    }

    public void cancelNote(){
        System.out.println("Cancel Note Button Clicked.");
    }

    public void deleteNote(){
        System.out.println("Delete Note Button Clicked.");

    }
}
