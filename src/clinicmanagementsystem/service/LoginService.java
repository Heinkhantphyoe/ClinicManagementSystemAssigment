package clinicmanagementsystem.service;

import clinicmanagementsystem.model.User;
import java.util.List;

public class LoginService {
    public User login(String username, String password, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
