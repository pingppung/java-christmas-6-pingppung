package christmas.domain.event;

import christmas.enums.EventType;

public class EligibleEvent {
    private final EventType type;
    private final int discount;

    public EligibleEvent(EventType type, int discount) {
        this.type = type;
        this.discount = discount;
    }

    public String getType() {
        return type.getEventType();
    }

    public int getDiscount() {
        return discount;
    }
}
