package christmas.services.date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DateRefereeTest {
    private DateReferee dateReferee;

    @BeforeEach
    void setUp() {
        dateReferee = new DateReferee();
    }

    @DisplayName("특정 일자의 정확한 요일 확인")
    @Test
    void checkOfWeekForCorrectDay() {
        int dayOfWeek = dateReferee.checkOfWeek(16);
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.SATURDAY.getValue());
    }

    @DisplayName("특정 일자의 틀린 요일 확인")
    @Test
    void checkOfWeekForIncorrectDay() {
        int dayOfWeek = dateReferee.checkOfWeek(16);
        assertThat(dayOfWeek).isNotEqualTo(DayOfWeek.MONDAY.getValue());
    }

    @DisplayName("특정 일자의 평일(일, 월, 화, 수, 목) 확인")
    @Test
    void checkWeekday() {
        int dayOfWeek = dateReferee.checkOfWeek(17);
        String weekday = dateReferee.checkWeekendOrWeekday(dayOfWeek); //2023-12-17 일요일
        assertThat(weekday).isEqualTo("평일");
    }

    @DisplayName("특정 일자의 주말(금, 토) 확인")
    @Test
    void checkWeekend() {
        int dayOfWeek = dateReferee.checkOfWeek(16);
        String weekend = dateReferee.checkWeekendOrWeekday(dayOfWeek); //2023-12-16 토요일
        assertThat(weekend).isEqualTo("주말");
    }

    @DisplayName("크리스마스 경계값-25일 입력")
    @Test
    void hasChristmasNotPassedFor25() {
        assertTrue(dateReferee.hasChristmasNotPassed(25));
    }

    @DisplayName("크리스마스 경계값-26일 입력")
    @Test
    void hasChristmasPassedFor26() {
        assertFalse(dateReferee.hasChristmasNotPassed(26));
    }
}
