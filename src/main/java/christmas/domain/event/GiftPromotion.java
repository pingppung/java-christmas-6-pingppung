package christmas.domain.event;

import christmas.enums.EventType;

public class GiftPromotion extends Event {

    public GiftPromotion() {
        super(EventType.GIFT_PROMOTION);
    }

    @Override
    public int calculateDiscount() { //샴페인값 25,000
        return 0;
    }
}
