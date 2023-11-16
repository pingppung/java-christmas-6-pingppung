package christmas.domain.event.items;

import christmas.domain.event.enums.EventType;
import christmas.domain.menu.items.Beverage;

public class GiftPromotion extends Event {
    private static final String GIFT_ITEM = Beverage.CHAMPAGNE.getName();
    private static final int GIFT_PRICE = Beverage.CHAMPAGNE.getPrice();
    private static final int GIFT_COUNT = 1;

    public GiftPromotion() {
        super(EventType.GIFT_PROMOTION);
    }

    @Override
    public int calculateDiscount() {
        return GIFT_COUNT * GIFT_PRICE;
    }

    public static String getGiftItem() {
        return GIFT_ITEM;
    }

    public static int getGiftCount() {
        return GIFT_COUNT;
    }
}
