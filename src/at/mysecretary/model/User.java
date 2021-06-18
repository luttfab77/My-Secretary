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
    
    public void addNote(Note iNote){
        iNote.setUsername(getUsername());
        if (!this.notes.contains(iNote)){
            this.notes.add(iNote);
        }
    }

    public void removeNote(Note iNote){
        iNote.setUsername(getUsername());
        if (this.notes.contains(iNote)){
            notes.remove(iNote);
            SerializationFactory.getInstance().remove(iNote);
        }
    }


    public void save() {
        SerializationFactory.getInstance().save(this);
    }

    public static User selectByUsernamePassword(String username,String password) {
        return SerializationFactory.getInstance().selectUserByUsernamePassword(username,password);
    }


    //Für Testzwecke.
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
