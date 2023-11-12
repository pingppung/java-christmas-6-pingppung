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
        dateReferee = DateReferee.create(1);
    }

    @DisplayName("특정 일자의 정확한 요일 확인")
    @Test
    void checkOfWeekForCorrectDay() {
        DayOfWeek dayOfWeek = dateReferee.checkOfWeek();
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.FRIDAY);
    }

    @DisplayName("특정 일자의 틀린 요일 확인")
    @Test
    void checkOfWeekForIncorrectDay() {
        DayOfWeek dayOfWeek = dateReferee.checkOfWeek();
        assertThat(dayOfWeek).isNotEqualTo(DayOfWeek.MONDAY);
    }

    @DisplayName("특정 일자의 평일(일, 월, 화, 수, 목) 확인")
    @Test
    void checkWeekday() {
        DayOfWeek dayOfWeek = DayOfWeek.WEDNESDAY;
        String weekday = dateReferee.checkWeekendOrWeekday(dayOfWeek);
        assertThat(weekday).isEqualTo("평일");
    }

    @DisplayName("특정 일자의 주말(금, 토) 확인")
    @Test
    void checkWeekend() {
        DayOfWeek dayOfWeek = DayOfWeek.FRIDAY;
        String weekend = dateReferee.checkWeekendOrWeekday(dayOfWeek);
        assertThat(weekend).isEqualTo("주말");
    }

    @DisplayName("크리스마스 경계값-25일 입력")
    @Test
    void hasChristmasNotPassedFor25() {
        DateReferee christmas = DateReferee.create(25);
        assertTrue(christmas.hasChristmasNotPassed());
    }

    @DisplayName("크리스마스 경계값-26일 입력")
    @Test
    void hasChristmasPassedFor26() {
        DateReferee afterChristmas = DateReferee.create(26);
        assertFalse(afterChristmas.hasChristmasNotPassed());
    }
}
