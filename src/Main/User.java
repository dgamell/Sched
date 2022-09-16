package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Defines structure of the "User" table. */
public class User extends Data {

    private SimpleStringProperty password;
    public String getPassword () { return password.get(); }

    /** User constructor. */
    public User(Integer ID, String name, String password) {
        this.ID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
    }

    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    public static ObservableList<String> allNames = FXCollections.observableArrayList();

    /** Populates the allUsers table with all usernames (strings only). Used a lambda here because of the efficiency of forEach. */
    public static void populateNames() { allUsers.forEach(User -> allNames.add(User.getName())); }

    public static ObservableList<String> getAllNames() { return allNames; }

    /** Adds a user to the list.
     *
     * @param user
     * */
    public static void add(User user) { allUsers.add(user); }

    /** Converts a user name to user ID.
     *
     * @param name
     * @return ID if successful, otherwise 0.
     * */
    public static int convertToID (String name) {
        for (int i = 0; i <  allUsers.size(); i++) {
            if (  allUsers.get(i).getName().equals(name) ) {
                return  allUsers.get(i).getID();
            }
        }
        return 0;
    }

    /** Converts a user ID to user name.
     *
     * @param id
     * @return name if successful, otherwise "".
     * */
    public static String convertToName (int id) {
        for (int i = 0; i <  allUsers.size(); i++) {
            if (  allUsers.get(i).getID() == id ) {
                return  allUsers.get(i).getName();
            }
        }
        return "";
    }

    /** Converts a user name to their corresponding password.
     *
     * @param name
     * @return password if successful, otherwise "".
     * */
    public static String convertToPassword (String name) {
        for (int i = 0; i <  allUsers.size(); i++) {
            if (  allUsers.get(i).getName().equals(name) ) {
                return  allUsers.get(i).getPassword();
            }
        }
        return "";
    }
}