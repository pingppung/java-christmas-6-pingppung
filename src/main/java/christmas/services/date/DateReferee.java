package christmas.services.date;

public class DateReferee {
    public DateReferee() {
    }

    public String checkOfWeek(int date) {
        return "요일";
    }

    public int checkWeekendOrWeekday(int date) {
        return 0;
    }

    public boolean isChristmas(int date) {
        return true;
    }
}
