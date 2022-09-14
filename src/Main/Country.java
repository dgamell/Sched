package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Defines structure of the "Country" table. */
public class Country {
    private SimpleIntegerProperty ID;
    public Integer getID () { return ID.get(); }
    public void setID (int i) { this.ID = new SimpleIntegerProperty(i); }

    private SimpleStringProperty name;
    public String getName () { return name.get(); }
    public void setName (String str) { this.name = new SimpleStringProperty(str); }

    /** Country constructor. */
    public Country(Integer ID, String Name) {
        this.ID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(Name);
    }

    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    public static ObservableList<String> allCountryNames = FXCollections.observableArrayList();

    /** Populates the allCountryNames table with country names (string only). Used a lambda here because of the efficiency of forEach. */
    public static void populateCountryNames() { allCountries.forEach(Country -> allCountryNames.add(Country.getName())); }

    /** Adds a country to the list.
     *
     * @param country
     * */
    public static void addCountry(Country country) {
        allCountries.add(country);
    }

    /** Converts a country ID to country name.
     *
     * @param id int
     * @return country name if successful, otherwise "".
     * */
    public static String convertToName (int id) {
        for (int i = 0; i <  allCountries.size(); i++) {
            if (  allCountries.get(i).getID() == id ) {
                return  allCountries.get(i).getName();
            }
        }
        return "";
    }

}