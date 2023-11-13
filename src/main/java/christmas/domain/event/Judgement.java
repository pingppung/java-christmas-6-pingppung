package christmas.domain.event;

import christmas.domain.event.items.ChristmasDdayDiscount;
import christmas.domain.event.items.Event;
import christmas.domain.event.items.GiftPromotion;
import christmas.domain.event.items.SpecialDiscount;
import christmas.domain.event.items.WeekdayDiscount;
import christmas.domain.event.items.WeekendDiscount;
import christmas.enums.EventType;
import christmas.services.date.DateCalculator;
import christmas.services.date.DateReferee;
import christmas.vo.EligibleEventVO;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Judgement {
    private static final int FREE_CHAMPAGNE_THRESHOLD = 120_000;
    private static final String WEEKDAY = "평일";
    private static final String WEEKEND = "주말";
    private final DateReferee dateReferee;
    private final List<EligibleEventVO> eligibleEvents;


    //날짜에 대한 이벤트 조건 판단
    public Judgement(DateReferee dateReferee) {
        this.dateReferee = dateReferee;
        this.eligibleEvents = new ArrayList<>();
    }

    public void processEligibilityEvents(int day, int dessert, int mainDish, int total) {
        eligibleEvents.clear();
        addChristmasDdayDiscount(day);
        addWeekendOrWeekdayDiscount(dessert, mainDish);
        addSpecialDiscount();
        addGiftPromotion(total);
    }

    protected void addChristmasDdayDiscount(int day) {
        if (dateReferee.hasChristmasNotPassed()) {
            DateCalculator dateCalculator = DateCalculator.create();
            int dday = dateCalculator.countDday(day);
            Event event = new ChristmasDdayDiscount(dday);
            addEventToEligibleList(EventType.CHRISTMAS_DDAY_DISCOUNT, event);
        }
    }

    protected void addWeekendOrWeekdayDiscount(int dessertCount, int maindishCount) {
        DayOfWeek dayOfWeek = dateReferee.checkOfWeek();
        String isWeekdayOrWeekend = dateReferee.checkWeekendOrWeekday(dayOfWeek);
        if (isWeekdayOrWeekend.equals(WEEKDAY)) {
            applyDiscount(new WeekdayDiscount(dessertCount));
        }
        if (isWeekdayOrWeekend.equals(WEEKEND)) {
            applyDiscount(new WeekendDiscount(maindishCount));
        }
    }

    private void applyDiscount(Event event) {
        addEventToEligibleList(event.getEventType(), event);
    }

    public void addSpecialDiscount() {
        if (checkStarInEventCalendar()) {
            Event event = new SpecialDiscount();
            addEventToEligibleList(EventType.SPECIAL_DISCOUNT, event);
        }
    }

    protected boolean checkStarInEventCalendar() {
        return dateReferee.checkOfWeek() == DayOfWeek.SUNDAY || dateReferee.isChristmas();
    }

    protected void addGiftPromotion(int totalAmount) {
        if (checkTotalAmount(totalAmount)) {
            Event event = new GiftPromotion();
            addEventToEligibleList(EventType.GIFT_PROMOTION, event);
        }
    }

    protected boolean checkTotalAmount(int totalAmount) {
        return totalAmount >= FREE_CHAMPAGNE_THRESHOLD;
    }

    private void addEventToEligibleList(EventType eventType, Event event) {
        eligibleEvents.add(new EligibleEventVO(eventType, event.calculateDiscount()));
    }
}
