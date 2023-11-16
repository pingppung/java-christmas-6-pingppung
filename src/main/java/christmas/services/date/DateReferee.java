package christmas.services.date;

import christmas.domain.event.enums.EventDate;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateReferee {
    private final LocalDate reservationDate;
    private final LocalDate christmasDate;

    private DateReferee(int day) {
        christmasDate = EventDate.CHRISTMAS_DAY.getDate();
        reservationDate = LocalDate.of(christmasDate.getYear(), christmasDate.getMonthValue(), day);
    }

    public static DateReferee create(int day) {
        return new DateReferee(day);
    }

    public String checkWeekendOrWeekday() {
        if (isWeekend()) {
            return "주말";
        }
        return "평일";
    }

    public DayOfWeek getDayOfWeek() {
        return reservationDate.getDayOfWeek();
    }

    public boolean hasChristmasNotPassed() {
        return reservationDate.isBefore(christmasDate) || reservationDate.isEqual(christmasDate);
    }

    public boolean isChristmas() {
        return reservationDate.equals(christmasDate);
    }

    private boolean isWeekend() {
        return reservationDate.getDayOfWeek() == DayOfWeek.FRIDAY ||
                reservationDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}
