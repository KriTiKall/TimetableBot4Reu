package model.mappers;

import java.text.SimpleDateFormat;
import java.util.*;

public class Mapper4Array extends Mapper<List<List<String>>,  String[][]> {

    private String getTomorrow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yy");
        Calendar calendar = new GregorianCalendar();

        calendar.add(Calendar.DATE, +1);
        if (Calendar.DAY_OF_WEEK == 5)
            calendar.add(Calendar.DATE, +2);
        else if (Calendar.DAY_OF_WEEK == 6)
            calendar.add(Calendar.DATE, +1);
        calendar.add(calendar.DATE, -2);

        return (dateFormat.format(calendar.getTime())).toString();
    }

    @Override
    protected String[][] mapImp(List<List<String>> item) {

        int index = 0;
        String tom = getTomorrow();
        String [][] newArr = new String[2][9];

        for (int i = 0; i < item.get(0).size(); i++)
            if (item.get(0).get(i).contains(tom))
                index = i;
//
//        for (int i = 0; i < item.size(); i++) {
//            for (int j = 0; j < item.get(i).size(); j++) {
//                System.out.println(i + ", " + j + " : " + item.get(i).get(j));
//            }
//        }
        for (int i = 0; i < item.size(); i++) {
            newArr[0][i] = item.get(i).get(0);
            newArr[1][i] = item.get(i).get(index);
        }
        return newArr;
    }
}