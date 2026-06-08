package clinicmanagementsystem.model;

public class MedicalRecord {
    private String recordId;
    private String patientId;
    private String doctorId;
    private String diagnosis;
    private String treatment;
    private String visitDate;

    public MedicalRecord() {
    }

    public MedicalRecord(String recordId, String patientId, String doctorId, String diagnosis,
            String treatment, String visitDate) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.visitDate = visitDate;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return recordId + "," + patientId + "," + doctorId + "," + diagnosis + ","
                + treatment + "," + visitDate;
    }
}
