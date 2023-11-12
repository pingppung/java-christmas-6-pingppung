package christmas.services.date;

import christmas.enums.ChristmasDate;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateReferee {

    public DateReferee() {
    }

    public DayOfWeek checkOfWeek(int day) {
        LocalDate date = LocalDate.of(ChristmasDate.YEAR.getValue(), ChristmasDate.MONTH.getValue(), day);
        return date.getDayOfWeek();
    }

    public String checkWeekendOrWeekday(DayOfWeek dayOfWeek) {
        if (isWeekend(dayOfWeek)) {
            return "주말";
        }
        return "평일";
    }

    public boolean hasChristmasNotPassed(int day) {
        return day <= ChristmasDate.DAY.getValue();
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
