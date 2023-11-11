package christmas;

import christmas.services.date.DateReferee;

public class Application {
    public static void main(String[] args) {
        DateReferee dateReferee = new DateReferee();
        String dayOfWeek = dateReferee.checkOfWeek(12);
        System.out.println(dayOfWeek);
    }
}
