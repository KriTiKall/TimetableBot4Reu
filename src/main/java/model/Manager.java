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
import java.util.stream.Collectors;

public class Manager {

    private Repository repository;
    private TimerForParse timer = new TimerForParse();

    private MessageDigest md;

    private String[][] schedule;
    private String hashSum;
    private boolean isNew = false;

    public Manager() throws NoSuchAlgorithmException {
        this.repository = new Repository(new ParserPK_21(), new Mapper4Array(), new ProcessingTimetable());
        md = MessageDigest.getInstance("MD5");
    }

    public String[][] getSchedule() {
        return schedule;
    }

    private void compare() {
        String[][] newSchedule;
        String newHashSum;

        if (schedule == null) {
            schedule = repository.getSchedulePK_21();
            hashSum = getHashSum(schedule);
            isNew = true;
            return;
        }

        newSchedule = repository.getSchedulePK_21();
        newHashSum = getHashSum(newSchedule);

        if (!newHashSum.equals(hashSum)) {
            hashSum = newHashSum;
            schedule = newSchedule;
        }
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
