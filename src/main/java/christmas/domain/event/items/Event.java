package christmas.domain.event.items;

import christmas.enums.EventType;

public abstract class Event {
    private int discountAmount;
    private final EventType eventType;

    public Event(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public abstract int calculateDiscount();
}
