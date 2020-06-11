package model.performers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 */
public class ProcessingTimetable {

    private static final String[] words = {
            " преподаватель техникума", " преподаватель", " доцент",
            " старший преподаватель", " декан факультета", " профессор",
            " заведующий кафедрой", " ассистент"
    };

    public static String[][] removeExcess(String[][] timetable) {
        for (int i = 0 ; i < timetable.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (timetable[i][1].contains(words[j])) {
                    timetable[i][1] = timetable[i][1].replaceAll(words[j], "");
                }
            }
        }
        return timetable;
    }
}
