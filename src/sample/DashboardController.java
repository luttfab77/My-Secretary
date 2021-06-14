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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    static User currentUser;

    double x,y;


    @FXML
    private Button btn_home;

    @FXML
    private Button btn_passwords;


    @FXML
    private Button btn_calendar;


    @FXML
    private Button btn_note;


    @FXML
    private Button btn_secretary;

    @FXML
    private Button btn_settings;


    @FXML
    private Button btn_signout;

    @FXML
    private Pane pn_secPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HomeController homeController= new HomeController();
        homeController.show_home(pn_secPane);
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

    public void handleClicks(ActionEvent actionEvent) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        String chosenPanel;
        chosenPanel=actionEvent.getSource().toString();
        chosenPanel=chosenPanel.substring(chosenPanel.indexOf('_')+1,chosenPanel.indexOf(','));

        String chosenController= chosenPanel.substring(0,1);
        chosenController = "sample."+chosenPanel.replaceFirst(chosenController,chosenController.toUpperCase())+"Controller";

        Class<?> controllerClass = Class.forName(chosenController);
        Object controller = controllerClass.newInstance();
        Method handleMethod = controllerClass.getDeclaredMethod("show_"+chosenPanel,Pane.class);
        handleMethod.invoke(controller,pn_secPane);
    }





}
