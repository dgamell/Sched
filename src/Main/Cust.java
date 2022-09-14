package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Defines structure of the "Cust" table. */
public class Cust {
    private SimpleIntegerProperty custID;
    public Integer getCustID () { return custID.get(); }
    public void setCustID (int i) { this.custID = new SimpleIntegerProperty(i); }

    private SimpleStringProperty custName;
    public String getCustName () { return custName.get(); }
    public void setCustName (String str) { this.custName = new SimpleStringProperty(str); }

    private SimpleStringProperty phone;
    public String getPhone () { return phone.get(); }
    public void setPhone (String str) { this.phone = new SimpleStringProperty(str); }

    private SimpleStringProperty addr;
    public String getAddr () { return addr.get(); }
    public void setAddr (String str) { this.addr = new SimpleStringProperty(str); }

    private SimpleStringProperty zip;
    public String getZip () { return zip.get(); }
    public void setZip (String str) { this.zip = new SimpleStringProperty(str); }

    private SimpleIntegerProperty divID;
    public Integer getDivID () { return divID.get(); }
    public void setDivID (int i) { this.divID = new SimpleIntegerProperty(i); }

    private SimpleIntegerProperty countryID;
    public Integer getCountryID () { return countryID.get(); }
    public void setCountryID (int i) { this.countryID = new SimpleIntegerProperty(i); }

    private SimpleStringProperty territory;
    public String getTerritory () { return territory.get(); }
    public void setTerritory (String str) { this.territory = new SimpleStringProperty(str); }

    private SimpleStringProperty country;
    public String getCountry () { return country.get(); }
    public void setCountry (String str) { this.country = new SimpleStringProperty(str); }

    /** Cust constructor. */
    public Cust(Integer cust, String custName, String phone, String addr, String zip, Integer divID, Integer countryID, String territory, String country) {
        this.custID = new SimpleIntegerProperty(cust);
        this.custName = new SimpleStringProperty(custName);
        this.phone = new SimpleStringProperty(phone);
        this.addr = new SimpleStringProperty(addr);
        this.zip = new SimpleStringProperty(zip);
        this.divID = new SimpleIntegerProperty(divID);
        this.countryID = new SimpleIntegerProperty(countryID);
        this.territory = new SimpleStringProperty(territory);
        this.country = new SimpleStringProperty(country);
    }

    public static ObservableList<Cust> allCusts = FXCollections.observableArrayList();
    public static ObservableList<String> allCustNames = FXCollections.observableArrayList();

    /** Populates the allCustNames table with customer names (strings only). Used a lambda here because of the efficiency of forEach. */
    public static void populateCustNames() { allCusts.forEach(Cust -> allCustNames.add(Cust.getCustName())); }

    private static Cust custSelected;

    /** Stores which row is selected in the appointment table view.
     *
     * @param cust
     * */
    public static void setCustSelected(Cust cust) { custSelected = cust; }

    /** Returns which row is selected in the customers table view.
     *
     * @return custSelected
     * */
    public static Cust getCustSelected() { return custSelected; }

    /** Adds a customer to the list.
     *
     * @param cust
     * */
    public static void addCust(Cust cust) { allCusts.add(cust); }

    /** Updates the attributes of a customer object.
     *
     * @param cust object
     * @param id int
     * @param custName
     * @param phone
     * @param addr
     * @param zip
     * @param divID int
     * @param countryID int
     * @param territory
     * @param country
     * */
    public static void updateCust(Cust cust, int id, String custName, String phone, String addr, String zip, int divID, int countryID, String territory, String country) {
        cust.setCustID(id);
        cust.setCustName(custName);
        cust.setPhone(phone);
        cust.setAddr(addr);
        cust.setZip(zip);
        cust.setDivID(divID);
        cust.setCountryID(countryID);
        cust.setTerritory(territory);
        cust.setCountry(country);
    }

    /** Removes a customer from the list.
     *
     * @param cust
     * @return true if successful, false if unsuccessful.
     * */
    public static boolean removeCust(Cust cust) {
        for (int i = 0; i < allCusts.size(); i++) {
            if ( allCusts.get(i) == cust ) {
                allCusts.remove(i);
                return true;
            }
        }
        return false;
    }

    /** Returns the entire list of customers from the list.
     *
     * @return allCusts
     * */
    public static ObservableList<Cust> getAllCusts() {
        return allCusts;
    }

    /** Removes all customers from the list. */
    public static void clearAllCusts() {
        allCusts.clear();
    }

    /** Converts a customer's name to their ID.
     *
     * @param name
     * @return custID if successful, otherwise 0.
     * */
    public static int convertToID (String name) {
        for (int i = 0; i <  allCusts.size(); i++) {
            if (  allCusts.get(i).getCustName() == name ) {
                return  allCusts.get(i).getCustID();
            }
        }
        return 0;
    }

    /** Converts a customer's ID to their name.
     *
     * @param id
     * @return custName if successful, otherwise "".
     * */
    public static String convertToName (int id) {
        for (int i = 0; i <  allCusts.size(); i++) {
            if (  allCusts.get(i).getCustID() == id ) {
                return  allCusts.get(i).getCustName();
            }
        }
        return "";
    }

    /** Checks to see if the customer has appointments and creates a new table full of them.
     *
     * @param custID int
     * @return table of appointments this customer has.
     * */
    public static ObservableList<Appt> checkAppts(int custID) {
        ObservableList<Appt> getAppts = Appt.getAllAppts();
        ObservableList<Appt> custAppts = FXCollections.observableArrayList();
        for ( int i = 0; i < getAppts.size() ; i++ ) {
            if ( getAppts.get(i).getCustFKID() == custID ) {
                custAppts.add(getAppts.get(i));
            }
        }
        return custAppts;
    }
}