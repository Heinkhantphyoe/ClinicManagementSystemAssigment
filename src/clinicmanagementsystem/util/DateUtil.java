package clinicmanagementsystem.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private DateUtil() {
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMAT);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }

    public static LocalDate parseDate(String value) {
        return LocalDate.parse(value, DATE_FORMAT);
    }
}
