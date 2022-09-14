package Main;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

/** Defines structure of the "Appt" table. */
public class Appt {
    private SimpleIntegerProperty apptID;
    public Integer getApptID () { return apptID.get(); }
    public void setApptID (int i) { this.apptID = new SimpleIntegerProperty(i); }

    private SimpleStringProperty title;
    public String getTitle () { return title.get(); }
    public void setTitle (String str) { this.title = new SimpleStringProperty(str); }

    private SimpleStringProperty desc;
    public String getDesc () { return desc.get(); }
    public void setDesc (String str) { this.desc = new SimpleStringProperty(str); }

    private SimpleStringProperty type;
    public String getType () { return type.get(); }
    public void setType (String str) { this.type = new SimpleStringProperty(str); }

    private SimpleStringProperty loc;
    public String getLoc () { return loc.get(); }
    public void setLoc (String str) { this.loc = new SimpleStringProperty(str); }

    private LocalDateTime start;
    public LocalDateTime getStart () { return start; }
    public void setStart (LocalDateTime ldt) { this.start = ldt; }

    private LocalDateTime end;
    public LocalDateTime getEnd () { return end; }
    public void setEnd (LocalDateTime ldt) { this.end = ldt; }

    private SimpleStringProperty user;
    public String getUser () { return user.get(); }
    public void setUser (String str) { this.user = new SimpleStringProperty(str); }

    private SimpleIntegerProperty userFKID;
    public Integer getUserFKID () { return userFKID.get(); }
    public void setUserFKID (int i) { this.userFKID = new SimpleIntegerProperty(i); }

    private SimpleStringProperty cust;
    public String getCust () { return cust.get(); }
    public void setCust (String str) { this.cust = new SimpleStringProperty(str); }

    private SimpleIntegerProperty custFKID;
    public Integer getCustFKID () { return custFKID.get(); }
    public void setCustFKID (int i) { this.custFKID = new SimpleIntegerProperty(i); }

    private SimpleStringProperty contact;
    public String getContact () { return contact.get(); }
    public void setContact (String str) { this.contact = new SimpleStringProperty(str); }

    private SimpleIntegerProperty contactFKID;
    public Integer getContactFKID () { return contactFKID.get(); }
    public void setContactFKID (int i) { this.contactFKID = new SimpleIntegerProperty(i); }

    /** Appt constructor. */
    public Appt(Integer apptID, String title, String desc, String type, String loc, LocalDateTime start, LocalDateTime end, String user, Integer userFKID, String cust, Integer custFKID, String contact, Integer contactFKID) {
        this.apptID = new SimpleIntegerProperty(apptID);
        this.title = new SimpleStringProperty(title);
        this.desc = new SimpleStringProperty(desc);
        this.type = new SimpleStringProperty(type);
        this.loc = new SimpleStringProperty(loc);
        this.start = start;
        this.end = end;
        this.user = new SimpleStringProperty(user);
        this.userFKID = new SimpleIntegerProperty(userFKID);
        this.cust = new SimpleStringProperty(cust);
        this.custFKID = new SimpleIntegerProperty(custFKID);
        this.contact = new SimpleStringProperty(contact);
        this.contactFKID = new SimpleIntegerProperty(contactFKID);
    }

    private static ObservableList<Appt> allAppts = FXCollections.observableArrayList();
    private static ObservableList<Appt> monthAppts = FXCollections.observableArrayList();
    private static ObservableList<Appt> weekAppts = FXCollections.observableArrayList();

    /** Returns the entire list of appointments from the list.
     *
     * @return allAppts
     * */
    public static ObservableList<Appt> getAllAppts() {
        return allAppts;
    }

    /** Returns the list of appointments this month.
     *
     * @return allAppts
     * */
    public static ObservableList<Appt> getMonthAppts() {
        return monthAppts;
    }

    /** Returns the list of appointments this week.
     *
     * @return allAppts
     * */
    public static ObservableList<Appt> getWeekAppts() {
        return weekAppts;
    }

    /** Populates the appts table with only appointments for this month. */
    public static void populateMonthAppts() {
        clearMonthAppts();
        ObservableList<Appt> all = getAllAppts();
        LocalDateTime before = LocalDateTime.now().minusMonths(1);
        LocalDateTime after = LocalDateTime.now().plusMonths(1);

        for ( int i = 0 ; i < all.size() ; i++ ) {
            if (all.get(i).getStart().isAfter(before) && all.get(i).getStart().isBefore(after) ) {
                monthAppts.add(all.get(i));
            }
        }
    }

    /** Populates the appts table with only appointments for this week. */
    public static void populateWeekAppts() {
        clearWeekAppts();
        ObservableList<Appt> all = getAllAppts();
        LocalDateTime before = LocalDateTime.now().minusWeeks(1);
        LocalDateTime after = LocalDateTime.now().plusWeeks(1);

        for ( int i = 0 ; i < all.size() ; i++ ) {
            if (all.get(i).getStart().isAfter(before) && all.get(i).getStart().isBefore(after) ) {
                weekAppts.add(all.get(i));
            }
        }
    }

    private static Appt apptSelected;

    /** Stores which row is selected in the appointment table view.
     *
     * @param appt
     * */
    public static void setApptSelected(Appt appt) {
        apptSelected = appt;
    }

    /** Returns which row is selected in the appointment table view.
     *
     * @return appSelected;
     * */
    public static Appt getApptSelected() { return apptSelected; }

    /** Clears the selection in the appointments table view. */
    public static void clearApptSelected() { apptSelected = null; }

    /** Adds an appointment to the list.
     *
     * @param appt
     * */
    public static void addAppt(Appt appt) {
        allAppts.add(appt);
    }

    /** Updates the attributes of an appointment object.
     *
     * @param appt object
     * @param id int
     * @param title
     * @param desc
     * @param type
     * @param loc
     * @param start LocalDateTime
     * @param end LocalDateTime
     * @param user
     * @param userFKID int
     * @param cust
     * @param custFKID int
     * @param contact
     * @param contactFKID int
     * */
    public static void updateAppt(Appt appt, int id, String title, String desc, String type, String loc, LocalDateTime start, LocalDateTime end, String user, int userFKID, String cust, int custFKID, String contact, int contactFKID) {
        appt.setApptID(id);
        appt.setTitle(title);
        appt.setDesc(desc);
        appt.setType(type);
        appt.setLoc(loc);
        appt.setStart(start);
        appt.setEnd(end);
        appt.setUser(user);
        appt.setUserFKID(userFKID);
        appt.setCust(cust);
        appt.setCustFKID(custFKID);
        appt.setContact(contact);
        appt.setContactFKID(contactFKID);
    }

    /** Removes an appointment from the list.
     *
     * @param appt
     * @return true if successful, false if unsuccessful.
     * */
    public static boolean removeAppt(Appt appt) {
        for (int i = 0; i < allAppts.size(); i++) {
            if ( allAppts.get(i) == appt ) {
                allAppts.remove(i);
                return true;
            }
        }
        return false;
    }

    /** Removes all appointments from the list. */
    public static void clearAllAppts() {
        allAppts.clear();
    }

    /** Removes all appointments from the month list. */
    public static void clearMonthAppts() {
        monthAppts.clear();
    }

    /** Removes all appointments from the week list. */
    public static void clearWeekAppts() {
        weekAppts.clear();
    }

}
