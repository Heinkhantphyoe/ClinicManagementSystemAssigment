package clinicmanagementsystem.model;

public class Receptionist extends User {
    private String counterNumber;

    public Receptionist() {
        setRole("Receptionist");
    }

    public Receptionist(String userId, String name, String gender, String phoneNumber, String email,
            String username, String password, String counterNumber) {
        super(userId, name, gender, phoneNumber, email, username, password, "Receptionist");
        this.counterNumber = counterNumber;
    }

    public String getCounterNumber() {
        return counterNumber;
    }

    public void setCounterNumber(String counterNumber) {
        this.counterNumber = counterNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "," + counterNumber;
    }
}
