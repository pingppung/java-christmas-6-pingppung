package christmas;

import christmas.services.date.DateReferee;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        DateReferee dateReferee = new DateReferee();
        List<Integer> days = List.of(25, 26);
        List<Boolean> chkChristmas = days.stream()
                .map(dateReferee::hasChristmasNotPassed)
                .toList();
        System.out.println(chkChristmas);

    }
}
