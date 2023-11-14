package christmas.domain.event.items;

import christmas.domain.event.enums.EventType;
import christmas.domain.menu.items.Beverage;

public class GiftPromotion extends Event {
    private static final int GIFT_PRICE = Beverage.CHAMPAGNE.getPrice();

    public GiftPromotion() {
        super(EventType.GIFT_PROMOTION);
    }

    @Override
    public int calculateDiscount() {
        return GIFT_PRICE;
    }
}
