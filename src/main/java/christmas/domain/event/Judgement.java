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
        hasWeekdayDiscount();
        hasWeekendDiscount();
        hasSpecialDiscount();
    }

    private void hasChristmasDdayDiscount(int day) {

    }

    private void hasWeekdayDiscount() {
    }

    private void hasWeekendDiscount() {

    }

    public void hasSpecialDiscount() {

    }
}
