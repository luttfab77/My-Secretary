package sample;

import java.io.Serializable;
import java.time.LocalDate;

public class Appointment implements Serializable
{
    private LocalDate startdate, enddate;
    private String title, description;
    private String username;

    public LocalDate getStartdate() { return startdate; }
    public void setStartdate(LocalDate startdate) { this.startdate = startdate; }
    public LocalDate getEnddate() { return enddate; }
    public void setEnddate(LocalDate enddate) { this.enddate = enddate; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Appointment(LocalDate startdate, LocalDate enddate, String title, String description)
    {
        this.startdate = startdate;
        this.enddate = enddate;
        this.title = title;
        this.description = description;
    }

    public Appointment(){
        this.startdate = LocalDate.now();
        this.enddate = LocalDate.now();
        this.title = "";
        this.description = "";
    }
    

    //Für Testzwecke.
    @Override
    public String toString()
    {
        return "\n\n-Appointment-" +
                "\nStartdate:\t\t" + this.startdate.toString() +
                "\nEnddate:\t\t" + this.enddate.toString() +
                "\nTitle:\t\t\t" + this.title +
                "\nDescription:\t" + this.description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Appointment iAppointment = (Appointment) o;

        return getUsername().equals(iAppointment.getUsername()) && getTitle().equals(iAppointment.getTitle()) &&
                getDescription().equals(iAppointment.getDescription()) && (
                        getStartdate().equals(iAppointment.getStartdate()) ||
                        getEnddate().equals(iAppointment.getEnddate())
                );
    }
}
