package christmas.services.date;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateReferee {
    private static final int DEFAULT_YEAR = 2023;
    private static final int DEFAULT_MONTH = 12;

    public DateReferee() {
    }

    public DayOfWeek checkOfWeek(int day) {
        LocalDate date = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, day);
        return date.getDayOfWeek();
    }

    public String checkWeekendOrWeekday(DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return "주말";
        }
        return "평일";
    }

    public boolean hasChristmasNotPassed(int day) {
        return day <= 25;
    }
}
