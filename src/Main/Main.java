package Main;


import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.fxml.*;

/** Launches the application. */
public class Main extends Application {

    public static Stage loginStage = new Stage();
    public static Stage schedStage = new Stage();

    /** Creates the javafx stages for the application.
     *
     * @param stage
     * @throws Exception
     * */
    @Override
    public void start(Stage stage) throws Exception {
        Parent loginLoader = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        Scene loginScene = new Scene(loginLoader);
        stage.setScene(loginScene);
        stage.setTitle("Login");
        stage.show();
        loginStage = stage;

        Parent schedLoader = FXMLLoader.load(getClass().getResource("../Views/Sched.fxml"));
        Scene schedScene = new Scene(schedLoader);
        schedStage.setScene(schedScene);
        schedStage.setTitle("Scheduler");
        schedStage.hide();
    }

    /** Opens the jdbc connection, populates the initial observable lists from the mysql database, and launches the javafx stages.
     *
     * @param args
     * @throws Exception
     * */
    public static void main(String[] args) throws Exception {
        JDBC.openConn();
        JDBC.populateUsersTable();
        JDBC.populateContactsTable();
        JDBC.populateFirstLDsTable();
        JDBC.populateCountriesTable();
        FirstLD.populateFirstLDNames();
        FirstLD.populateUSTerritories();
        FirstLD.populateUKTerritories();
        FirstLD.populateCanadaTerritories();
        Country.populateCountryNames();
        User.populateNames();
        Contact.populateContactNames();
        JDBC.populateCustsTable();
        Cust.populateCustNames();
        JDBC.populateApptsTable();
        launch(args);
        JDBC.closeConn();
    }

    /** Hides the login stage and shows the scheduler stage after successful login. */
    public static void openSched() {
        schedStage.show();
        schedStage.toFront();
        loginStage.hide();
    }
}
