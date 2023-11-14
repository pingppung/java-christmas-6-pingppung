package christmas.domain.event;

import christmas.domain.event.enums.EventType;
import christmas.services.date.DateReferee;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Judgement {
    private static final int FREE_CHAMPAGNE_THRESHOLD = 120_000;
    private static final String WEEKDAY = "평일";
    private static final String WEEKEND = "주말";
    private final DateReferee dateReferee;
    private final List<EventType> eligibleEvents;

    public Judgement(DateReferee dateReferee) {
        this.dateReferee = dateReferee;
        this.eligibleEvents = new ArrayList<>();
    }

    public List<EventType> processEligibilityEvents(int day, int total) {
        eligibleEvents.clear();
        addChristmasDdayDiscount(day);
        addWeekendOrWeekdayDiscount();
        addSpecialDiscount();
        addGiftPromotion(total);
        return new ArrayList<>(eligibleEvents);
    }

    protected void addChristmasDdayDiscount(int day) {
        if (dateReferee.hasChristmasNotPassed()) {
            eligibleEvents.add(EventType.CHRISTMAS_DDAY_DISCOUNT);
        }
    }

    protected void addWeekendOrWeekdayDiscount() {
        DayOfWeek dayOfWeek = dateReferee.checkOfWeek();
        String isWeekdayOrWeekend = dateReferee.checkWeekendOrWeekday(dayOfWeek);
        if (isWeekdayOrWeekend.equals(WEEKDAY)) {
            eligibleEvents.add(EventType.WEEKDAY_DISCOUNT);
        }
        if (isWeekdayOrWeekend.equals(WEEKEND)) {
            eligibleEvents.add(EventType.WEEKEND_DISCOUNT);
        }
    }

    public void addSpecialDiscount() {
        if (checkStarInEventCalendar()) {
            eligibleEvents.add(EventType.SPECIAL_DISCOUNT);
        }
    }

    protected boolean checkStarInEventCalendar() {
        return dateReferee.checkOfWeek() == DayOfWeek.SUNDAY || dateReferee.isChristmas();
    }

    protected void addGiftPromotion(int totalAmount) {
        if (checkTotalAmount(totalAmount)) {
            eligibleEvents.add(EventType.GIFT_PROMOTION);
        }
    }

    protected boolean checkTotalAmount(int totalAmount) {
        return totalAmount >= FREE_CHAMPAGNE_THRESHOLD;
    }
}
