package christmas.services.date;

import christmas.enums.ChristmasDate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator {
    private final LocalDate christmasDate;

    public DateCalculator() {
        christmasDate = LocalDate.of(ChristmasDate.YEAR.getValue(), ChristmasDate.MONTH.getValue(),
                ChristmasDate.DAY.getValue());
    }

    public int countDday(int day) {
        LocalDate date = LocalDate.of(ChristmasDate.YEAR.getValue(), ChristmasDate.MONTH.getValue(), day);
        long dday = ChronoUnit.DAYS.between(date, christmasDate);
        return (int) dday;
    }
}
