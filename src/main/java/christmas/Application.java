package christmas;

import christmas.services.date.DateCalculator;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        DateCalculator dateCalculator = new DateCalculator();
        List<Integer> days = List.of(5, 25, 30);
        List<Integer> dday = days.stream()
                .map(dateCalculator::countDday)
                .toList();
        System.out.println(dday);
    }
}
