package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Defines structure of the "FirstLD" table. */
public class FirstLD extends Data {

    private SimpleIntegerProperty countryID;
    public Integer getCountryID () { return countryID.get(); }

    /** FirstLD constructor. */
    public FirstLD(Integer ID, String name, Integer countryID) {
        this.ID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.countryID = new SimpleIntegerProperty(countryID);
    }

    private static ObservableList<FirstLD> allFirstLDs = FXCollections.observableArrayList();

    /** Returns table of all FirstLDs.
     *
     * @return allFirstLDs
     * */
    public static ObservableList<FirstLD> getAllFirstLDs() {
        return allFirstLDs;
    }

    public static ObservableList<String> allFirstLDNames = FXCollections.observableArrayList();

    /** Populates the allFirstLDs table with FirstLD territories (strings only). Used a lambda here because of the efficiency of forEach. */
    public static void populateFirstLDNames() { allFirstLDs.forEach(FirstLD -> allFirstLDNames.add(FirstLD.getName())); }

    /** Adds a first level division to the list.
     *
     * @param firstLD
     * */
    public static void addFirstLD(FirstLD firstLD) {
        allFirstLDs.add(firstLD);
    }

    /** Converts a territory name to a div ID.
     *
     * @param name
     * @return id if successful, otherwise 0.
     * */
    public static int convertToID (String name) {
        for (int i = 0; i < allFirstLDs.size(); i++) {
            if ( allFirstLDs.get(i).getName() == name ) {
                return allFirstLDs.get(i).getID();
            }
        }
        return 0;
    }

    /** Converts a div ID to a territory name.
     *
     * @param id int
     * @return associated territory if successful, otherwise "".
     * */
    public static String convertToName (int id) {
        for (int i = 0; i < allFirstLDs.size(); i++) {
            if ( allFirstLDs.get(i).getID() == id ) {
                return allFirstLDs.get(i).getName();
            }
        }
        return "";
    }

    /** Converts a div ID to a country ID.
     *
     * @param id int
     * @return country ID if successful, otherwise 0.
     * */
    public static int convertToCountryID (int id) {
        for (int i = 0; i < allFirstLDs.size(); i++) {
            if ( allFirstLDs.get(i).getID() == id ) {
                return allFirstLDs.get(i).getCountryID();
            }
        }
        return 0;
    }

    public static ObservableList<String> usTerritories = FXCollections.observableArrayList();
    public static ObservableList<String> ukTerritories = FXCollections.observableArrayList();
    public static ObservableList<String> canadaTerritories = FXCollections.observableArrayList();

    /** Populates the territories combobox with all US territories, only. */
    public static void populateUSTerritories() {
        ObservableList<FirstLD> all = getAllFirstLDs();
        for ( int i = 0 ; i < all.size() ; i++ ) {
            if (all.get(i).countryID.getValue() == 1) {
                usTerritories.add(all.get(i).getName());
            }
        }
    }

    /** Populates the territories combobox with all UK territories, only. */
    public static void populateUKTerritories() {
        ObservableList<FirstLD> all = getAllFirstLDs();
        for ( int i = 0 ; i < all.size() ; i++ ) {
            if (all.get(i).countryID.getValue() == 2) {
                ukTerritories.add(all.get(i).getName());
            }
        }

    }

    /** Populates the territories combobox with all Canada territories, only. */
    public static void populateCanadaTerritories() {
        ObservableList<FirstLD> all = getAllFirstLDs();
        for ( int i = 0 ; i < all.size() ; i++ ) {
            if (all.get(i).countryID.getValue() == 3) {
                canadaTerritories.add(all.get(i).getName());
            }
        }
    }
}