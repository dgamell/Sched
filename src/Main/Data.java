package Main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Data {
    public SimpleIntegerProperty ID;
    public Integer getID () { return ID.get(); }
    public void setID (int i) { this.ID = new SimpleIntegerProperty(i); }

    public SimpleStringProperty name;
    public String getName () { return name.get(); }
    public void setName (String str) { this.name = new SimpleStringProperty(str); }

    /** Data default constructor. */
    public Data() { }

}