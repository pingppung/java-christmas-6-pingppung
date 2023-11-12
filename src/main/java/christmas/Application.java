package christmas;

import christmas.domain.event.Judgement;
import christmas.services.date.DateReferee;

public class Application {
    public static void main(String[] args) {
        int day = 20;
        DateReferee dateReferee = DateReferee.create(day);
        Judgement judgement = new Judgement(dateReferee, day);
    }
}
