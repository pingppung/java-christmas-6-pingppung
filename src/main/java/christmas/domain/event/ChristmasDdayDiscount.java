package christmas.domain.event;

import christmas.enums.EventType;

public class ChristmasDdayDiscount extends Event {

    public ChristmasDdayDiscount() {
        super(EventType.CHRISTMAS_DDAY_DISCOUNT);
    }

    @Override
    public int calculateDiscount() {
        return 0;
    }
}
