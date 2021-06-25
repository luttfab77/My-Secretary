package at.mysecretary.viewcontroller;

import javafx.scene.layout.Pane;

import java.io.IOException;

public class SignoutController {

    /**
     * Default constructor
     */
    public SignoutController() {
    }

    /**
     * Closes the dashboard and shows the loginscreen again
     */
    public void show_signout(Pane pn_secPane) throws IOException {
        DashboardController.closeDashboard();
        LoginController loginController = new LoginController();
        loginController.showLogin();
    }
}
