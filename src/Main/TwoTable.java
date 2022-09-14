package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static Main.Appt.getAllAppts;
import static Main.Cust.allCusts;

/** Defines structure of the "TwoTable" table used in three areas on the right side of the reports tab. */
public class TwoTable {
    private SimpleStringProperty name;
    public String getName () { return name.get(); }
    public void setName (String str) { this.name = new SimpleStringProperty(str); }

    private SimpleIntegerProperty total;
    public Integer getTotal() { return total.get(); }
    public void setTotal(int i) { this.total = new SimpleIntegerProperty(i); }

    /** TwoTable constructor. */
    public TwoTable(String name, int total) {
        this.name = new SimpleStringProperty(name);
        this.total = new SimpleIntegerProperty(total);
    }


    private static ObservableList<TwoTable> allCustomersTT = FXCollections.observableArrayList();

    /** Table of all TwoTables to populate the customers table view.
     *
     * @return allCustomersTT
     */
    public static ObservableList<TwoTable> getAllCustomersTT() {
        return allCustomersTT;
    }

    /** Create a TwoTable of all customers that have appointments, along with their count. */
    public static void populateAllCustomersTT() {
        allCustomersTT.clear();
        ObservableList<Appt> all = getAllAppts();
        for (int j = 1; j < allCusts.size(); j++) {
            TwoTable customer = new TwoTable("", 0);
            customer.setName(allCusts.get(j).getCustName());
            int count = 0;
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getCust().equals(customer.getName())) {
                    count++;
                }
            }
            customer.setTotal(count);
            allCustomersTT.add(customer);
        }
    }
}