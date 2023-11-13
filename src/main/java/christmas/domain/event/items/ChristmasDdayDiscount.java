package christmas.domain.event.items;

import christmas.enums.EventType;

public class ChristmasDdayDiscount extends Event {
    private static final int BASE_DISCOUNT = 1_000;
    private static final int ADDITIONAL_DISCOUNT_PER_DAY = 100;
    private static final int CHRISTMAS_DAY = 25;
    private final int dday;

    public ChristmasDdayDiscount(int dday) {
        super(EventType.CHRISTMAS_DDAY_DISCOUNT);
        this.dday = dday;
    }

    @Override
    public int calculateDiscount() {
        int elapsedDays = CHRISTMAS_DAY - dday - 1;
        return BASE_DISCOUNT + (ADDITIONAL_DISCOUNT_PER_DAY * elapsedDays);
    }
}
