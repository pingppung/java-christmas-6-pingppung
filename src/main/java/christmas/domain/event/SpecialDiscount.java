package christmas.domain.event;

import christmas.enums.EventType;

public class SpecialDiscount extends Event {
    private static final int DISCOUNT_AMOUNT = 1000;

    public SpecialDiscount() {
        super(EventType.SPECIAL_DISCOUNT);
    }

    @Override
    public int calculateDiscount() {
        return DISCOUNT_AMOUNT;
    }
}
