package christmas.services.date;

import static christmas.enums.ChristmasDate.CHRISTMAS_DAY;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator {
    private final LocalDate christmasDate;

    public DateCalculator() {
        christmasDate = CHRISTMAS_DAY.getDate();
    }

    public int countDday(int day) {
        LocalDate date = LocalDate.of(christmasDate.getYear(), christmasDate.getMonthValue(), day);
        long dday = ChronoUnit.DAYS.between(date, christmasDate);
        return (int) dday;
    }
}
