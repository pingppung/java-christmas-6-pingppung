package christmas.services.date;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateCalculatorTest {
    private DateCalculator dateCalculator;
    private LocalDate christmasDate;

    @BeforeEach
    void setUp() {
        dateCalculator = DateCalculator.create();
        christmasDate = LocalDate.of(2023, 12, 25);
    }

    private int initializeReservationDate(int day) {
        LocalDate reservationDate = LocalDate.of(2023, 12, day);
        return dateCalculator.countDday(day);
    }

    @DisplayName("크리스마스까지의 디데이를 정확히 계산")
    @Test
    void countDdayForChristmas() {
        int dday = dateCalculator.countDday(12);
        LocalDate reservationDate = LocalDate.of(2023, 12, 12);
        long expectedDday = ChronoUnit.DAYS.between(reservationDate, christmasDate);
        assertThat(dday).isEqualTo((int) expectedDday);
    }

    @DisplayName("크리스마스 이전 날짜의 디데이 계산하여 양수가 나오는지 확인")
    @Test
    void countDdayForPreChristmas() {
        int dday = initializeReservationDate(20);
        assertThat(dday).isPositive();
    }

    @DisplayName("크리스마스 당일 디데이 계산하여 0이 나오는지 확인")
    @Test
    void countDdayForSameDay() {
        int dday = initializeReservationDate(25);
        assertThat(dday).isEqualTo(0);
    }

    @DisplayName("크리스마스 지난 날짜의 디데이 계산하여 음수가 나오는지 확인")
    @Test
    void countDdayForPastChristmas() {
        int dday = initializeReservationDate(30);
        assertThat(dday).isNegative();
    }
}
