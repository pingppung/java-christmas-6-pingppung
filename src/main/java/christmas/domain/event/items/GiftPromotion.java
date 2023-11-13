package christmas.domain.event.items;

import christmas.enums.EventType;

public class GiftPromotion extends Event {
    private static final int CHAMPAGNE_PRICE = 25_000;

    public GiftPromotion() {
        super(EventType.GIFT_PROMOTION);
    }

    @Override
    public int calculateDiscount() {
        return CHAMPAGNE_PRICE;
    }
}
