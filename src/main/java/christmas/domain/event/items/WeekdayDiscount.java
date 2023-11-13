package christmas.domain.event.items;

import christmas.enums.EventType;

public class WeekdayDiscount extends Event {
    private static final int DISCOUNT_AMOUNT = 2_023;
    private final int dessertCount;

    public WeekdayDiscount(int count) {
        super(EventType.WEEKDAY_DISCOUNT);
        this.dessertCount = count;
    }

    @Override
    public int calculateDiscount() {
        return DISCOUNT_AMOUNT * dessertCount;
    }
}
