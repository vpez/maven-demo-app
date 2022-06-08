package worldreadiness;

import com.ibm.icu.text.*;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;

import java.util.Locale;

public class LocaleTest {
    public static void main(String[] args) {
        long time = 946724400000L;
        Calendar calendar;
        DateFormat df;
        Locale locale;
        TimeZone timeZone = TimeZone.GMT_ZONE;

        // 1
        locale = Locale.forLanguageTag("lt-LT");
        df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
        calendar = Calendar.getInstance(timeZone, locale);
        calendar.setTimeInMillis(time);
        df.setCalendar(calendar);
        System.out.println(df.format(calendar));

        // 2
        locale = Locale.forLanguageTag("sq-AL");
        calendar = Calendar.getInstance(timeZone, locale);
        calendar.setTimeInMillis(time);
        df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
        df.setCalendar(calendar);
        System.out.println(df.format(calendar));

        // 3
        calendar = Calendar.getInstance(Locale.forLanguageTag("ar-LB"));
        System.out.println(calendar.getFirstDayOfWeek());

        // 4
        locale = Locale.forLanguageTag("th-TH");
        calendar = Calendar.getInstance(locale);
        System.out.println(calendar.getDisplayName(locale));

        // 5
        timeZone = TimeZone.getTimeZone("Brazil/West");
        System.out.println(timeZone.getDisplayName(Locale.forLanguageTag("en-US")));
    }
}
