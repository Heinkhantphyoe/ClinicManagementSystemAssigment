package clinicmanagementsystem.util;

import clinicmanagementsystem.model.Patient;
import clinicmanagementsystem.model.User;

/**
 * SessionManager holds the currently logged-in user for the lifetime of
 * the application. It supports two separate session types:
 *  - Staff (User): Admin, Doctor, Nurse, Receptionist
 *  - Patient (Patient)
 *
 * Access from any class using:
 *   SessionManager.getCurrentUser()    → for staff
 *   SessionManager.getCurrentPatient() → for patients
 */
public class SessionManager {

    private static User currentUser = null;
    private static Patient currentPatient = null;

    // Private constructor — this class should never be instantiated
    private SessionManager() {}

    // -------------------------
    // Staff session
    // -------------------------

    public static void setCurrentUser(User user) {
        currentUser = user;
        currentPatient = null; // clear patient session if staff logs in
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static String getCurrentUserId() {
        return currentUser != null ? currentUser.getUserId() : null;
    }

    // -------------------------
    // Patient session
    // -------------------------

    public static void setCurrentPatient(Patient patient) {
        currentPatient = patient;
        currentUser = null; // clear staff session if patient logs in
    }

    public static Patient getCurrentPatient() {
        return currentPatient;
    }

    public static String getCurrentPatientId() {
        return currentPatient != null ? currentPatient.getPatientId() : null;
    }

    // -------------------------
    // General helpers
    // -------------------------

    /** Returns true if a staff member is logged in. */
    public static boolean isStaffLoggedIn() {
        return currentUser != null;
    }

    /** Returns true if a patient is logged in. */
    public static boolean isPatientLoggedIn() {
        return currentPatient != null;
    }

    /** Clears all session data (call on logout). */
    public static void logout() {
        currentUser = null;
        currentPatient = null;
    }
}
