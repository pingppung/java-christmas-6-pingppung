package christmas.services.date;

import static christmas.enums.ChristmasDate.CHRISTMAS_DAY;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateReferee {

    public DateReferee() {
    }

    public DayOfWeek checkOfWeek(int day) {
        LocalDate christmasDate = CHRISTMAS_DAY.getDate();
        LocalDate date = LocalDate.of(christmasDate.getYear(), christmasDate.getMonthValue(), day);
        return date.getDayOfWeek();
    }

    public String checkWeekendOrWeekday(DayOfWeek dayOfWeek) {
        if (isWeekend(dayOfWeek)) {
            return "주말";
        }
        return "평일";
    }

    public boolean hasChristmasNotPassed(int day) {
        return day <= CHRISTMAS_DAY.getDate().getDayOfMonth();
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
