package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Defines structure of the "Contact" table. */
public class Contact extends Data {

    /** Contact constructor. */
    public Contact(Integer ID, String name) {
        this.ID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
    }

    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    public static ObservableList<String> allContactNames = FXCollections.observableArrayList();


    /** Populates the allContacts table with all contact names (strings only). Used a lambda here because of the efficiency of forEach. */
    public static void populateContactNames() { allContacts.forEach(Contact -> allContactNames.add(Contact.getName())); }

    /** Adds a contact to the list.
     *
     * @param contact
     * */
    public static void addContact(Contact contact) {
        allContacts.add(contact);
    }

    /** Converts a contact name into their contact ID.
     *
     * @param name
     * @return ID if successful, otherwise 0.
     * */
    public static int convertToID (String name) {
        for (int i = 0; i <   allContacts.size(); i++) {
            if (   allContacts.get(i).getName() == name ) {
                return   allContacts.get(i).getID();
            }
        }
        return 0;
    }

    /** Converts a contact's ID into their name.
     *
     * @param id int
     * @return contact name if successful, otherwise "".
     * */
    public static String convertToName (int id) {
        for (int i = 0; i <   allContacts.size(); i++) {
            if (   allContacts.get(i).getID() == id ) {
                return   allContacts.get(i).getName();
            }
        }
        return "";
    }

    public static ObservableList<Appt> contactAppts = FXCollections.observableArrayList();

    /** Populates the appointments-by-contact table in the reports tab.
     *
     * @param id
     * @return table of all appointments by this contact ID.
     * */
    public static ObservableList<Appt> populateContactAppts(int id) {
        contactAppts.clear();
        ObservableList<Appt> all = Appt.getAllAppts();
        for ( int i = 0 ; i < all.size() ; i++ ) {
            if (all.get(i).getContactFKID() == id) {
                contactAppts.add(all.get(i));
            }
        }
        return contactAppts;
    }
}