package at.mysecretary.model;

import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable
{
    private String username,passwordHash, firstname, lastname;
    private Image profilepic;
    private Calendar calendar;
    private PasswordManagement pm;
    private Secretary secretary;
    private List<Note> notes;

    public String getUsername() { return username; }

    /**
     * Sets the username from created user as username.
     * Also sets the username for passwordmanager and calendar.
     *    This is needed to find the calendar and passwordmanager.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
        this.calendar.setUsername(username);
        this.pm.setUsername(username);
    }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public Image getProfilepic() { return profilepic; }
    public void setProfilepic(Image profilepic) { this.profilepic = profilepic; }
    public Calendar getCalendar() { return calendar; }
    public void setCalendar(Calendar calendar) { this.calendar = calendar; }
    public PasswordManagement getPm() { return pm; }
    public void setPm(PasswordManagement pm) { this.pm = pm; }
    public Secretary getSecretary() { return secretary; }
    public void setSecretary(Secretary secretary) { this.secretary = secretary; }
    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }

    public User(String username, String passwordHash, String firstname, String lastname, Image profilepic)
    {
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilepic = profilepic;
        this.calendar = new Calendar();
        this.calendar.setUsername(username);
        this.pm = new PasswordManagement();
        this.pm.setUsername(username);
        this.secretary = new Secretary(null);
        this.notes = new ArrayList<>();
    }

    public User(){
        this.username = "";
        this.passwordHash = "";
        this.firstname = "";
        this.lastname = "";
        this.profilepic = null;
        this.calendar = new Calendar();
        this.pm = new PasswordManagement();
        this.secretary = new Secretary();
        this.notes = new ArrayList<>();
    }

    /**
     * Adds a Note, if it doesn't exist.
     * @param iNote
     */
    public void addNote(Note iNote){
        iNote.setUsername(getUsername());
        if (!this.notes.contains(iNote)){
            this.notes.add(iNote);
        }
    }

    /**
     * Removes a Note, if it exists.
     * @param iNote
     */
    public void removeNote(Note iNote){
        iNote.setUsername(getUsername());
        if (this.notes.contains(iNote)){
            notes.remove(iNote);
            SerializationFactory.getInstance().remove(iNote);
        }
    }

    /**
     * Saves a User into the objects List from SerializationFactory.
     */
    public void save() {
        SerializationFactory.getInstance().save(this);
    }

    /**
     * Deletes a User from the objects List from SerializationFactory.
     * ATTENTION! Do not use this Method carelessly.
     */
    public void remove() {
        SerializationFactory.getInstance().remove(this);
    }

    /**
     * You can get a User as return value, if you have the username and the password.
     * This uses the Method from SerilisationFactory, called selectUserByUsernamePassword().
     * @param username
     * @param password
     * @return
     */
    public static User selectByUsernamePassword(String username,String password) {
        return SerializationFactory.getInstance().selectUserByUsernamePassword(username,password);
    }


    /**
     * Only for test purposes.
     * @return string
     */
    @Override
    public String toString()
    {
        StringBuilder reString = new StringBuilder("\n----------USER----------");

        reString.append("\nUsername:\t\t").append(this.username);
        reString.append("\nPasswordHash:\t").append(this.passwordHash);
        reString.append("\nFirstName:\t\t").append(this.firstname);
        reString.append("\nLastName:\t\t").append(this.lastname);
        reString.append("\n\n").append(this.calendar.toString());
        reString.append("\n\n").append(this.secretary.toString());
        reString.append("\n\n\n------Notes------");
        for (Note iNote : this.notes){
            reString.append(iNote.toString());
        }
        reString.append("\n\n").append(this.pm.toString());
        return reString.toString();
    }


    /**
     * Method to check, if the User already exists or not.
     * Helps the Method contains() out, to find the right object.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User iUser = (User) o;

        return getUsername().equals(iUser.getUsername());
    }
}
