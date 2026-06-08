package clinicmanagementsystem.service;

import clinicmanagementsystem.model.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private final List<Appointment> appointments = new ArrayList<Appointment>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<Appointment>(appointments);
    }

    public Appointment findById(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }
}
