package at.mysecretary.viewcontroller.note;

import at.mysecretary.model.Note;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NoteController implements Initializable
{
    @FXML
    private VBox vbox_noteitemslist;

    @FXML
    private Button btn_noteadd;


    public static Pane actualPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        fillFields();
    }


    public void show_note(Pane pn_secPane) {

        actualPane = pn_secPane;

        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("NoteList.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        actualPane.getChildren().add(newLoadedPane);
    }

    public void add_Note(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notes/NoteEdit.fxml"));
        Pane pane = null;
        try
        {
            pane = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        NoteEditController noteEditController = fxmlLoader.getController();

        noteEditController.iNote = new Note();
        noteEditController.fillFields();
        noteEditController.show_noteEdit(actualPane,pane);
    }


    private void fillFields() {

        FXMLLoader fxmlLoader = null;
        Node[] nodes = new Node[HomeController.currentUser.getNotes().size()];
        NoteListItemController controller = null;
        for (int i = 0; i < nodes.length; i++) {
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("NoteListItem.fxml"));
                nodes[i] = fxmlLoader.load();
                nodes[i].setId(nodes[i].getId()+i);

                controller = fxmlLoader.getController();
                controller.setTitleDescription(HomeController.currentUser.getNotes().get(i).getTitle(), HomeController.currentUser.getNotes().get(i).getDescription());
                controller.iNote = HomeController.currentUser.getNotes().get(i);


                vbox_noteitemslist.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
