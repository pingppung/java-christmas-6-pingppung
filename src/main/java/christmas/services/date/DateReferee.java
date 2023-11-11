package christmas.services.date;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateReferee {
    private final static int year = 2023;
    private final static int month = 12;

    public DateReferee() {
    }

    public int checkOfWeek(int day) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        //월-1 / 화-2 / 수-3 / 목-4 / 금-5 / 토-6 / 일-7
        return dayOfWeek.getValue();
    }

    public String checkWeekendOrWeekday(int dayOfWeek) {
        if (dayOfWeek == 5 || dayOfWeek == 6) { //금, 토
            return "주말";
        }
        return "평일"; //나머지 요일
    }

    public boolean isChristmas(int day) {
        return true;
    }
}
