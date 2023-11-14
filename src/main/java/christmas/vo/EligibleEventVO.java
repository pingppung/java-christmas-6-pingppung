package christmas.vo;

import christmas.domain.event.enums.EventType;
import christmas.utils.Parser;

public record EligibleEventVO(EventType type, int discount) {
    @Override
    public String toString() {
        return type.getEventType() + ": " + Parser.formatMoneyCurrency(-discount) + "원";
    }
}
