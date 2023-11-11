package christmas.services.date;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateReferee {
    private final static int year = 2023;
    private final static int month = 12;

    public DateReferee() {
    }

    public String checkOfWeek(int day) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.toString();
    }

    public String checkWeekendOrWeekday(int day) {
        return "평일";
    }

    public boolean isChristmas(int day) {
        return true;
    }
}
