package christmas.domain.event;

import christmas.services.date.DateReferee;
import java.time.DayOfWeek;

public class Judgement {
    private static final int FREE_CHAMPAGNE_THRESHOLD = 120_000;
    private final DateReferee dateReferee;

    //날짜에 대한 이벤트 조건 판단
    public Judgement(DateReferee dateReferee) {
        this.dateReferee = dateReferee;
    }

    public void isEligibleForEvent(int day, int dessert, int mainDish, int total) {
        hasChristmasDdayDiscount(day);
        hasWeekendOrWeekdayDiscount(dessert, mainDish);
        hasSpecialDiscount();
        hasGiftPromotion(total);
    }

    protected void hasChristmasDdayDiscount(int day) {
        if (dateReferee.hasChristmasNotPassed()) {
            //1. 디데이 날짜 구해서 하는 방법
//            DateCalculator dateCalculator = DateCalculator.create();
//            int dday = dateCalculator.countDday(day);
//            System.out.println("크리스마스 D-day 할인 이벤트 적용! D-day: " + dday);
//            Event event = new ChristmasDdayDiscount(dday);

            //2. 그냥 입력 날짜 보내는 방법
            Event event = new ChristmasDdayDiscount(day);
            int discount = event.calculateDiscount();
            System.out.println("크리스마스 D-day 할인 이벤트 적용! 할인 금액: " + discount);
        }
    }

    protected void hasWeekendOrWeekdayDiscount(int dessertCount, int maindishCount) {
        DayOfWeek dayOfWeek = dateReferee.checkOfWeek();
        String isWeekdayOrWeekend = dateReferee.checkWeekendOrWeekday(dayOfWeek);
        if (isWeekdayOrWeekend.equals("평일")) {
            applyWeekdayDiscount(dessertCount);
        }
        if (isWeekdayOrWeekend.equals("주말")) {
            applyWeekendDiscount(maindishCount);
        }
    }

    private void applyWeekdayDiscount(int count) {
        Event event = new WeekdayDiscount(count);
        int discount = event.calculateDiscount();
        System.out.println("평일 할인 적용! 할인 금액: " + discount);
    }

    private void applyWeekendDiscount(int count) {
        Event event = new WeekendDiscount(count);
        int discount = event.calculateDiscount();
        System.out.println("주말 할인 적용! 할인 금액: " + discount);
    }

    public void hasSpecialDiscount() {
        if (checkStarInEventCalendar()) {
            Event event = new SpecialDiscount();
            int discount = event.calculateDiscount();
            System.out.println("특별 할인 적용! 할인 금액: " + discount);
        }
    }

    protected boolean checkStarInEventCalendar() {
        return dateReferee.checkOfWeek() == DayOfWeek.SUNDAY || dateReferee.isChristmas();
    }

    public void hasGiftPromotion(int totalAmount) {
        if (totalAmount >= FREE_CHAMPAGNE_THRESHOLD) {
            Event event = new GiftPromotion();
            int discount = event.calculateDiscount();
            System.out.println("샴페인 증정 적용! 할인 금액: " + discount);
        }
    }
}
