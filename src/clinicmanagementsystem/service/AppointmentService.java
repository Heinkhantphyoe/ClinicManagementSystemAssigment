package clinicmanagementsystem.service;

import clinicmanagementsystem.model.Appointment;
import clinicmanagementsystem.util.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File-backed access to appointments.txt.
 * Format: appointmentId,patientId,doctorId,appointmentDate,appointmentTime,status,notes
 */
public class AppointmentService {
    private static final String FILE_PATH = "src/clinicmanagementsystem/data/appointments.txt";
    private final List<Appointment> appointments = new ArrayList<Appointment>();

    public AppointmentService() {
        try {
            loadAppointments();
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
    }

    private void loadAppointments() throws IOException {
        List<String> lines = FileManager.readLines(FILE_PATH);
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            // limit -1 keeps a trailing empty "notes" field so the row width is stable
            String[] parts = line.split(",", -1);
            if (parts.length >= 7) {   // skip malformed rows instead of crashing
                appointments.add(new Appointment(
                    parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]
                ));
            }
        }
    }

    public void addAppointment(Appointment appointment) throws IOException {
        appointments.add(appointment);
        FileManager.appendLine(FILE_PATH, appointment.toString());
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<Appointment>(appointments);
    }

    public Appointment findById(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }

    public List<Appointment> findByDate(String date) {
        List<Appointment> result = new ArrayList<Appointment>();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentDate().equals(date)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /** Update one appointment's status and persist the whole file. */
    public boolean updateStatus(String appointmentId, String newStatus) throws IOException {
        Appointment target = findById(appointmentId);
        if (target == null) {
            return false;
        }
        target.setStatus(newStatus);
        saveAll();
        return true;
    }

    private void saveAll() throws IOException {
        List<String> lines = new ArrayList<String>();
        for (Appointment appointment : appointments) {
            lines.add(appointment.toString());
        }
        FileManager.writeLines(FILE_PATH, lines);
    }
}
