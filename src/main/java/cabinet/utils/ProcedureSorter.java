package cabinet.utils;

import cabinet.model.Procedure;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

public class ProcedureSorter implements Comparator<Procedure>
{
    @Override
    public int compare(Procedure p1, Procedure p2) {
        LocalDate date1 = LocalDate.parse(p1.getDate());
        LocalDate date2 = LocalDate.parse(p2.getDate());
        int comparedDate = date1.compareTo(date2);
        if (comparedDate != 0) {
            return comparedDate;
        } else {
            LocalTime time1 = LocalTime.parse(p1.getTime());
            LocalTime time2 = LocalTime.parse(p2.getTime());
            return time1.compareTo(time2);
        }

    }
}