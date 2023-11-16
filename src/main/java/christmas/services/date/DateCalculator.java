package christmas.services.date;

import christmas.domain.event.enums.EventDate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator {
    private final LocalDate christmasDate;

    public DateCalculator() {
        this.christmasDate = EventDate.CHRISTMAS_DAY.getDate();
    }

    public int countDaysUntilChristmas(LocalDate reservationDate) {
        long daysUntilChristmas = ChronoUnit.DAYS.between(reservationDate, christmasDate);
        return (int) daysUntilChristmas;
    }
}
