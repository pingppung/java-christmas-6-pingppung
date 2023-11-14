package christmas.vo;

import christmas.domain.event.enums.EventType;

public record EligibleEventVO(EventType type, int discount) {
    @Override
    public String toString() {
        return type.getEventType() + "-" + discount;
    }
}
