package christmas.domain.event;

import christmas.enums.EventType;

public class WeekendDiscount extends Event {

    private static final int DISCOUNT_AMOUNT = 2023;
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
