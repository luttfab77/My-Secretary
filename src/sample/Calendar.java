package sample;

import java.util.ArrayList;
import java.util.List;

public class Calendar
{
    private List<Appointment> appointments;

    public Calendar()
    {
        appointments = new ArrayList<>();
    }
    
    public void addAppointment(Appointment iAppointment){
        if (!this.appointments.contains(iAppointment)){
            this.appointments.add(iAppointment);
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
}
