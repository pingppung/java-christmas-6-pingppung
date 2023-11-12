package christmas.domain.event;

import christmas.services.date.DateReferee;

public class Judgement {
    private final DateReferee dateReferee;

    //날짜에 대한 이벤트 조건 판단
    public Judgement(DateReferee dateReferee, int day) {
        this.dateReferee = dateReferee;
        isEligibleForEvent(day);
    }

    public void isEligibleForEvent(int day) {
        hasChristmasDdayDiscount(day);
//        hasWeekdayDiscount();
//        hasWeekendDiscount();
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

    private void hasWeekdayDiscount() {
    }

    private void hasWeekendDiscount() {

    }

    public void hasSpecialDiscount() {

    }
}
