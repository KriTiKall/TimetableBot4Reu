package model.performers;

import java.util.function.UnaryOperator;

/**
 *
 */
public class ProcessingTimetable implements UnaryOperator<String[][]> {

    private static final String[] words = {
            " преподаватель техникума", " преподаватель", " доцент",
            " старший преподаватель", " декан факультета", " профессор",
            " заведующий кафедрой", " ассистент"
    };

    private static String[][] removeExcess(String[][] timetable) {
        for (int i = 0; i < timetable.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (timetable[i][1].contains(words[j])) {
                    timetable[i][1] = timetable[i][1].replaceAll(words[j], "");
                }
            }
        }
        return timetable;
    }

    @Override
    public String[][] apply(String[][] timetable) {
        return removeExcess(timetable);
    }
}
