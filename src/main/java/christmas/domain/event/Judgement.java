package christmas.domain.event;

import christmas.services.date.DateReferee;
import java.time.DayOfWeek;

public class Judgement {
    private final DateReferee dateReferee;

    //날짜에 대한 이벤트 조건 판단
    public Judgement(DateReferee dateReferee, int day, int dessert, int maindish) {
        this.dateReferee = dateReferee;
        isEligibleForEvent(day, dessert, maindish);
    }

    public void isEligibleForEvent(int day, int dessert, int mainDish) {
        hasChristmasDdayDiscount(day);
        hasWeekendOrWeekdayDiscount(dessert, mainDish);
//        hasSpecialDiscount();
    }

    private void hasChristmasDdayDiscount(int day) {
        if (dateReferee.hasChristmasNotPassed()) {
            //1. 디데이 날짜 구해서 하는 방법
//            DateCalculator dateCalculator = DateCalculator.create();
//            int dday = dateCalculator.countDday(day);
//            System.out.println("크리스마스 D-day 할인 이벤트 적용! D-day: " + dday);
//            Event event = new ChristmasDdayDiscount(dday);

            //2. 그냥 입력 날짜 보내는 방법
            Event event = new ChristmasDdayDiscount(day);
            int discount = event.calculateDiscount();
            System.out.println("크리스마스 D-day 할인 이벤트 적용!");
            System.out.println(discount);
        }
    }

    private void hasWeekendOrWeekdayDiscount(int dessertCount, int maindishCount) {
        DayOfWeek dayOfWeek = dateReferee.checkOfWeek();
        String isWeekday = dateReferee.checkWeekendOrWeekday(dayOfWeek);
        if (isWeekday.equals("평일")) {
            applyWeekdayDiscount(dessertCount);
        }
        if (isWeekday.equals("주말")) {
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

    }
}
