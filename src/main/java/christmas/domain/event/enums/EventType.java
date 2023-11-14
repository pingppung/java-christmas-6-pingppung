package christmas.domain.event.enums;

import christmas.domain.event.items.ChristmasDdayDiscount;
import christmas.domain.event.items.Event;
import christmas.domain.event.items.GiftPromotion;
import christmas.domain.event.items.SpecialDiscount;
import christmas.domain.event.items.WeekdayDiscount;
import christmas.domain.event.items.WeekendDiscount;
import christmas.dto.EventParamsDTO;

public enum EventType {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인") {
        @Override
        public Event createEvent(EventParamsDTO params) {
            return new ChristmasDdayDiscount(params.getDday());
        }
    },
    WEEKDAY_DISCOUNT("평일 할인") {
        @Override
        public Event createEvent(EventParamsDTO params) {
            return new WeekdayDiscount(params.getDessertCount());
        }
    },
    WEEKEND_DISCOUNT("주말 할인") {
        @Override
        public Event createEvent(EventParamsDTO params) {
            return new WeekendDiscount(params.getMainDishCount());
        }
    },
    SPECIAL_DISCOUNT("특별 할인") {
        @Override
        public Event createEvent(EventParamsDTO params) {
            return new SpecialDiscount();
        }
    },
    GIFT_PROMOTION("증정 이벤트") {
        @Override
        public Event createEvent(EventParamsDTO params) {
            return new GiftPromotion();
        }
    };

    private final String eventType;

    EventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public abstract Event createEvent(EventParamsDTO params);
}
