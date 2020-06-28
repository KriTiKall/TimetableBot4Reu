package model;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ScheduleHash {

    private MessageDigest md;

    public ScheduleHash() {
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getHashSum(String[][] schedule) {
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
