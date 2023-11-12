package christmas;

import christmas.domain.event.Judgement;
import christmas.services.date.DateReferee;

public class Application {
    public static void main(String[] args) {
        int day = 3;
        DateReferee dateReferee = DateReferee.create(day);
        Judgement judgement = new Judgement(dateReferee, day, 2, 1, 110_000);
    }
}
