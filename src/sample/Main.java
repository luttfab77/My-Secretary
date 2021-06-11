package sample;

import javafx.application.Application;
import java.util.List;
import java.util.ArrayList;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*
            Creating a testuser for test purposes
            Username: admin (CASE INSENSITIVE)
            Password: admin
         */
        PasswordManagement pm1 = new PasswordManagement();
        Password pwd1 = new Password();
        pwd1.setAssociation("INTELLIJ");
        pwd1.setUsr("KEVIN");
        pwd1.setPasswd("ADMIN");
        Password pwd2 = new Password();
        pwd2.setAssociation("ADOBE");
        pwd2.setUsr("CAHNTI");
        pwd2.setPasswd("ADMIN");

        Calendar calendar = new Calendar();
        Appointment appt1 = new Appointment();
        appt1.setTitle("TEAMS");
        appt1.setDescription("MATHE");

        Note note1 = new Note();
        note1.setTitle("Notiz 1");

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appt1);

        List<Password> passwords = new ArrayList<>();
        passwords.add(pwd1);
        passwords.add(pwd2);

        calendar.setAppointments(appointments);
        pm1.setPasswords(passwords);


        User admin = new User();
        admin.setUsername("admin");
        admin.setPasswordHash(PasswordManagement.encryptPassword("admin"));
        admin.setPm(pm1);
        admin.setCalendar(calendar);
        admin.save();
        SerializationFactory.getInstance().persist();

        // Show the first Scene
        LoginController loginController = new LoginController();
        loginController.showLogin();

    }

    @Override
    public void init() {
        SerializationFactory.getInstance().restore();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        SerializationFactory.getInstance().persist();
    }

    public static void main(String[] args) {
        launch(args);
    }
}