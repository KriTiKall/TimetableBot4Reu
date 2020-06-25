package model;

import data.ParserPK_21;
import model.mappers.Mapper4Array;
import model.performers.ProcessingTimetable;
import model.performers.TimerForParse;
import model.repository.Repository;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Manager {

    private Repository repository;
    private TimerForParse timer = new TimerForParse();

    private MessageDigest md;

    private String[][] schedule;
    private String hashSum;
    private Consumer<String[][]> consumer;

    public Manager(Consumer<String[][]> consumer) throws NoSuchAlgorithmException {
        this.repository = new Repository(new ParserPK_21(), new Mapper4Array(), new ProcessingTimetable());
        md = MessageDigest.getInstance("MD5");
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
            schedule = repository.getSchedulePK_21();
            hashSum = getHashSum(schedule);
            return true;
        }

        newSchedule = repository.getSchedulePK_21();
        newHashSum = getHashSum(newSchedule);

        if (!newHashSum.equals(hashSum)) {
            hashSum = newHashSum;
            schedule = newSchedule;
            return true;
        }
        return false;
    }

    private String getHashSum(String[][] schedule) {
        byte[] digest;

        md.update(convertToOneLine(schedule).getBytes(Charset.defaultCharset()));
        digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest)
                .toUpperCase();
    }

    private String convertToOneLine(String[][] schedule) {
        return Arrays.stream(schedule)
                .flatMap(Arrays::stream)
                .collect(Collectors.joining());
    }
}
