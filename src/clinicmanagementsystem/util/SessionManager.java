package clinicmanagementsystem.util;

import clinicmanagementsystem.model.User;

/**
 * Holds the user that is currently logged in, so any screen can find out who
 * they are without being passed the User object through every constructor.
 *
 * LoginGUI calls setCurrentUser(...) right after a successful login; the nurse
 * screens read getCurrentUser() to filter "my" roster / appointments.
 */
public final class SessionManager {

    private static User currentUser;

    private SessionManager() { }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    /** Convenience: the logged-in user's id (e.g. "U003"), or null if none. */
    public static String getCurrentUserId() {
        return currentUser == null ? null : currentUser.getUserId();
    }

    public static void clear() {
        currentUser = null;
    }
}
