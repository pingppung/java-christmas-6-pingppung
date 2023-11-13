package christmas.services.date;

import static christmas.enums.EventDate.CHRISTMAS_DAY;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateReferee {
    private final LocalDate reservationDate;
    private final LocalDate christmasDate = CHRISTMAS_DAY.getDate();

    private DateReferee(int day) {
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

    public boolean isChristmas() {
        return reservationDate.equals(christmasDate);
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
