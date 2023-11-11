package christmas.services.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator {
    private static final int CHRISTMAS_YEAR = 2023;
    private static final int CHRISTMAS_MONTH = 12;
    private static final int CHRISTMAS_DAY = 25;

    public DateCalculator() {
    }

    public int countDday(int day) {
        LocalDate christmasDate = LocalDate.of(CHRISTMAS_YEAR, CHRISTMAS_MONTH, CHRISTMAS_DAY);
        LocalDate date = LocalDate.of(CHRISTMAS_YEAR, CHRISTMAS_MONTH, day);
        long dday = ChronoUnit.DAYS.between(date, christmasDate);
        return (int) dday;
    }
}
