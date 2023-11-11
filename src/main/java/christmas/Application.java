package christmas;

import christmas.services.date.DateReferee;

public class Application {
    public static void main(String[] args) {
        DateReferee dateReferee = new DateReferee();
        int weekendDayOfWeek = dateReferee.checkOfWeek(8);
        String isWeekend = dateReferee.checkWeekendOrWeekday(weekendDayOfWeek);
        System.out.println(isWeekend);

        int weekdayDayOfWeek = dateReferee.checkOfWeek(25);
        String isWeekday = dateReferee.checkWeekendOrWeekday(weekdayDayOfWeek);
        System.out.println(isWeekday);
    }
}
