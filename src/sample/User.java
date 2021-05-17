package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class User
{
    private String username,passwordHash, firstname, lastname;
    private Image profilepic;
    private Calendar calendar;
    private PasswordManagement pm;
    private Secretary secretary;
    private List<Note> notes;

    public User(String username, String passwordHash, String firstname, String lastname, Image profilepic,
                Calendar calendar, Secretary secretary)
    {
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilepic = profilepic;
        this.calendar = calendar;
        this.pm = new PasswordManagement();
        this.secretary = secretary;
        this.notes = new ArrayList<>();
    }
    
    public void addNote(Note iNote){
        if (!this.notes.contains(iNote)){
            this.notes.add(iNote);
        }
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
        return reString.toString();
    }
}
