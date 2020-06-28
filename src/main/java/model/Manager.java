package model;

import data.repository.RepositoryPK_21;

import java.util.function.Consumer;

public class Manager {

    private RepositoryPK_21 repository;
    private TimerForParse timer = new TimerForParse();
    private ScheduleHash adder = new ScheduleHash();

    private String[][] schedule;
    private String hashSum;
    private Consumer<String[][]> consumer;

    public Manager(Consumer<String[][]> consumer) {
        this.repository = new RepositoryPK_21();
        this.consumer = consumer;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void start() {
        timer.timer(() -> {
            if (compare())
                consumer.accept(schedule);
        });
    }

    private boolean compare() {
        String[][] newSchedule;
        String newHashSum;

        if (schedule == null) {
            schedule = repository.getSchedule();
            hashSum = adder.getHashSum(schedule);
            return true;
        }

        newSchedule = repository.getSchedule();
        newHashSum = adder.getHashSum(newSchedule);

        if (!newHashSum.equals(hashSum)) {
            hashSum = newHashSum;
            schedule = newSchedule;
            return true;
        }
        return false;
    }
}
