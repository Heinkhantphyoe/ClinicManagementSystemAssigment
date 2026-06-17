package clinicmanagementsystem.service;

import clinicmanagementsystem.model.VitalRecord;
import clinicmanagementsystem.util.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File-backed access to vitals.txt (owned by the Nurse module).
 * Follows the same load-into-memory pattern as PatientService.
 *
 * Format: vitalId,patientId,temperature,bloodPressure,heartRate,recordDate
 */
public class VitalService {
    private static final String FILE_PATH = "src/clinicmanagementsystem/data/vitals.txt";
    private final List<VitalRecord> vitals = new ArrayList<VitalRecord>();

    public VitalService() {
        try {
            loadVitals();
        } catch (IOException e) {
            System.err.println("Error loading vitals: " + e.getMessage());
        }
    }

    private void loadVitals() throws IOException {
        List<String> lines = FileManager.readLines(FILE_PATH);
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            if (parts.length >= 6) {   // skip malformed rows instead of crashing
                vitals.add(new VitalRecord(
                    parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]
                ));
            }
        }
    }

    public List<VitalRecord> getAllVitals() {
        return new ArrayList<VitalRecord>(vitals);
    }

    public List<VitalRecord> findByPatientId(String patientId) {
        List<VitalRecord> result = new ArrayList<VitalRecord>();
        for (VitalRecord v : vitals) {
            if (v.getPatientId().equalsIgnoreCase(patientId)) {
                result.add(v);
            }
        }
        return result;
    }

    public void addVital(VitalRecord vital) throws IOException {
        vitals.add(vital);
        FileManager.appendLine(FILE_PATH, vital.toString());
    }

    /** Update an existing record (matched by vitalId) and persist the file. */
    public boolean update(VitalRecord updated) throws IOException {
        for (int i = 0; i < vitals.size(); i++) {
            if (vitals.get(i).getVitalId().equalsIgnoreCase(updated.getVitalId())) {
                vitals.set(i, updated);
                saveAll();
                return true;
            }
        }
        return false;
    }

    private void saveAll() throws IOException {
        List<String> lines = new ArrayList<String>();
        for (VitalRecord v : vitals) {
            lines.add(v.toString());
        }
        FileManager.writeLines(FILE_PATH, lines);
    }

    /** Next id like V001, V002 ... matching the width used in the seed data. */
    public String nextId() {
        int max = 0;
        for (VitalRecord v : vitals) {
            String id = v.getVitalId();
            if (id != null && id.length() > 1) {
                try { max = Math.max(max, Integer.parseInt(id.substring(1))); }
                catch (NumberFormatException ignored) { }
            }
        }
        return String.format("V%03d", max + 1);
    }
}
