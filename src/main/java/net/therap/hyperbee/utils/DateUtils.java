package net.therap.hyperbee.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rumman
 * @since 12/5/16
 */
public class DateUtils {

    public static Calendar getCalendarFromString(String dateTime) {
        Calendar calendar = new GregorianCalendar();

        String regExp = "^([0-9]+)/([0-9]+)/([0-9]+) ([0-9]+):([0-9]+) (AM|PM)$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(dateTime);

        while (matcher.find()) {

            calendar.set(Calendar.MONTH, Integer.parseInt(matcher.group(1)) - 1);
            calendar.set(Calendar.DATE, Integer.parseInt(matcher.group(2)));
            calendar.set(Calendar.YEAR, Integer.parseInt(matcher.group(3)));
            calendar.set(Calendar.HOUR, Integer.parseInt(matcher.group(4)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(matcher.group(5)));
            calendar.set(Calendar.AM_PM, (matcher.group(6)).equals("AM") ? 0 : 1);
        }

        return calendar;
    }
}
