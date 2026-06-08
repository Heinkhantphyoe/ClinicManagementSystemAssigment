package clinicmanagementsystem.model;

public class Admin extends User {

    public Admin() {
        setRole("Admin");
    }

    public Admin(String userId, String name, String gender, String phoneNumber, String email,
            String username, String password) {
        super(userId, name, gender, phoneNumber, email, username, password, "Admin");
    }
}
