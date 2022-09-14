package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Defines structure of the "User" table. */
public class User {
    private SimpleIntegerProperty userID;
    public Integer getUserID () { return userID.get(); }
    public void setUserID (int i) { this.userID = new SimpleIntegerProperty(i); }

    private SimpleStringProperty userName;
    public String getUserName () { return userName.get(); }
    public void setUserName (String str) { this.userName = new SimpleStringProperty(str); }

    private SimpleStringProperty password;
    public String getPassword () { return password.get(); }
    public void setPassword (String str) { this.password = new SimpleStringProperty(str); }

    /** User constructor. */
    public User(Integer userID, String userName, String password) {
        this.userID = new SimpleIntegerProperty(userID);
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
    }

    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    public static ObservableList<String> allUserNames = FXCollections.observableArrayList();

    /** Populates the allUsers table with all usernames (strings only). Used a lambda here because of the efficiency of forEach. */
    public static void populateUserNames() {
        allUsers.forEach(User -> allUserNames.add(User.getUserName()));
    }

    /** Adds a user to the list.
     *
     * @param user
     * */
    public static void addUser(User user) { allUsers.add(user); }

    /** Converts a user name to user ID.
     *
     * @param name
     * @return ID if successful, otherwise 0.
     * */
    public static int convertToID (String name) {
        for (int i = 0; i <  allUsers.size(); i++) {
            if (  allUsers.get(i).getUserName().equals(name) ) {
                return  allUsers.get(i).getUserID();
            }
        }
        return 0;
    }

    /** Converts a user ID to user name.
     *
     * @param id
     * @return userName if successful, otherwise "".
     * */
    public static String convertToName (int id) {
        for (int i = 0; i <  allUsers.size(); i++) {
            if (  allUsers.get(i).getUserID() == id ) {
                return  allUsers.get(i).getUserName();
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
            if (  allUsers.get(i).getUserName().equals(name) ) {
                return  allUsers.get(i).getPassword();
            }
        }
        return "";
    }
}