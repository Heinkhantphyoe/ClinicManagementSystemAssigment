package clinicmanagementsystem.model;

public class Roster {
    private String rosterId;
    private String staffId;
    private String staffRole;
    private String dutyDate;
    private String shift;

    public Roster() {
    }

    public Roster(String rosterId, String staffId, String staffRole, String dutyDate, String shift) {
        this.rosterId = rosterId;
        this.staffId = staffId;
        this.staffRole = staffRole;
        this.dutyDate = dutyDate;
        this.shift = shift;
    }

    public String getRosterId() {
        return rosterId;
    }

    public void setRosterId(String rosterId) {
        this.rosterId = rosterId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(String dutyDate) {
        this.dutyDate = dutyDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return rosterId + "," + staffId + "," + staffRole + "," + dutyDate + "," + shift;
    }
}
