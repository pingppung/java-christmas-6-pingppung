package christmas.domain.event;

import christmas.enums.EventType;

public class SpecialDiscount extends Event {
    public SpecialDiscount() {
        super(EventType.SPECIAL_DISCOUNT);
    }

    @Override
    public int calculateDiscount() {
        return 0;
    }
}
