package at.mysecretary.viewcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.InflaterInputStream;

public class NoteListItemController
{

    @FXML
    Label lbl_title;
    @FXML
    Label lbl_description;



    public void clickOnNoteListItem(){
        System.out.println("Ich wurde gedrückt.");
    }

    public void setTitleDescription(String title, String description){
        lbl_title.setText(title);
        lbl_description.setText(description);
    }
}
