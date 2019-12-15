package by.bsuir.ief.system.fabric.util;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtil {

    /** The date pattern that is used for conversion. Change as you wish. */

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    /** The date formatter. */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Returns the given date as a well formatted String. The above defined
     * {@link DateUtil#DATE_PATTERN} is used.
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        return formattedDate;
    }

    /**
     * Converts a String in the formatDateTime of the defined {@link DateUtil#DATE_PATTERN}
     * to a {@link LocalDate} object.
     *
     * Returns null if the String could not be converted.
     *
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }

    public static LocalDate getLocalDate(@NotNull final Date date){

        Instant instant = date.toInstant();

        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date getDate(final LocalDate localDate){

        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

}
