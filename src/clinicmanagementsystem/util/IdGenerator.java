package clinicmanagementsystem.util;

public class IdGenerator {
    private IdGenerator() {
    }

    public static String generateId(String prefix, int number) {
        return prefix + String.format("%04d", number);
    }
}
