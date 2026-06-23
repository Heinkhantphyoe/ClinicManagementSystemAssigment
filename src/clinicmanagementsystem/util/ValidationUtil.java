package clinicmanagementsystem.util;

public class ValidationUtil {
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\+?[0-9]{9,15}$");
    }
    
    public static boolean isValidDate(String value) {
        return value != null && value.trim().matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    public static boolean isDouble(String value) {
        if (isNullOrEmpty(value)) return false;
        try { Double.parseDouble(value.trim()); return true; }
        catch (NumberFormatException e) { return false; }
    }

    public static boolean isInteger(String value) {
        if (isNullOrEmpty(value)) return false;
        try { Integer.parseInt(value.trim()); return true; }
        catch (NumberFormatException e) { return false; }
    }
    
    /** A comma would corrupt the CSV files, so block it in free-text fields. */
    public static boolean containsComma(String value) {
        return value != null && value.contains(",");
    }
    
    /** Parse an int, returning {@code fallback} on null/blank/garbage. */
    public static int parseIntOr(String value, int fallback) {
        if (isNullOrEmpty(value)) return fallback;
        try { return Integer.parseInt(value.trim()); }
        catch (NumberFormatException e) { return fallback; }
    }
}
