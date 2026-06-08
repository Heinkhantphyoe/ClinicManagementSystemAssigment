package clinicmanagementsystem.model;

public class Nurse extends User {
    private String assignedWard;

    public Nurse() {
        setRole("Nurse");
    }

    public Nurse(String userId, String name, String gender, String phoneNumber, String email,
            String username, String password, String assignedWard) {
        super(userId, name, gender, phoneNumber, email, username, password, "Nurse");
        this.assignedWard = assignedWard;
    }

    public String getAssignedWard() {
        return assignedWard;
    }

    public void setAssignedWard(String assignedWard) {
        this.assignedWard = assignedWard;
    }

    @Override
    public String toString() {
        return super.toString() + "," + assignedWard;
    }
}
