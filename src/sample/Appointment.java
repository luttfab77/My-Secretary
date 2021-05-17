package sample;

import java.time.LocalDate;

public class Appointment
{
    private LocalDate startdate, enddate;
    private String title, description;

    public Appointment(LocalDate startdate, LocalDate enddate, String title, String description)
    {
        this.startdate = startdate;
        this.enddate = enddate;
        this.title = title;
        this.description = description;
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
}
