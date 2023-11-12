package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.services.date.DateReferee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JudgementTest {
    DateReferee dateReferee;
    Judgement judgement;

    @BeforeEach
    void setUp() {
        dateReferee = mock(DateReferee.class);
        judgement = new Judgement(dateReferee);
    }

    @DisplayName("크리스마스 D-day 할인 적용 여부 확인 - 크리스마스 당일")
    @Test
    void onChristmasDdayDiscount() {
        when(dateReferee.hasChristmasNotPassed()).thenReturn(true);
        Event event = new ChristmasDdayDiscount(25);
        int expectedDiscount = 3_400;
        assertThat(event.calculateDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("크리스마스 D-day 할인 적용 여부 확인 - 크리스마스 전")
    @Test
    void beforeChristmasDdayDiscount() {
        when(dateReferee.hasChristmasNotPassed()).thenReturn(true);
        Event event = new ChristmasDdayDiscount(10);
        int expectedDiscount = 1_900;
        assertThat(event.calculateDiscount()).isEqualTo(expectedDiscount);
    }

}
