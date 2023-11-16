package christmas.domain.event.items;

import christmas.domain.event.enums.EventType;

public class SpecialDiscount extends Event {
    private static final int DISCOUNT_AMOUNT = 1_000;

    public SpecialDiscount() {
        super(EventType.SPECIAL_DISCOUNT);
    }

    @Override
    public int calculateDiscount() {
        return DISCOUNT_AMOUNT;
    }
}
