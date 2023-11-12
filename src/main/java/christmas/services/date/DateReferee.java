package christmas.services.date;

import static christmas.enums.ChristmasDate.CHRISTMAS_DAY;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateReferee {
    private final LocalDate reservationDate;

    private DateReferee(int day) {
        LocalDate christmasDate = CHRISTMAS_DAY.getDate();
        reservationDate = LocalDate.of(christmasDate.getYear(), christmasDate.getMonthValue(), day);
    }

    public static DateReferee create(int day) {
        return new DateReferee(day);
    }

    public String checkWeekendOrWeekday(DayOfWeek dayOfWeek) {
        if (isWeekend(dayOfWeek)) {
            return "주말";
        }
        return "평일";
    }

    public DayOfWeek checkOfWeek() {
        return reservationDate.getDayOfWeek();
    }

    public boolean hasChristmasNotPassed() {
        return reservationDate.getDayOfMonth() <= CHRISTMAS_DAY.getDate().getDayOfMonth();
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
