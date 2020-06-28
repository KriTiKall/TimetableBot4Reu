package model.services;

import model.mappers.Mapper;

import java.util.List;

public class ScheduleService implements Service {

    private Mapper<List<List<String>>, String[][]> mapper;

    private final String[] words = {
            " преподаватель техникума", " преподаватель", " доцент",
            " старший преподаватель", " декан факультета", " профессор",
            " заведующий кафедрой", " ассистент"
    };

    public ScheduleService(Mapper<List<List<String>>, String[][]> mapper) {
        this.mapper = mapper;
    }

    @Override
    public String[][] getProcessedData(List<List<String>> schedule) {
        return removeExcess(mapper.map(schedule));
    }

    private String[][] removeExcess(String[][] schedule) {
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (schedule[i][1].contains(words[j])) {
                    schedule[i][1] = schedule[i][1].replaceAll(words[j], "");
                }
            }
        }
        return schedule;
    }
}
