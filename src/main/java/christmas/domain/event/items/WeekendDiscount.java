package christmas.domain.event.items;

import christmas.enums.EventType;

public class WeekendDiscount extends Event {

    private static final int DISCOUNT_AMOUNT = 2_023;
    private final int mainDishCount;

    public WeekendDiscount(int count) {
        super(EventType.WEEKEND_DISCOUNT);
        this.mainDishCount = count;
    }

    @Override
    public int calculateDiscount() {
        return DISCOUNT_AMOUNT * mainDishCount;
    }
}
