package clinicmanagementsystem.model;

public class VitalRecord {
    private String vitalId;
    private String patientId;
    private String temperature;
    private String bloodPressure;
    private String heartRate;
    private String recordDate;

    public VitalRecord() {
    }

    public VitalRecord(String vitalId, String patientId, String temperature, String bloodPressure,
            String heartRate, String recordDate) {
        this.vitalId = vitalId;
        this.patientId = patientId;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.recordDate = recordDate;
    }

    public String getVitalId() {
        return vitalId;
    }

    public void setVitalId(String vitalId) {
        this.vitalId = vitalId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return vitalId + "," + patientId + "," + temperature + "," + bloodPressure + ","
                + heartRate + "," + recordDate;
    }
}
