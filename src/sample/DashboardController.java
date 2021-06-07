package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    double x,y;
    Stage dashboardStage = new Stage();

    @FXML
    private VBox pnl_items = null;
    Pane[] paneArray = new Pane[7];


    @FXML
    private Button btn_home;
    @FXML
    private Pane pnl_home;



    @FXML
    private Button btn_passwords;
    @FXML
    private Pane pnl_passwords;


    @FXML
    private Button btn_calendar;
    @FXML
    private Pane pnl_calendar;


    @FXML
    private Button btn_notes;
    @FXML
    private Pane pnl_notes;


    @FXML
    private Button btn_secretary;
    @FXML
    private Pane pnl_secretary;


    @FXML
    private Button btn_settings;
    @FXML
    private Pane pnl_settings;


    @FXML
    private Button btn_signout;
    @FXML
    private Pane pnl_signout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("DashboardTermin.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnl_items.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pnl_home.toFront();

        paneArray[0]=pnl_home;
        paneArray[1]=pnl_passwords;
        paneArray[2]=pnl_calendar;
        paneArray[3]=pnl_notes;
        paneArray[4]=pnl_secretary;
        paneArray[5]=pnl_settings;
        paneArray[6]=pnl_signout;
    }

    public void showDashboard() throws IOException {
        Stage dashboardStage = new Stage();
        Parent DashboardRoot = FXMLLoader.load(Objects.requireNonNull(DashboardController.class.getResource("Dashboard.fxml")));
        dashboardStage.setScene(new Scene(DashboardRoot));
        //set stage borderless
        dashboardStage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        DashboardRoot.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        DashboardRoot.setOnMouseDragged(event -> {

            dashboardStage.setX(event.getScreenX() - x);
            dashboardStage.setY(event.getScreenY() - y);

        });
        dashboardStage.show();
    }

    public void handleClicks(ActionEvent actionEvent) {


        String chosenPanel;
        chosenPanel=actionEvent.getSource().toString();
        chosenPanel=chosenPanel.substring(chosenPanel.indexOf('_')+1,chosenPanel.indexOf(','));
        chosenPanel="pnl_"+chosenPanel;

        for (Pane pane : paneArray) {
            if (pane.getId().equals(chosenPanel)) {
                pane.toFront();
            }
        }
    }
}
