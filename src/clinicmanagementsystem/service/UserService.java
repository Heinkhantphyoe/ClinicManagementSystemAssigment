package clinicmanagementsystem.service;

import clinicmanagementsystem.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<User>(users);
    }

    public User findById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public boolean removeUser(String userId) {
        User user = findById(userId);
        return user != null && users.remove(user);
    }
}
