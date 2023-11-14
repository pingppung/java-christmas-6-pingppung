package christmas.services.date;

import static christmas.domain.event.enums.EventDate.CHRISTMAS_DAY;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator {
    private final LocalDate christmasDate;

    private DateCalculator() {
        christmasDate = CHRISTMAS_DAY.getDate();
    }

    public static DateCalculator create() {
        return new DateCalculator();
    }

    public int countDday(int day) {
        LocalDate reservationDate = LocalDate.of(christmasDate.getYear(), christmasDate.getMonthValue(), day);
        long dday = ChronoUnit.DAYS.between(reservationDate, christmasDate);
        return (int) dday;
    }
}
