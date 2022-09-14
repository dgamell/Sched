package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.Month;
import java.util.ArrayList;
import static Main.Appt.getAllAppts;

/** Defines structure of the "ThreeTable" table used in three areas on the right side of the reports tab. */
public class ThreeTable {
    private SimpleStringProperty name;
    public String getName () { return name.get(); }
    public void setName (String str) { this.name = new SimpleStringProperty(str); }

    private SimpleStringProperty type;
    public String getType () { return type.get(); }
    public void setType (String str) { this.type = new SimpleStringProperty(str); }

    private SimpleIntegerProperty total;
    public Integer getTotal() { return total.get(); }
    public void setTotal(int i) { this.total = new SimpleIntegerProperty(i); }

    /**
     * ThreeTable constructor.
     */
    public ThreeTable(String name, String type, int total) {
        this.name = new SimpleStringProperty(name);
        this.name = new SimpleStringProperty(type);
        this.total = new SimpleIntegerProperty(total);
    }


    private static ObservableList<ThreeTable> threeTables = FXCollections.observableArrayList();

    /**
     * Table of all ThreeTables to populate the month table view.
     *
     * @return allMonthsTT
     */
    public static ObservableList<ThreeTable> getThreeTables() {
        return threeTables;
    }

    /**
     * Create a ThreeTable of month-type combinations in appointments, along with their count.
     */
    public static void populateThreeTables() {
        threeTables.clear();
        ObservableList<Appt> all = getAllAppts();
        ArrayList<String> types = new ArrayList<>();
        String type = "";
        for (int i = 0; i < all.size(); i++) {
            type = all.get(i).getType();
            boolean contains = false;
            for (int j = 0; j < types.size(); j++) {
                if (types.get(j).equals(type)) contains = true;
            }
            if (!contains) {
                types.add(type);
            }
        }
        for (int j = 1; j < 13; j++) {
            String month = Month.of(j).toString();
            for (int k = 0; k < types.size(); k++) {
                int count = 0;
                type = types.get(k);
                ThreeTable tt = new ThreeTable("", "", 0);
                tt.setName(month);
                tt.setType(type);
                for (int i = 0; i < all.size(); i++) {
                    Appt appt = all.get(i);
                    if (appt.getStart().getMonth() == Month.of(j) && (appt.getType().equals(type))) {
                        count++;
                    }
                }
                if (count > 0) {
                    tt.setTotal(count);
                    threeTables.add(tt);
                }
            }
        }
    }
}