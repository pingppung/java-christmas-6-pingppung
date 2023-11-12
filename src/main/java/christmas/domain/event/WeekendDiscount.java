package christmas.domain.event;

import christmas.enums.EventType;

public class WeekendDiscount extends Event {
    public WeekendDiscount() {
        super(EventType.WEEKEND_DISCOUNT);
    }

    @Override
    public int calculateDiscount() {
        return 0;
    }
}
