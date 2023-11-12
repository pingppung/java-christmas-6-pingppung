package christmas.domain.event;

import christmas.enums.EventType;

public class WeekdayDiscount extends Event {
    private static final int DISCOUNT_AMOUNT = 2023;
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
