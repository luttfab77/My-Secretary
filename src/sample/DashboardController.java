package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    static User currentUser;

    double x,y;

    @FXML
    private VBox pnl_items = null;
    Pane[] paneArray = new Pane[7];


    @FXML
    private Button btn_home;
    @FXML
    private Pane pnl_home;


    @FXML
    Label lbl_username;
    @FXML
    Label lbl_passwords;
    @FXML
    Label lbl_appointments;
    @FXML
    Label lbl_notes;


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

        fillFields();
    }

    public void showDashboard() throws IOException {
        Stage dashboardStage = new Stage();
        Parent DashboardRoot = FXMLLoader.load(Objects.requireNonNull(DashboardController.class.getResource("Dashboard.fxml")));
        dashboardStage.setTitle("MySecretary");
        dashboardStage.getIcons().add(new Image("/images/Logos.png"));
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

        for (Pane pane : paneArray) {
            if(pane.getId().equals("pnl_"+chosenPanel)) {
                pane.setVisible(true);
                //Method m = DashboardController.class.getDeclaredMethod("handle_"+chosenPanel);
                //m.invoke(null);
                if(pane.getId().equals("pnl_signout")){
                    handleSignout();
                }
            }
            else{
                pane.setVisible(false);
            }
        }
    }

    private void handleSignout(){
        System.exit(0);
    }

    private void fillFields() {
        Node[] nodes = new Node[currentUser.getCalendar().getAppointments().size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("DashboardTermin.fxml"));

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-border-radius: 5;"+"-fx-border-color: #f42d76");
                });
                nodes[i].setOnMouseExited(event ->{
                    nodes[j].setStyle("-fx-background-color: #38383e");
                });
                pnl_items.getChildren().add(nodes[i]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        paneArray[0]=pnl_home;
        pnl_home.setVisible(true);

        paneArray[1]=pnl_passwords;
        pnl_passwords.setVisible(false);

        paneArray[2]=pnl_calendar;
        pnl_calendar.setVisible(false);

        paneArray[3]=pnl_notes;
        pnl_notes.setVisible(false);

        paneArray[4]=pnl_secretary;
        pnl_secretary.setVisible(false);

        paneArray[5]=pnl_settings;
        pnl_settings.setVisible(false);

        paneArray[6]=pnl_signout;
        pnl_signout.setVisible(false);


        String username = currentUser.getUsername().substring(0,1);

        lbl_username.setText("Welcome, " + currentUser.getUsername().replace(username,username.toUpperCase()));
        lbl_passwords.setText(String.valueOf(currentUser.getPm().getPasswords().size()));
        lbl_appointments.setText(String.valueOf(currentUser.getCalendar().getAppointments().size()));
        lbl_notes.setText(String.valueOf(currentUser.getNotes().size()));
    }


}
