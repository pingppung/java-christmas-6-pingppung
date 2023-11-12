package christmas.domain.event;

import christmas.enums.EventType;

public class WeekdayDiscount extends Event {
    private final int dessertCount;

    public WeekdayDiscount(int count) {
        super(EventType.WEEKDAY_DISCOUNT);
        this.dessertCount = count;
    }

    @Override
    public int calculateDiscount() {
        int discountAmount = 2023;
        return discountAmount * dessertCount;
    }
}
