package at.mysecretary.viewcontroller.dashboard;

import at.mysecretary.model.User;
import at.mysecretary.viewcontroller.home.HomeController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    /**
     * Counts how many times the user has been logged in and the dashboard has been opened
     */
    static int counterDashboards = 0;


    /**
     * The stage that gets opened after logging in
     */
    static Stage dashboardStage = new Stage();


    /**
     * 2 double variables, so the dashboard gets exactly dragged to where it should be
     */
    double x,y;


    /**
     * Image of an exit button
     */
    @FXML
    private ImageView img_exit;


    /**
     * Image of a minimize button
     */
    @FXML
    private ImageView img_minimize;


    /**
     * The pane on the right side, which gets passed as a parameter
     * The opened Panels will be inserted into this panel
     */
    @FXML
    private Pane pn_secPane;


    /**
     * Method, that gets called when the Class is opened
     * Creates a new Controller and opens the show_home Method, Home-Screen is shown
     * Sets the image for exit and minimize button
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HomeController homeController= new HomeController();
        homeController.show_home(pn_secPane);
        img_exit.setImage(new Image("/at/mysecretary/images/icons8_exit_30px.png"));
        img_minimize.setImage(new Image("/at/mysecretary/images/icons8_minimize_48px.png"));
    }

    /**
     * Method that opens the Dashboard after being logged in
     */
    public void showDashboard() throws IOException {
        Parent DashboardRoot = FXMLLoader.load(Objects.requireNonNull(DashboardController.class.getResource("Dashboard.fxml")));
        dashboardStage.setTitle("MySecretary");
        dashboardStage.getIcons().add(new Image("/at/mysecretary/images/Logos.png"));
        dashboardStage.setScene(new Scene(DashboardRoot));

        if (counterDashboards == 0) {
            //set stage borderless
            dashboardStage.initStyle(StageStyle.UNDECORATED);
            counterDashboards = counterDashboards + 1;
        }

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


    /**
     * After a Dashboard-Button is pressed, this method gets called
     * it checks the selected Button and calls the connected Method via invoking
     */
    public void handleClicks(ActionEvent actionEvent) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        String chosenPanel;
        chosenPanel=actionEvent.getSource().toString();
        chosenPanel=chosenPanel.substring(chosenPanel.indexOf('_')+1,chosenPanel.indexOf(','));
        System.out.println("Opening " + chosenPanel);

        String chosenController= chosenPanel.substring(0,1);
        chosenController = "at.mysecretary.viewcontroller." +chosenPanel + "." +chosenPanel.replaceFirst(chosenController,chosenController.toUpperCase())+"Controller";

        Class<?> controllerClass = Class.forName(chosenController);
        Object controller = controllerClass.newInstance();
        Method handleMethod = controllerClass.getDeclaredMethod("show_"+chosenPanel,Pane.class);
        handleMethod.invoke(controller,pn_secPane);
    }


    /**
     * Closes the Dashboard
     */
    public static void closeDashboard() {
        dashboardStage.close();
    }


    /**
     * Exits the program
     */
    public void exit(){
        Platform.exit();
    }

    /**
     * Minimizes the program
     */
    public void minimize(){
       dashboardStage.setIconified(true);
    }
}
