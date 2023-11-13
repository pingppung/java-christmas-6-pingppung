package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.event.items.ChristmasDdayDiscount;
import christmas.domain.event.items.Event;
import christmas.domain.event.items.GiftPromotion;
import christmas.domain.event.items.WeekdayDiscount;
import christmas.domain.event.items.WeekendDiscount;
import christmas.services.date.DateReferee;
import java.time.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JudgementTest {
    private static final int FREE_CHAMPAGNE_THRESHOLD = 120_000;
    private static final int CHAMPAGNE_PRICE = 25_000;
    private DateReferee dateReferee;
    private Judgement judgement;


    @BeforeEach
    void setUp() {
        dateReferee = mock(DateReferee.class);
        judgement = new Judgement(dateReferee);
    }

    @DisplayName("특별 할인이 적용되는지 확인 - 일요일인 경우")
    @Test
    void specialDiscountAppliedOnSunday() {
        when(dateReferee.checkOfWeek()).thenReturn(DayOfWeek.SUNDAY);
        when(dateReferee.isChristmas()).thenReturn(false);
        boolean hasSpecialDiscount = judgement.checkStarInEventCalendar();
        assertTrue(hasSpecialDiscount);
    }

    @DisplayName("특별 할인이 적용되는지 확인 - 크리스마스인 경우")
    @Test
    void specialDiscountAppliedOnChristmas() {
        when(dateReferee.checkOfWeek()).thenReturn(DayOfWeek.MONDAY); // 아무 요일이나 크리스마스가 아닌 경우로 가정
        when(dateReferee.isChristmas()).thenReturn(true);
        boolean hasSpecialDiscount = judgement.checkStarInEventCalendar();
        assertTrue(hasSpecialDiscount);
    }

    @DisplayName("특별 할인이 적용되지 않는 경우 확인")
    @Test
    void specialDiscountNotApplied() {
        when(dateReferee.checkOfWeek()).thenReturn(DayOfWeek.THURSDAY);
        when(dateReferee.isChristmas()).thenReturn(false);

        boolean hasSpecialDiscount = judgement.checkStarInEventCalendar();
        assertFalse(hasSpecialDiscount);
    }

    @DisplayName("크리스마스 D-day 할인 적용 여부 확인 - 크리스마스 당일")
    @Test
    void onChristmasDdayDiscount() {
        when(dateReferee.hasChristmasNotPassed()).thenReturn(true);
        int dday = 0;
        Event event = new ChristmasDdayDiscount(dday);
        int expectedDiscount = 3_400;
        assertThat(event.calculateDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("크리스마스 D-day 할인 적용 여부 확인 - 크리스마스 전")
    @Test
    void beforeChristmasDdayDiscount() {
        when(dateReferee.hasChristmasNotPassed()).thenReturn(true);
        int dday = 15;
        Event event = new ChristmasDdayDiscount(dday);
        int expectedDiscount = 1_900;
        assertThat(event.calculateDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("평일 할인 적용 여부 확인")
    @Test
    void weekdayDiscount() {
        when(dateReferee.checkOfWeek()).thenReturn(DayOfWeek.WEDNESDAY);
        Judgement weekDayJudgement = createTestDateReferee();
        weekDayJudgement.addWeekendOrWeekdayDiscount(2, 3);
        Event event = new WeekdayDiscount(2);
        int expectedDiscount = 2 * 2_023;
        assertThat(event.calculateDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("주말 할인 적용 여부 확인")
    @Test
    void weekendDiscount() {
        when(dateReferee.checkOfWeek()).thenReturn(DayOfWeek.FRIDAY);
        Judgement weekendJudgement = createTestDateReferee();
        weekendJudgement.addWeekendOrWeekdayDiscount(2, 4);
        Event event = new WeekendDiscount(4);
        int expectedDiscount = 4 * 2_023;
        assertThat(event.calculateDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("샴페인 증정이 적용되는지 확인 - 총 주문 금액이 기준 이상인 경우")
    @Test
    void giftPromotionApplied() {
        int totalAmount = FREE_CHAMPAGNE_THRESHOLD + 1;
        boolean hasGiftPromotion = judgement.checkTotalAmount(totalAmount);
        assertTrue(hasGiftPromotion);
    }

    @DisplayName("샴페인 증정이 적용되지 않는지 확인 - 총 주문 금액이 기준 미만인 경우")
    @Test
    void giftPromotionNotApplied() {
        int totalAmount = FREE_CHAMPAGNE_THRESHOLD - 1;
        boolean hasGiftPromotion = judgement.checkTotalAmount(totalAmount);
        assertFalse(hasGiftPromotion);
    }

    @DisplayName("샴페인 증정이 적용되는지 확인 - 총 주문 금액이 기준과 같은 경우")
    @Test
    void giftPromotionAppliedOnThreshold() {
        int totalAmount = FREE_CHAMPAGNE_THRESHOLD;
        boolean hasGiftPromotion = judgement.checkTotalAmount(totalAmount);
        assertTrue(hasGiftPromotion);
    }

    @DisplayName("증정 이벤트 적용될 때 할인 금액 샴페인값 포함되는지 확인")
    @Test
    void calculateDiscountWithGiftPromotion() {
        GiftPromotion giftPromotion = new GiftPromotion();
        int discount = giftPromotion.calculateDiscount();
        assertThat(discount).isEqualTo(CHAMPAGNE_PRICE);
    }

    private Judgement createTestDateReferee() {
        DateReferee dateRefereeTest = DateReferee.create(1);
        return new Judgement(dateRefereeTest);
    }
}
