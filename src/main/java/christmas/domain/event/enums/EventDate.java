package christmas.domain.event.enums;

import java.time.LocalDate;

public enum EventDate {
    CHRISTMAS_DAY(LocalDate.of(2023, 12, 25));

    private final LocalDate date;

    EventDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonthValue() {
        return date.getMonthValue();
    }
}
