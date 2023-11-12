package christmas.domain.event;

import christmas.enums.EventType;

public class WeekdayDiscount extends Event {
    public WeekdayDiscount() {
        super(EventType.WEEKDAY_DISCOUNT);
    }

    @Override
    public int calculateDiscount() {
        return 0;
    }
}
