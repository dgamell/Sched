package Controllers;

import Main.Appt;
import Main.User;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static Main.Appt.getAllAppts;
import static Main.Main.openSched;

/** Initialize the Login page. */
public class LoginController implements Initializable {
    @FXML private Label locationText;
    @FXML private Label zoneField;
    @FXML private Label userText;
    @FXML private Label passText;
    @FXML private Label loginError;
    @FXML private TextField userField;
    @FXML private PasswordField passField;
    @FXML private Button exitLogin;
    @FXML private Button loginButton;
    @FXML private Button languageButton;

    private FadeTransition fade = new FadeTransition (Duration.millis( 5000));
    String l = Locale.getDefault().toString();

    /** Initialize fields. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zoneField.setText(ZoneId.systemDefault().toString());
        language();

        userField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        login();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        passField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        login();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /** Check username and password against a table pulled from JDBC, as well as record login attempts in login_activity.txt in main folder. Logs in if successful.
     *
     * @throws Exception
     * */
    @FXML
    private void login() throws Exception {
            boolean incomplete = false;
            if ( userField.getText().isEmpty() ) { userField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { userField.setStyle(null); }
            if ( passField.getText().isEmpty() ) { passField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { passField.setStyle(null); }
            if ( incomplete == true ) { return; }

            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);

            if (User.convertToPassword(userField.getText()).equals(passField.getText())) {
                openSched();
                checkFifteen(User.convertToID(userField.getText()));
                pw.println("User: \"" + userField.getText() + "\" logged in at " + LocalDateTime.now() + ".");
            } else {
                pw.println("UNSUCCESSFUL ATTEMPT TO LOG IN WITH USERNAME: \"" + userField.getText() + "\" at " + LocalDateTime.now() + ".");
                loginError.setVisible(true);
                fade.setNode(loginError);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setCycleCount(1);
                fade.playFromStart();
            }
        pw.close();
    }

    /** Closes program if exit key is clicked.
     *
     * @throws Exception
     * */
    @FXML
    private void exitLogin(ActionEvent event) throws Exception {
        System.exit(0);
    }

    /** Checks to see if login username has any appointments within the next fifteen minutes. Gives an alert either way.
     *
     * @param user int
     * */
    private void checkFifteen(int user) {
        ObservableList<Appt> all = getAllAppts();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime testStart = null;
        int count = 0;
        String message = "The following appointments are within the next fifteen minutes: \n\n\n";
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getUserFKID() != user) continue;
            testStart = all.get(i).getStart();
            if (now.isEqual(testStart) || (now.isAfter(testStart.minusMinutes(15)) && now.isBefore(testStart))) {
                message = message + "\"" + all.get(i).getTitle() + "\" (#" + all.get(i).getApptID() + ") at " + all.get(i).getStart() + " with " + all.get(i).getCust() + ".\n\n";
                count++;
            }
        }
        if (count > 0) {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
            conf.show();
        } else {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "You have no appointments scheduled within the next fifteen minutes.", ButtonType.OK);
            conf.show();
        }
    }

    /** Language button triggers language() to change login screen without changing the computer's locale. Convenience feature. */
    public void languageButton() {
        if (l.equals("en_US")) { l = "fr_FR"; }
        else if (l.equals("fr_FR")) { l = "en_US"; }
        else if ((l.equals("")) || (l.equals(null))) { l = "en_US"; }
        language();
    }

    /** Changes the login screen between english and french languages only. */
    private void language() {
        ResourceBundle rb = ResourceBundle.getBundle("login", Locale.getDefault());
        if (l == "en_US") { rb = ResourceBundle.getBundle("login_en"); }
        if (l == "fr_FR") { rb = ResourceBundle.getBundle("login_fr"); }
        locationText.setText(rb.getString("Location"));
        userText.setText(rb.getString("Username"));
        passText.setText(rb.getString("Password"));
        loginError.setText(rb.getString("Error"));
        exitLogin.setText(rb.getString("Exit"));
        loginButton.setText(rb.getString("Login"));
        languageButton.setText(rb.getString("Language"));
    }
}
