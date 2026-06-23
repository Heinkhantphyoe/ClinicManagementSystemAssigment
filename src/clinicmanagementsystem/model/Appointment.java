package clinicmanagementsystem.model;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String nurseId;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private String appointType;

    public Appointment() {
    }

    public Appointment(String appointmentId, String patientId, String doctorId, String nurseId, String appointmentDate,
            String appointmentTime, String status, String appointType) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.nurseId = nurseId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.appointType = appointType;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointType() {
        return appointType;
    }

    public void setAppointType(String appointType) {
        this.appointType = appointType;
    }

    @Override
    public String toString() {
        return appointmentId + "," + patientId + "," + doctorId + "," + nurseId + "," + appointmentDate + ","
                + appointmentTime + "," + appointType + "," + status;
    }
}
