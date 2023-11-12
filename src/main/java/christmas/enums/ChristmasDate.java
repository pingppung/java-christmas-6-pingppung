package christmas.enums;

import java.time.LocalDate;

public enum ChristmasDate {
    CHRISTMAS_DAY(LocalDate.of(2023, 12, 25));

    private final LocalDate date;

    ChristmasDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
