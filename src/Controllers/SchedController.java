package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;

import static Main.ThreeTable.getThreeTables;
import static Main.ThreeTable.populateThreeTables;
import static java.lang.Integer.parseInt;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import Main.*;
import static Main.JDBC.*;
import static Main.Appt.*;
import static Main.Cust.*;
import static Main.TwoTable.*;
import static Main.User.*;
import static Main.Contact.*;


/** Initializes the controller for the "Sched.fxml" view. */
public class SchedController implements Initializable {

    @FXML private Tab apptTab;
    @FXML private Tab custTab;
    @FXML private Tab reportTab;

    @FXML private TableView<Appt> appts;
    @FXML private TableColumn<Appt, Integer> apptID = new TableColumn<Appt, Integer>();
    @FXML private TableColumn<Appt, String> title = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> desc = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> type = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> loc = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> start = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> end = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> user = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, Integer> userFKID = new TableColumn<Appt, Integer>();
    @FXML private TableColumn<Appt, String> cust = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, Integer> custFKID = new TableColumn<Appt, Integer>();
    @FXML private TableColumn<Appt, String> contact = new TableColumn<Appt, String>();

    @FXML private TableView<Cust> custs;
    @FXML private TableColumn<Cust, Integer> custID = new TableColumn<Cust, Integer>();
    @FXML private TableColumn<Cust, String> custName = new TableColumn<Cust, String>();
    @FXML private TableColumn<Cust, String> phone = new TableColumn<Cust, String>();
    @FXML private TableColumn<Cust, String> addr = new TableColumn<Cust, String>();
    @FXML private TableColumn<Cust, String> zip = new TableColumn<Cust, String>();
    @FXML private TableColumn<Cust, String> territory = new TableColumn<Cust, String>();
    @FXML private TableColumn<Cust, String> country = new TableColumn<Cust, String>();
    @FXML private TableColumn<Cust, Integer> divID = new TableColumn<Cust, Integer>();
    @FXML private TableColumn<Cust, Integer> countryID = new TableColumn<Cust, Integer>();

    @FXML private TableView<Appt> reportByContact;
    @FXML private TableColumn<Appt, Integer> apptIDReport = new TableColumn<Appt, Integer>();
    @FXML private TableColumn<Appt, String> titleReport = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> descReport = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> typeReport = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> locReport = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> startReport = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> endReport = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, String> userReport = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, Integer> userFKIDReport = new TableColumn<Appt, Integer>();
    @FXML private TableColumn<Appt, String> custReport = new TableColumn<Appt, String>();
    @FXML private TableColumn<Appt, Integer> custFKIDReport = new TableColumn<Appt, Integer>();
    @FXML private TableColumn<Appt, String> contactReport = new TableColumn<Appt, String>();
    @FXML private ComboBox reportContact;

    @FXML private TableView<ThreeTable> threeTable;
    @FXML private TableColumn<ThreeTable, String> monthCol = new TableColumn<ThreeTable, String>();
    @FXML private TableColumn<ThreeTable, String> typeCol = new TableColumn<ThreeTable, String>();
    @FXML private TableColumn<ThreeTable, Integer> totalCol = new TableColumn<ThreeTable, Integer>();

    @FXML private TableView<TwoTable> reportByCust;
    @FXML private TableColumn<TwoTable, String> customerTable = new TableColumn<TwoTable, String>();
    @FXML private TableColumn<TwoTable, Integer> customerTotal = new TableColumn<TwoTable, Integer>();

    @FXML private Button resetApptSearch;
    @FXML private TextField titleSearch;
    @FXML private TextField descSearch;
    @FXML private TextField typeSearch;
    @FXML private TextField locSearch;
    @FXML private TextField startSearch;
    @FXML private TextField endSearch;
    @FXML private TextField userSearch;
    @FXML private TextField custSearch;
    @FXML private TextField contactSearch;

    @FXML private Button resetCustSearch;
    @FXML private TextField custNameSearch;
    @FXML private TextField phoneSearch;
    @FXML private TextField addrSearch;
    @FXML private TextField zipSearch;
    @FXML private TextField territorySearch;
    @FXML private TextField countrySearch;

    @FXML private TextField apptIDField;
    @FXML private TextField titleField;
    @FXML private TextField descField;
    @FXML private TextField typeField;
    @FXML private TextField locField;
    @FXML private DatePicker startDateField;
    @FXML private DatePicker endDateField;
    @FXML private ComboBox startTimeField;
    @FXML private ComboBox endTimeField;
    @FXML private ComboBox userField;
    @FXML private TextField userFKIDField;
    @FXML private ComboBox custField;
    @FXML private TextField custFKIDField;
    @FXML private ComboBox contactField;
    @FXML private TextField contactFKIDField;

    @FXML private Button apptUpdate ;
    @FXML private Button apptDelete ;
    @FXML private Button apptClear;

    @FXML private TextField custIDField;
    @FXML private TextField custNameField;
    @FXML private TextField phoneField;
    @FXML private TextField addrField;
    @FXML private TextField zipField;
    @FXML private ComboBox countryField;
    @FXML private ComboBox territoryField;
    @FXML private TextField countryIDField;
    @FXML private TextField divIDField;

    @FXML private Button custUpdate ;
    @FXML private Button custDelete ;
    @FXML private Button custClear;

    @FXML private Label apptDeletedText;
    @FXML private Label apptUpdatedText;
    @FXML private Label apptAddedText;
    @FXML private Label custDeletedText;
    @FXML private Label custUpdatedText;
    @FXML private Label custAddedText;
    @FXML private Label startBadText;
    @FXML private Label endBadText;
    @FXML private Label apptOverlapText;

    private FadeTransition fadeADT = new FadeTransition (Duration.millis( 5000));
    private FadeTransition fadeAUT = new FadeTransition (Duration.millis( 5000));
    private FadeTransition fadeAAT = new FadeTransition (Duration.millis( 5000));
    private FadeTransition fadeCDT = new FadeTransition (Duration.millis( 5000));
    private FadeTransition fadeCUT = new FadeTransition (Duration.millis( 5000));
    private FadeTransition fadeCAT = new FadeTransition (Duration.millis( 5000));
    private FadeTransition fadeSBT = new FadeTransition (Duration.millis( 5000));
    private FadeTransition fadeEBT = new FadeTransition (Duration.millis( 5000));
    private FadeTransition fadeAOT = new FadeTransition (Duration.millis( 10000));

    /** Initializes the cells, text fields, buttons, click events, etc for the Sched.fxml view. Used lambda expressions for all onMouseClicked and onAction triggers for cleaner code.
     *
     * @param url URL
     * @param rb ResourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apptID.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("apptID"));
        title.setCellValueFactory(new PropertyValueFactory<Appt, String>("title"));
        desc.setCellValueFactory(new PropertyValueFactory<Appt, String>("desc"));
        type.setCellValueFactory(new PropertyValueFactory<Appt, String>("type"));
        loc.setCellValueFactory(new PropertyValueFactory<Appt, String>("loc"));
        start.setCellValueFactory(new PropertyValueFactory<Appt, String>("start"));
        end.setCellValueFactory(new PropertyValueFactory<Appt, String>("end"));
        user.setCellValueFactory(new PropertyValueFactory<Appt, String>("user"));
        userFKID.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("userFKID"));
        cust.setCellValueFactory(new PropertyValueFactory<Appt, String>("cust"));
        custFKID.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("custFKID"));
        contact.setCellValueFactory(new PropertyValueFactory<Appt, String>("contact"));

        custID.setCellValueFactory(new PropertyValueFactory<Cust, Integer>("custID"));
        custName.setCellValueFactory(new PropertyValueFactory<Cust, String>("custName"));
        phone.setCellValueFactory(new PropertyValueFactory<Cust, String>("phone"));
        addr.setCellValueFactory(new PropertyValueFactory<Cust, String>("addr"));
        zip.setCellValueFactory(new PropertyValueFactory<Cust, String>("zip"));
        divID.setCellValueFactory(new PropertyValueFactory<Cust, Integer>("divID"));
        countryID.setCellValueFactory(new PropertyValueFactory<Cust, Integer>("countryID"));
        territory.setCellValueFactory(new PropertyValueFactory<Cust, String>("territory"));
        country.setCellValueFactory(new PropertyValueFactory<Cust, String>("country"));

        apptIDReport.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("apptID"));
        titleReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("title"));
        descReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("desc"));
        typeReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("type"));
        locReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("loc"));
        startReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("start"));
        endReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("end"));
        userReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("user"));
        userFKIDReport.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("userFKID"));
        custReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("cust"));
        custFKIDReport.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("custFKID"));
        contactReport.setCellValueFactory(new PropertyValueFactory<Appt, String>("contact"));

        monthCol.setCellValueFactory(new PropertyValueFactory<ThreeTable, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<ThreeTable, String>("type"));
        totalCol.setCellValueFactory(new PropertyValueFactory<ThreeTable, Integer>("total"));

        customerTable.setCellValueFactory(new PropertyValueFactory<TwoTable, String>("name"));
        customerTotal.setCellValueFactory(new PropertyValueFactory<TwoTable, Integer>("total"));

        ObservableList<LocalTime> times = FXCollections.observableArrayList();

        for (int i=7; i < 24; i++){
            LocalTime l = LocalTime.of(i, 0);
            LocalTime adjustedTime = LocalDateTime.of(LocalDate.now(), l).atZone(ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString())).toLocalTime();
            times.add(adjustedTime);
            l = LocalTime.of(i, 15);
            adjustedTime = LocalDateTime.of(LocalDate.now(), l).atZone(ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString())).toLocalTime();
            times.add(adjustedTime);
            l = LocalTime.of(i, 30);
            adjustedTime = LocalDateTime.of(LocalDate.now(), l).atZone(ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString())).toLocalTime();
            times.add(adjustedTime);
            l = LocalTime.of(i, 45);
            adjustedTime = LocalDateTime.of(LocalDate.now(), l).atZone(ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString())).toLocalTime();
            times.add(adjustedTime);
        }

        //convertToUTC(LocalDateTime.now());
        //convertFromUTC(LocalDateTime.now());
        //convertToEST(LocalDateTime.now());
        //convertFromEST(LocalDateTime.now());

        startTimeField.setItems(times);
        endTimeField.setItems(times);

        userField.setItems(allUserNames);
        custField.setItems(allCustNames);
        contactField.setItems(allContactNames);
        countryField.setItems(Country.allCountryNames);
        reportContact.setItems(allContactNames);

        userField.setValue("");
        custField.setValue("");
        contactField.setValue("");

        apptUpdate.setText("Add");
        custUpdate.setText("Add");
        appts.setItems(getAllAppts());
        custs.setItems(getAllCusts());

        titleField.setOnMouseClicked((event) -> titleField.setStyle(null));
        descField.setOnMouseClicked((event) -> descField.setStyle(null));
        typeField.setOnMouseClicked((event) -> typeField.setStyle(null));
        locField.setOnMouseClicked((event) -> locField.setStyle(null));
        userField.setOnMouseClicked((event) -> userField.setStyle(null));
        custField.setOnMouseClicked((event) -> custField.setStyle(null));
        contactField.setOnMouseClicked((event) -> contactField.setStyle(null));
        startDateField.setOnMouseClicked((event) -> startDateField.setStyle(null));
        startTimeField.setOnMouseClicked((event) -> startTimeField.setStyle(null));
        endDateField.setOnMouseClicked((event) -> endDateField.setStyle(null));
        endTimeField.setOnMouseClicked((event) -> endTimeField.setStyle(null));


        custIDField.setOnMouseClicked((event) ->  custIDField.setStyle(null));
        custNameField.setOnMouseClicked((event) ->  custNameField.setStyle(null));
        phoneField.setOnMouseClicked((event) ->  phoneField.setStyle(null));
        addrField.setOnMouseClicked((event) ->  addrField.setStyle(null));
        zipField.setOnMouseClicked((event) ->  zipField.setStyle(null));
        territoryField.setOnMouseClicked((event) ->  territoryField.setStyle(null));
        countryField.setOnMouseClicked((event) ->  countryField.setStyle(null));
        divIDField.setOnMouseClicked((event) ->  divIDField.setStyle(null));
        countryIDField.setOnMouseClicked((event) ->  countryIDField.setStyle(null));

        startTimeField.setOnAction((event) -> checkStartTime());
        endTimeField.setOnAction((event) -> checkEndTime());

        countryField.setOnAction((event) -> updateTerritoriesField());
        territoryField.setOnAction((event) -> updateTerritoryID());

        reportTab.setOnSelectionChanged((event) -> {
            populateThreeTables();
            threeTable.setItems(getThreeTables());
            populateAllCustomersTT();
            reportByCust.setItems(getAllCustomersTT());
        });

        userField.setOnAction((event) -> userFKIDField.setText(String.valueOf(User.convertToID(userField.getValue().toString()))));
        custField.setOnAction((event) -> custFKIDField.setText(String.valueOf(Cust.convertToID(custField.getValue().toString()))));
        contactField.setOnAction((event) -> contactFKIDField.setText(String.valueOf(Contact.convertToID(contactField.getValue().toString()))));
        reportContact.setOnAction((event) -> {
            populateContactAppts(Contact.convertToID(reportContact.getValue().toString()));
            reportByContact.setItems(contactAppts);
        });

        appts.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                if(appts.getSelectionModel().getSelectedItem() == null) {
                    apptDelete.setVisible(false);
                    setApptSelected(appts.getSelectionModel().getSelectedItem());
                    appts.refresh();
                }
                if(appts.getSelectionModel().getSelectedItem() != null) {
                    if ( (getApptSelected() != null) && (getApptSelected() != appts.getSelectionModel().getSelectedItem())) {
                        if (!discardApptChanges()) {
                            setApptSelection(getApptSelected());
                            return;
                        }
                    }
                    apptDelete.setVisible(true);
                    setApptSelected(appts.getSelectionModel().getSelectedItem());
                    populateApptSelected();
                    appts.refresh();
                }
            }
        });
        custs.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                if(custs.getSelectionModel().getSelectedItem() == null) {
                    custDelete.setVisible(false);
                    setCustSelected(custs.getSelectionModel().getSelectedItem());
                    custs.refresh();
                }
                if(custs.getSelectionModel().getSelectedItem() != null) {
                    if ( (getCustSelected() != null) && (getCustSelected() != custs.getSelectionModel().getSelectedItem())) {
                        if (!discardCustChanges()) {
                            setCustSelection(getCustSelected());
                            return;
                        }
                    }
                    custDelete.setVisible(true);
                    setCustSelected(custs.getSelectionModel().getSelectedItem());
                    populateCustSelected();
                    custs.refresh();
                }
            }
        } );

        titleSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                titleSearch();
            } } });
        descSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                    descSearch();
            } } });
        typeSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                typeSearch();
            } } });
        locSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                locSearch();
            } } });
        startSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                startSearch();
            } } });
        endSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                endSearch();
            } } });
        userSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                userSearch();
            } } });
        custSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                custSearch();
            } } });
        contactSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                contactSearch();
            } } });
        custNameSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                custNameSearch();
            } } });
        phoneSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                phoneSearch();
            } } });
        addrSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                addrSearch();
            } } });
        zipSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                zipSearch();
            } } });
        territorySearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                territorySearch();
            } } });
        countrySearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent ke) { if (ke.getCode().equals(KeyCode.ENTER)) {
                countrySearch();
            } } });

    }


    /** Shows all appointments when the respective radio button is clicked.
     *
     * @throws SQLException
     * */
    public void showAll() throws SQLException {
        appts.setItems(getAllAppts());
        clearApptSelected();
    }

    /** Shows appointments this month when the respective radio button is clicked.
     *
     * @throws SQLException
     * */
    public void month() throws SQLException {
        Appt.populateMonthAppts();
        appts.setItems(getMonthAppts());
        clearApptFields();
        clearApptSelected();
    }

    /** Shows appointments this week when the respective radio button is clicked.
     *
     * @throws SQLException
     * */
    public void week() throws SQLException {
        Appt.populateWeekAppts();
        appts.setItems(getWeekAppts());
        clearApptFields();
        clearApptSelected();
    }


    /** Populates the appointments input fields with whatever row has been clicked on in the table. */
    private void populateApptSelected() {
        Appt apptSelected = getApptSelected();
        if (apptSelected == null) { return; }
        apptIDField.setText(apptSelected.getApptID().toString());
        titleField.setText(apptSelected.getTitle());
        descField.setText(apptSelected.getDesc());
        typeField.setText(apptSelected.getType());
        locField.setText(apptSelected.getLoc());
        startDateField.setValue(apptSelected.getStart().toLocalDate());
        startTimeField.setValue(apptSelected.getStart().toLocalTime());
        endDateField.setValue(apptSelected.getEnd().toLocalDate());
        endTimeField.setValue(apptSelected.getEnd().toLocalTime());
        userFKIDField.setText(apptSelected.getUserFKID().toString());
        custFKIDField.setText(apptSelected.getCustFKID().toString());
        contactFKIDField.setText(apptSelected.getContactFKID().toString());
        userField.setValue(User.convertToName(parseInt(userFKIDField.getText())));
        custField.setValue(Cust.convertToName(parseInt(custFKIDField.getText())));
        contactField.setValue(Contact.convertToName(parseInt(contactFKIDField.getText())));
        contactFKIDField.setText(apptSelected.getContactFKID().toString());
        unredApptFields();
        apptUpdate.setText("Update");
        appts.refresh();
    }

    /** When the add/update button is pressed, this either adds a new appointment or updates an existing one if apptIDField has an existing ID in it.
     *
     * @throws SQLException
     * */
    public void apptAddUpdate() throws SQLException {
        if ( areApptFieldsEmpty() ) return;
        if ( checkStartTime() == "bad") return;
        if ( checkEndTime() == "bad") return;
        if ( (apptIDField.getText() == null) || (apptIDField.getText() == "")) {
            int overlap = checkOverlap(LocalDateTime.of(startDateField.getValue(), (LocalTime) startTimeField.getValue()), LocalDateTime.of(endDateField.getValue(), (LocalTime) endTimeField.getValue()), parseInt(custFKIDField.getText()), 0);
            if ( overlap > 0 ) {
                apptOverlapText.setVisible(true);
                apptOverlapText.setText("Times overlap with " + custField.getValue() + "'s appointment: #" + String.valueOf(overlap) + ".");
                fadeAOT.setNode(apptOverlapText);
                fadeAOT.setFromValue(1.0);
                fadeAOT.setToValue(0.0);
                fadeAOT.setCycleCount(1);
                fadeAOT.playFromStart();
                return;
            }
            setApptSelected(appts.getSelectionModel().getSelectedItem());
            int newID = JDBC.insAppt(titleField.getText(), descField.getText(), locField.getText(), typeField.getText(), LocalDateTime.of(startDateField.getValue(), (LocalTime) startTimeField.getValue()), LocalDateTime.of(endDateField.getValue(), (LocalTime) endTimeField.getValue()), parseInt(userFKIDField.getText()), parseInt(custFKIDField.getText()), parseInt(contactFKIDField.getText()));
            Appt newAppt = new Appt(
                    newID,
                    titleField.getText(),
                    descField.getText(),
                    typeField.getText(),
                    locField.getText(),
                    LocalDateTime.of(startDateField.getValue(), (LocalTime) startTimeField.getValue()),
                    LocalDateTime.of(endDateField.getValue(), (LocalTime) endTimeField.getValue()),
                    userField.getValue().toString(),
                    parseInt(userFKIDField.getText()),
                    custField.getValue().toString(),
                    parseInt(custFKIDField.getText()),
                    contactField.getValue().toString(),
                    parseInt(contactFKIDField.getText())
            );
            addAppt(newAppt);
            apptIDField.setText(String.valueOf(newID));
            setApptSelection(newAppt);
            apptDelete.setVisible(true);
            apptUpdate.setText("Update");
            appts.refresh();

            apptAddedText.setVisible(true);
            fadeAAT.setNode(apptAddedText);
            fadeAAT.setFromValue(1.0);
            fadeAAT.setToValue(0.0);
            fadeAAT.setCycleCount(1);
            fadeAAT.playFromStart();

        } else {
            int overlap = checkOverlap(LocalDateTime.of(startDateField.getValue(), (LocalTime) startTimeField.getValue()), LocalDateTime.of(endDateField.getValue(), (LocalTime) endTimeField.getValue()), parseInt(custFKIDField.getText()), parseInt(apptIDField.getText()));
            if ( overlap > 0 ) {
                apptOverlapText.setVisible(true);
                apptOverlapText.setText("Times overlap with " + custField.getValue() + "'s appointment: #" + String.valueOf(overlap) + ".");
                fadeAAT.setNode(apptOverlapText);
                fadeAAT.setFromValue(1.0);
                fadeAAT.setToValue(0.0);
                fadeAAT.setCycleCount(1);
                fadeAAT.playFromStart();
                return;
            }
            for (int j = 0; j < getAllAppts().size(); j++) {
                if (parseInt(apptIDField.getText()) == getAllAppts().get(j).getApptID()) {
                    JDBC.updtAppt(titleField.getText(), descField.getText(), locField.getText(), typeField.getText(), LocalDateTime.of(startDateField.getValue(), (LocalTime) startTimeField.getValue()), LocalDateTime.of(endDateField.getValue(), (LocalTime) endTimeField.getValue()), parseInt(userFKIDField.getText()), parseInt(custFKIDField.getText()), parseInt(contactFKIDField.getText()), parseInt(apptIDField.getText()));
                    updateAppt(
                            getAllAppts().get(j),
                            parseInt(apptIDField.getText()),
                            titleField.getText(),
                            descField.getText(),
                            typeField.getText(),
                            locField.getText(),
                            LocalDateTime.of(startDateField.getValue(), (LocalTime) startTimeField.getValue()),
                            LocalDateTime.of(endDateField.getValue(), (LocalTime) endTimeField.getValue()),
                            userField.getValue().toString(),
                            parseInt(userFKIDField.getText()),
                            custField.getValue().toString(),
                            parseInt(custFKIDField.getText()),
                            contactField.getValue().toString(),
                            parseInt(contactFKIDField.getText())
                    );
                    setApptSelection(getAllAppts().get(j));
                    apptDelete.setVisible(true);
                    apptUpdate.setText("Update");
                    appts.refresh();

                    apptUpdatedText.setVisible(true);
                    fadeAUT.setNode(apptUpdatedText);
                    fadeAUT.setFromValue(1.0);
                    fadeAUT.setToValue(0.0);
                    fadeAUT.setCycleCount(1);
                    fadeAUT.playFromStart();
                }
            }
        }
    }

    /** Checks if the user has edited any fields after clicking on an existing appointment; used to trigger a confirmation if the user clicks on a different record without saving their changes.
     *
     * @return true if edits are detected, false if not.
     * */
    private boolean checkApptChanges() {
        Appt apptSelected = getApptSelected();
        if (apptSelected == null) { return false; }
        boolean edited = false;
        if ( !apptIDField.getText().equals(apptSelected.getApptID().toString()) ) { edited = true; }
        if ( !titleField.getText().equals(apptSelected.getTitle()) ) { edited = true; }
        if ( !descField.getText().equals(apptSelected.getDesc()) ) { edited = true; }
        if ( !typeField.getText().equals(apptSelected.getType()) ) { edited = true; }
        if ( !locField.getText().equals(apptSelected.getLoc()) ) { edited = true; }
        //if ( !startDateField.getText().equals(apptSelected.getStartDateField()) ) { edited = true; }
        //if ( !startTimeField.getText().equals(apptSelected.getStartTimeField()) ) { edited = true; }
        //if ( !endField.getText().equals(apptSelected.getEndDateField()) ) { edited = true; }
        //if ( !endField.getText().equals(apptSelected.getEndTimeField()) ) { edited = true; }
        if ( !userFKIDField.getText().equals(apptSelected.getUserFKID().toString()) ) { edited = true; }
        if ( !custFKIDField.getText().equals(apptSelected.getCustFKID().toString()) ) { edited = true; }
        if ( !contactFKIDField.getText().equals(apptSelected.getContactFKID().toString()) ) { edited = true; }
        return edited;
    }

    /** Deletes the selected appointment, after a confirmation popup.
     *
     * @throws SQLException
     * */
    public void deleteSelectedAppt() throws SQLException {
        if ( getApptSelected() == null) { return; }
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to delete the selected appointment?", ButtonType.NO, ButtonType.YES);
        conf.showAndWait();
        if (conf.getResult() == ButtonType.NO) { return; }
        apptDeletedText.setText("Appointment #" + getApptSelected().getApptID().toString() + " (type: " + getApptSelected().getType() + ") has been deleted.");
        JDBC.delAppt(getApptSelected().getApptID());
        removeAppt(getApptSelected());
        appts.getSelectionModel().clearSelection();
        clearApptSelected();
        apptIDField.setText("");
        apptUpdate.setText("Add");
        apptDelete.setVisible(false);
        appts.refresh();

        apptDeletedText.setVisible(true);
        fadeADT.setNode(apptDeletedText);
        fadeADT.setFromValue(1.0);
        fadeADT.setToValue(0.0);
        fadeADT.setCycleCount(1);
        fadeADT.playFromStart();
    }

    /** Clears the fields in the editing section of the appointments tab, after checking for any changes and ensuring user wishes to discard changes.
     *
     * @throws SQLException
     * */
    public void clearApptFields() throws SQLException {
        if (!discardApptChanges()) { return; }
        unredApptFields();
        apptIDField.clear();
        titleField.clear();
        descField.clear();
        typeField.clear();
        locField.clear();
        startDateField.setValue(null);
        startTimeField.setValue(null);
        endDateField.setValue(null);
        endTimeField.setValue(null);
        userField.setValue("");
        custField.setValue("");
        contactField.setValue("");
        userFKIDField.clear();
        custFKIDField.clear();
        contactFKIDField.clear();
        appts.getSelectionModel().clearSelection();
        setApptSelected(null);
        apptUpdate.setText("Add");
        apptDelete.setVisible(false);
        appts.refresh();
    }

    /** Ensures proper row is selected and changes 'Add' button to 'Update' button.
     *
     * @param appt
     * */
    private void setApptSelection(Appt appt) {
        for (int j = 0; j < getAllAppts().size(); j++) {
            if (appt == getAllAppts().get(j)) {
                appts.getSelectionModel().select(j);
                appts.getFocusModel().focus(j);
                apptUpdate.setText("Update");
                unredApptFields();
                appts.refresh();
                return;
            }
        }
    }

    /** Dialog box that asks if user is sure they wish to discard their changes.
     *
     * @return true if yes, false if no.
     * */
    private boolean discardApptChanges() {
        boolean changed = checkApptChanges();
        if ( changed == true ) {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to discard your changes?", ButtonType.NO, ButtonType.YES);
            conf.showAndWait();
            if (conf.getResult() == ButtonType.NO) return false;
        }
        return true;
    }

    /** Checks if any fields are empty or incorrect and highlights those fields in red if so. Used before add/update.
     *
     * @return true if any fields are empty, false if none are empty.
     * */
    private boolean areApptFieldsEmpty() {
        boolean incomplete = false;
        if ( titleField.getText().isEmpty() ) { titleField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { titleField.setStyle(null); }
        if ( descField.getText().isEmpty() ) { descField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { descField.setStyle(null); }
        if ( typeField.getText().isEmpty() ) { typeField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { typeField.setStyle(null); }
        if ( locField.getText().isEmpty() ) { locField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { locField.setStyle(null); }

        if ( startDateField.getValue() == null ) { startDateField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { startDateField.setStyle(null); }
        if ( (startTimeField.getValue() == null) || ((startTimeField.getValue() != null) && (startTimeField.getValue().toString() == "")) ) { startTimeField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { startTimeField.setStyle(null); }
        if ( endDateField.getValue() == null ) { endDateField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { endDateField.setStyle(null); }
        if ( (endTimeField.getValue() == null) || ((endTimeField.getValue() != null) && (endTimeField.getValue().toString() == "")) ) { endTimeField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { endTimeField.setStyle(null); }

        if ( (userField.getValue() == null) || (userField.getValue().toString() == "") ) { userField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { userField.setStyle(null); }
        if ( (custField.getValue() == null) || (custField.getValue().toString() == "") ) { custField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { custField.setStyle(null); }
        if ( (contactField.getValue() == null) || (contactField.getValue().toString() == "") ) { contactField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { contactField.setStyle(null); }

        if ( (endDateField.getValue() != null) && (startDateField.getValue() != null) && (endDateField.getValue().isBefore(startDateField.getValue())) ) { endDateField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { endDateField.setStyle(null); }
        if ( ((startTimeField.getValue() != null) && (endTimeField.getValue() != null) && (((LocalTime) endTimeField.getValue()).isBefore((LocalTime) startTimeField.getValue()) )) || ((startTimeField.getValue() != null) && (endTimeField.getValue() != null) && (((LocalTime) endTimeField.getValue()).equals((LocalTime) startTimeField.getValue())) )) { endTimeField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { endTimeField.setStyle(null); }

        return incomplete;
    }

    /** Makes all input fields no longer have a red border around them. */
    private void unredApptFields() {
        titleField.setStyle(null);
        descField.setStyle(null);
        typeField.setStyle(null);
        locField.setStyle(null);
        startDateField.setStyle(null);
        startTimeField.setStyle(null);
        endDateField.setStyle(null);
        endTimeField.setStyle(null);
        userField.setStyle(null);
        custField.setStyle(null);
        contactField.setStyle(null);
        userFKIDField.setStyle(null);
        custIDField.setStyle(null);
        contactFKIDField.setStyle(null);
    }

    /** Checks to make sure start time does not fall outside of business hours.
     *
     * @return bad if time falls outside business hours, good if not.
     * */
    private String checkStartTime() {
        if ( startTimeField.getValue() == null ) return "bad";
        LocalTime convertTime = LocalTime.now();
        convertTime = LocalDateTime.of(LocalDate.now(), (LocalTime) startTimeField.getValue()).atZone(ZoneId.of(ZoneId.systemDefault().toString())).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalTime();
        int convertHour = parseInt(convertTime.toString().substring(0,2));
        if ( ( convertHour < 8) || ( convertHour > 21 ) ) {
            startTimeField.setStyle("-fx-border-color: #FF0000;");
            startBadText.setVisible(true);
            fadeSBT.setNode(startBadText);
            fadeSBT.setFromValue(1.0);
            fadeSBT.setToValue(0.0);
            fadeSBT.setCycleCount(1);
            fadeSBT.playFromStart();
            return "bad";
        }
        return "good";
    }

    /** Checks to make sure end time does not fall outside of business hours.
     *
     * @return bad if time falls outside business hours, good if not.
     * */
    private String checkEndTime() {
        if ( endTimeField.getValue() == null ) return "bad";
        LocalTime convertTime = LocalTime.now();
        convertTime = LocalDateTime.of(LocalDate.now(), (LocalTime) endTimeField.getValue()).atZone(ZoneId.of(ZoneId.systemDefault().toString())).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalTime();
        int convertHour = parseInt(convertTime.toString().substring(0,2));
        if ( ( convertHour < 8) || ( convertHour > 21 ) ) {
            endTimeField.setStyle("-fx-border-color: #FF0000;");
            endBadText.setVisible(true);
            fadeEBT.setNode(endBadText);
            fadeEBT.setFromValue(1.0);
            fadeEBT.setToValue(0.0);
            fadeEBT.setCycleCount(1);
            fadeEBT.playFromStart();
            return "bad";
        }
        return "good";
    }

    /** Checks to make sure new appointment doesn't overlap any existing appointments for a given customer.
     *
     * @param start time
     * @param end time
     * @param cust id
     * @param appt id
     * @return total number of overlapping appointments.
     * */
    private int checkOverlap(LocalDateTime start, LocalDateTime end, int cust, int appt) {
        ObservableList<Appt> all = getAllAppts();
        LocalDateTime testStart = null;
        LocalDateTime testEnd = null;
        for ( int i = 0 ; i < all.size() ; i++) {
            if ( all.get(i).getCustFKID() != cust || all.get(i).getApptID() == appt ) continue;
            testStart = all.get(i).getStart();
            testEnd = all.get(i).getEnd();
            if ( start.isEqual(testStart) || (start.isAfter(testStart) && start.isBefore(testEnd)) ) {
                startDateField.setStyle("-fx-border-color: #FF0000;");
                startTimeField.setStyle("-fx-border-color: #FF0000;");
                return all.get(i).getApptID();
            }
            if ( end.isEqual(testEnd) || (end.isAfter(testStart) && end.isBefore(testEnd)) ) {
                endDateField.setStyle("-fx-border-color: #FF0000;");
                endTimeField.setStyle("-fx-border-color: #FF0000;");
                return all.get(i).getApptID();
            }
            if ( start.isBefore(testStart) && end.isAfter(testEnd) ) {
                startDateField.setStyle("-fx-border-color: #FF0000;");
                startTimeField.setStyle("-fx-border-color: #FF0000;");
                endDateField.setStyle("-fx-border-color: #FF0000;");
                endTimeField.setStyle("-fx-border-color: #FF0000;");
                return all.get(i).getApptID();
            }
        }
        return 0;
    }






    /** Populates the customers input fields with whatever row has been clicked on in the table. */
    private void populateCustSelected() {
        Cust custSelected = getCustSelected();
        if (custSelected == null) { return; }
        unredCustFields();
        custIDField.setText(String.valueOf(custSelected.getCustID()));
        custNameField.setText(custSelected.getCustName());
        phoneField.setText(custSelected.getPhone());
        addrField.setText(custSelected.getAddr());
        zipField.setText(custSelected.getZip());
        divIDField.setText(custSelected.getDivID().toString());
        countryIDField.setText(custSelected.getCountryID().toString());
        territoryField.setValue(custSelected.getTerritory());
        countryField.setValue(custSelected.getCountry());
        custUpdate.setText("Update");
        custs.refresh();
    }

    /** When the add/update button is pressed, this either adds a new customer or updates an existing one if custField has an existing ID in it.
     *
     * @throws SQLException
     * */
    public void custAddUpdate() throws SQLException {
        if ( areCustFieldsEmpty() ) return;
        if ( (custIDField.getText() == null) || (custIDField.getText() == "")) {
            setCustSelected(custs.getSelectionModel().getSelectedItem());
            int newID = JDBC.insCust(custNameField.getText(), phoneField.getText(), addrField.getText(), zipField.getText(), parseInt(divIDField.getText()));
            Cust newCust = new Cust(
                    newID,
                    custNameField.getText(),
                    phoneField.getText(),
                    addrField.getText(),
                    zipField.getText(),
                    parseInt(divIDField.getText()),
                    parseInt(countryIDField.getText()),
                    territoryField.getValue().toString(),
                    countryField.getValue().toString()
            );
            addCust(newCust);
            custIDField.setText(String.valueOf(newID));
            setCustSelection(newCust);
            custUpdate.setText("Update");
            custDelete.setVisible(true);
            custs.refresh();

            custAddedText.setVisible(true);
            fadeCAT.setNode(custAddedText);
            fadeCAT.setFromValue(1.0);
            fadeCAT.setToValue(0.0);
            fadeCAT.setCycleCount(1);
            fadeCAT.playFromStart();

        } else {
            for (int j = 0; j < getAllCusts().size(); j++) {
                JDBC.updtCust(custNameField.getText(), phoneField.getText(), addrField.getText(), zipField.getText(), parseInt(divIDField.getText()), parseInt(custIDField.getText()));
                if (parseInt(custIDField.getText()) == getAllCusts().get(j).getCustID()) {
                    updateCust(
                            getAllCusts().get(j),
                            parseInt(custIDField.getText()),
                            custNameField.getText(),
                            phoneField.getText(),
                            addrField.getText(),
                            zipField.getText(),
                            parseInt(divIDField.getText()),
                            parseInt(countryIDField.getText()),
                            territoryField.getValue().toString(),
                            countryField.getValue().toString()
                    );
                    setCustSelection(getAllCusts().get(j));
                    custDelete.setVisible(true);
                    custUpdate.setText("Update");
                    custs.refresh();

                    custUpdatedText.setVisible(true);
                    fadeCUT.setNode(custUpdatedText);
                    fadeCUT.setFromValue(1.0);
                    fadeCUT.setToValue(0.0);
                    fadeCUT.setCycleCount(1);
                    fadeCUT.playFromStart();
                }
            }
        }
    }

    /** Checks if the user has edited any fields after clicking on an existing customer; used to trigger a confirmation if the user clicks on a different record without saving their changes.
     *
     * @return true if edits are detected, false if not.
     * */
    private boolean checkCustChanges() {
        Cust custSelected = getCustSelected();
        if (custSelected == null) { return false; }
        boolean edited = false;
        if ( !custIDField.getText().equals(custSelected.getCustID().toString()) ) { edited = true; }
        if ( !custNameField.getText().equals(custSelected.getCustName()) ) { edited = true; }
        if ( !phoneField.getText().equals(custSelected.getPhone()) ) { edited = true; }
        if ( !addrField.getText().equals(custSelected.getAddr()) ) { edited = true; }
        if ( !zipField.getText().equals(custSelected.getZip()) ) { edited = true; }
        if ( !territoryField.getValue().equals(custSelected.getTerritory()) ) { edited = true; }
        if ( !countryField.getValue().equals(custSelected.getCountry()) ) { edited = true; }
        return edited;
    }

    private static ObservableList<Appt> custAppts = FXCollections.observableArrayList();

    /** Deletes the selected customer, after a confirmation popup.
     *
     * @throws SQLException
     * */
    public void deleteSelectedCust() throws SQLException {
        if ( getCustSelected() == null) { return; }
        custAppts = Cust.checkAppts(parseInt(custIDField.getText()));
        String deleteMessage = "Are you sure you wish to delete the selected customer \n\tAND all their associated appointments? \n\n\tID:\t\tTitle:\n\t----------------------------------------------\n";
        for ( int i = 0 ; i < custAppts.size(); i++ ) {
            deleteMessage = deleteMessage + "\t" + custAppts.get(i).getApptID() + "\t\t" + custAppts.get(i).getTitle() + "\n";
        }
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION, deleteMessage + "\n\n", ButtonType.NO, ButtonType.YES);
        conf.showAndWait();
        if (conf.getResult() == ButtonType.NO) { return; }
        for ( int i = 0 ; i < custAppts.size(); i++ ) {
            JDBC.delAppt(custAppts.get(i).getApptID());
            Appt.removeAppt(custAppts.get(i));
        }
        appts.refresh();
        JDBC.delCust(getCustSelected().getCustID());
        removeCust(getCustSelected());
        custs.getSelectionModel().clearSelection();
        custIDField.setText("");
        custDelete.setVisible(false);
        custUpdate.setText("Add");
        custs.refresh();

        custDeletedText.setVisible(true);
        fadeCDT.setNode(custDeletedText);
        fadeCDT.setFromValue(1.0);
        fadeCDT.setToValue(0.0);
        fadeCDT.setCycleCount(1);
        fadeCDT.playFromStart();
    }

    /** Clears the fields in the editing section of the customers tab, after checking for any changes and ensuring user wishes to discard changes. */
    public void clearCustFields() {
        if (!discardCustChanges()) { return; }
        unredCustFields();
        custIDField.clear();
        custNameField.clear();
        phoneField.clear();
        addrField.clear();
        zipField.clear();
        divIDField.clear();
        countryIDField.clear();
        countryField.getSelectionModel().clearSelection();
        territoryField.getSelectionModel().clearSelection();
        territoryField.setItems(null);
        custs.getSelectionModel().clearSelection();
        custUpdate.setText("Add");
        custDelete.setVisible(false);
        custs.refresh();
    }

    /** Ensures proper row is selected and changes 'Add' button to 'Update' button.
     *
     * @param cust
     * */
    private void setCustSelection(Cust cust) {
        for (int j = 0; j < getAllCusts().size(); j++) {
            if (cust == getAllCusts().get(j)) {
                custs.getSelectionModel().select(j);
                custs.getFocusModel().focus(j);
                unredCustFields();
                custUpdate.setText("Update");
                custs.refresh();
                return;
            }
        }
    }

    /** Dialog box that asks if user is sure they wish to discard their changes.
     *
     * @return true if yes, false if no.
     * */
    private boolean discardCustChanges() {
        boolean changed = checkCustChanges();
        if ( changed == true ) {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to discard your changes?", ButtonType.NO, ButtonType.YES);
            conf.showAndWait();
            if (conf.getResult() == ButtonType.NO) return false;
        }
        return true;
    }

    /** Checks if any fields are empty or incorrect and highlights those fields in red if so. Used before add/update.
     *
     * @return true if any fields are empty, false if none are empty.
     * */
    private boolean areCustFieldsEmpty() {
        boolean incomplete = false;
        if ( custNameField.getText().isEmpty() ) { custNameField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { custNameField.setStyle(null); }
        if ( phoneField.getText().isEmpty() ) { phoneField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { phoneField.setStyle(null); }
        if ( addrField.getText().isEmpty() ) { addrField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { addrField.setStyle(null); }
        if ( zipField.getText().isEmpty() ) { zipField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { zipField.setStyle(null); }
        if ( (territoryField.getValue() == null) || (territoryField.getValue().toString() == "") ) { territoryField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { territoryField.setStyle(null); }
        if ( (countryField.getValue() == null) || (countryField.getValue().toString() == "") ) { countryField.setStyle("-fx-border-color: #FF0000;"); incomplete = true; } else { countryField.setStyle(null); }
        return incomplete;
    }

    /** Makes all input fields no longer have a red border around them. */
    private void unredCustFields() {
        custNameField.setStyle(null);
        phoneField.setStyle(null);
        addrField.setStyle(null);
        zipField.setStyle(null);
        divIDField.setStyle(null);
        countryIDField.setStyle(null);
        territoryField.setStyle(null);
        countryField.setStyle(null);
    }

    /** Changes ID text of country selected, and populates territories combobox for selected country. */
    private void updateTerritoriesField() {
        if ((countryField.getValue() == null) || (countryField.getValue().toString() == "")) return;
        if (countryField.getValue().equals("U.S")) {
            territoryField.setItems(FirstLD.usTerritories);
            countryIDField.setText("1");
        }
        if (countryField.getValue().equals("UK")) {
            territoryField.setItems(FirstLD.ukTerritories);
            countryIDField.setText("2");
        }
        if (countryField.getValue().equals("Canada")) {
            territoryField.setItems(FirstLD.canadaTerritories);
            countryIDField.setText("3");
        }
    }

    /** Changes ID text of territory selected. */
    private void updateTerritoryID() {
        if (( territoryField.getValue() == null ) || ( territoryField.getValue().toString() == "")) return;
        divIDField.setText( String.valueOf(FirstLD.convertToID(territoryField.getValue().toString())));
    }


    /** Resets all search fields in the appointments tab. */
    @FXML
    private void resetApptSearch() {
        titleSearch.setText("");
        descSearch.setText("");
        typeSearch.setText("");
        locSearch.setText("");
        startSearch.setText("");
        endSearch.setText("");
        userSearch.setText("");
        custSearch.setText("");
        contactSearch.setText("");
        appts.setItems(getAllAppts());
        appts.refresh();
    }

    /** Resets all search fields in the customerstab. */
    @FXML
    private void resetCustSearch() {
        custNameSearch.setText("");
        phoneSearch.setText("");
        addrSearch.setText("");
        zipSearch.setText("");
        territorySearch.setText("");
        countrySearch.setText("");
        custs.setItems(getAllCusts());
        custs.refresh();
    }

    /** Searches all appointment titles for text entered in the title search field. */
    @FXML
    private void titleSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getDesc().toLowerCase().contains(titleSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all appointment descriptions for text entered in the description search field. */
    @FXML
    private void descSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getDesc().toLowerCase().contains(descSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all appointment types for text entered in the type search field. */
    @FXML
    private void typeSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getType().toLowerCase().contains(typeSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all appointment locations for text entered in the location search field. */
    @FXML
    private void locSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getLoc().toLowerCase().contains(locSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all appointment start times for text entered in the start time search field. */
    @FXML
    private void startSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getStart().toString().toLowerCase().contains(startSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all appointment end times for text entered in the end time search field. */
    @FXML
    private void endSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getEnd().toString().toLowerCase().contains(endSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all appointment users for text entered in the user search field. */
    @FXML
    private void userSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getUser().toLowerCase().contains(userSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all appointment customers for text entered in the customer search field. */
    @FXML
    private void custSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getCust().toLowerCase().contains(custSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all appointment contacts for text entered in the contact search field. */
    @FXML
    private void contactSearch () {
        FilteredList<Appt> searched = new FilteredList<>(getAllAppts());
        searched.setPredicate(Appt -> Appt.getContact().toLowerCase().contains(contactSearch.getText().toLowerCase()));
        appts.setItems(searched);
    }

    /** Searches all customer names for text entered in the name search field. */
    @FXML
    private void custNameSearch () {
        FilteredList<Cust> searched = new FilteredList<>(getAllCusts());
        searched.setPredicate(Cust -> Cust.getCustName().toLowerCase().contains(custNameSearch.getText().toLowerCase()));
        custs.setItems(searched);
    }

    /** Searches all customer phone numbers for text entered in the phone search field. */
    @FXML
    private void phoneSearch () {
        FilteredList<Cust> searched = new FilteredList<>(getAllCusts());
        searched.setPredicate(Cust -> Cust.getPhone().toLowerCase().contains(phoneSearch.getText().toLowerCase()));
        custs.setItems(searched);
    }

    /** Searches all customer addresses for text entered in the address search field. */
    @FXML
    private void addrSearch () {
        FilteredList<Cust> searched = new FilteredList<>(getAllCusts());
        searched.setPredicate(Cust -> Cust.getAddr().toLowerCase().contains(addrSearch.getText().toLowerCase()));
        custs.setItems(searched);
    }

    /** Searches all customer zip codes for text entered in the zip code search field. */
    @FXML
    private void zipSearch () {
        FilteredList<Cust> searched = new FilteredList<>(getAllCusts());
        searched.setPredicate(Cust -> Cust.getZip().toLowerCase().contains(zipSearch.getText().toLowerCase()));
        custs.setItems(searched);
    }

    /** Searches all customer territories for text entered in the territory search field. */
    @FXML
    private void territorySearch () {
        FilteredList<Cust> searched = new FilteredList<>(getAllCusts());
        searched.setPredicate(Cust -> Cust.getTerritory().toLowerCase().contains(territorySearch.getText().toLowerCase()));
        custs.setItems(searched);
    }

    /** Searches all customer countries for text entered in the country search field. */
    @FXML
    private void countrySearch () {
        FilteredList<Cust> searched = new FilteredList<>(getAllCusts());
        searched.setPredicate(Cust -> Cust.getCountry().toLowerCase().contains(countrySearch.getText().toLowerCase()));
        custs.setItems(searched);
    }

}
