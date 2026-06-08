package clinicmanagementsystem.service;

import clinicmanagementsystem.model.Patient;
import clinicmanagementsystem.util.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientService {
    private static final String FILE_PATH = "src/clinicmanagementsystem/data/patients.txt";
    private final List<Patient> patients = new ArrayList<Patient>();

    public PatientService() {
        try {
            loadPatients();
        } catch (IOException e) {
            System.err.println("Error loading patients: " + e.getMessage());
        }
    }

    private void loadPatients() throws IOException {
        List<String> lines = FileManager.readLines(FILE_PATH);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 9) {
                patients.add(new Patient(
                    parts[0], parts[1], parts[2], parts[3], parts[4],
                    Integer.parseInt(parts[5]), parts[6], parts[7], parts[8]
                ));
            }
        }
    }

    public void addPatient(Patient patient) throws IOException {
        patients.add(patient);
        FileManager.appendLine(FILE_PATH, patient.toString());
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<Patient>(patients);
    }

    public Patient findByPatientId(String patientId) {
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }
}
