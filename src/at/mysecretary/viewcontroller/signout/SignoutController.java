package at.mysecretary.viewcontroller.signout;

import at.mysecretary.viewcontroller.dashboard.DashboardController;
import at.mysecretary.viewcontroller.login.LoginController;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SignoutController {

    /*
        Author: Luttenberger Fabian
        Created on: 25.06.2021
        Description: Handles the sign out button
                     Shows the login controller and closes the dashboard
     */

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
