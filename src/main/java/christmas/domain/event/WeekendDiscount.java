package christmas.domain.event;

import christmas.enums.EventType;

public class WeekendDiscount extends Event {
    private final int mainDishCount;

    public WeekendDiscount(int count) {
        super(EventType.WEEKEND_DISCOUNT);
        this.mainDishCount = count;
    }

    @Override
    public int calculateDiscount() {
        int discountAmount = 2023;
        return discountAmount * mainDishCount;
    }
}
