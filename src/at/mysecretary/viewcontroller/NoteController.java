package at.mysecretary.viewcontroller;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        fillFields();
    }


    public void show_note(Pane pn_secPane) {

        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("NoteList.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pn_secPane.getChildren().add(newLoadedPane);
    }


    private void fillFields() {

        FXMLLoader fxmlLoader = null;
        Node[] nodes = new Node[HomeController.currentUser.getNotes().size()];
        Object controller = null;
        for (int i = 0; i < nodes.length; i++) {
            try {
//                nodes[i] = FXMLLoader.load(getClass().getResource("NoteListItem.fxml"));
                fxmlLoader = fxmlLoader = new FXMLLoader(getClass().getResource("NoteListItem.fxml"));
                nodes[i] = fxmlLoader.load();

                nodes[i].setId(nodes[i].getId()+i);
                controller = fxmlLoader.getController();
                vbox_noteitemslist.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
