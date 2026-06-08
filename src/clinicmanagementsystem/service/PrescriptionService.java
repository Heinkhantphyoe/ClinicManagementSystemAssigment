package clinicmanagementsystem.service;

import clinicmanagementsystem.model.Prescription;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionService {
    private final List<Prescription> prescriptions = new ArrayList<Prescription>();

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return new ArrayList<Prescription>(prescriptions);
    }

    public Prescription findById(String prescriptionId) {
        for (Prescription prescription : prescriptions) {
            if (prescription.getPrescriptionId().equals(prescriptionId)) {
                return prescription;
            }
        }
        return null;
    }
}
