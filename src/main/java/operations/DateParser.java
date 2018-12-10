package operations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public LocalDate operate(String date) {
       return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
