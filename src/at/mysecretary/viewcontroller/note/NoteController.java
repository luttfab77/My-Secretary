package at.mysecretary.viewcontroller.note;

import at.mysecretary.model.Note;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NoteController implements Initializable
{
    @FXML
    private VBox vbox_noteitemslist;

    @FXML
    private Button btn_noteadd;


    public static Pane actualPane;


    /**
     * Uses the fillFields() Method from below, as it fills the NoteList with the Notes from currentUser.
     * Sets the Style for the (+) Button.
     * I don't know why I set the Style in here, but adding the image didn't work in CSS.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        fillFields();
        btn_noteadd.setStyle(btn_noteadd.getStyle()+ "\n" +
                "-fx-background-image: url('/at/mysecretary/images/icons8_plus_50px.png');\n" +
                "-fx-background-size: 22px 22px;\n" +
                "-fx-background-repeat: no-repeat;\n" +
                "-fx-background-position: center;");
    }


    /**
     * Shows the Note window as a Pane in the specified DashboardController part.
     * As Parameter there's the Pane, in which the new Pane should be loaded.
     * @param pn_secPane
     */
    public void show_note(Pane pn_secPane) {

        actualPane = pn_secPane;

        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NoteList.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        actualPane.getChildren().add(newLoadedPane);
    }

    /**
     * Method for the (+) Button in the NoteList.
     * Then shows the EditNote window.
     */
    public void add_Note(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NoteEdit.fxml"));
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

    /**
     * Fills the NoteList with the single Notes from the currentUser.
     */
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
