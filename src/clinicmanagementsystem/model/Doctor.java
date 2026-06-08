package clinicmanagementsystem.model;

public class Doctor extends User {
    private String specialty;

    public Doctor() {
        setRole("Doctor");
    }

    public Doctor(String userId, String name, String gender, String phoneNumber, String email,
            String username, String password, String specialty) {
        super(userId, name, gender, phoneNumber, email, username, password, "Doctor");
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return super.toString() + "," + specialty;
    }
}
