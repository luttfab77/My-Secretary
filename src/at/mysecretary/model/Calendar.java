package at.mysecretary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Calendar implements Serializable
{
    private List<Appointment> appointments;
    private String username;

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Calendar()
    {
        this.appointments = new ArrayList<>();
        this.username = "";
    }


    public void addAppointment(Appointment iAppointment){
        iAppointment.setUsername(getUsername());
        if (!this.appointments.contains(iAppointment)){
            this.appointments.add(iAppointment);
        }
    }

    public void removeAppointment(Appointment iAppointment){
        iAppointment.setUsername(getUsername());
        if (this.appointments.contains(iAppointment)){
            this.appointments.remove(iAppointment);
            SerializationFactory.getInstance().remove(iAppointment);
        }
    }


    //Für Testzwecke.
    @Override
    public String toString()
    {
        StringBuilder reString = new StringBuilder("\n------Calendar------");

        for (Appointment iAppointment : this.appointments){
            reString.append(iAppointment.toString());
        }

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

        Calendar iCalendar = (Calendar) o;

        return getUsername().equals(iCalendar.getUsername());
    }
}
