package christmas.services.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.enums.EventType;
import christmas.vo.OrderMenuVO;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {

    private DiscountCalculator calculator;
    private List<OrderMenuVO> reserveMenu;
    private int initReservationDate;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator();
        initReservationDate = 25;
        reserveMenu = List.of(
                new OrderMenuVO("양송이수프", 1),
                new OrderMenuVO("시저샐러드", 2),
                new OrderMenuVO("티본스테이크", 1),
                new OrderMenuVO("크리스마스파스타", 2),
                new OrderMenuVO("아이스크림", 3),
                new OrderMenuVO("샴페인", 1)
        );
    }

    @DisplayName("메뉴 주문 후 할인 금액 정상적으로 계산")
    @Test
    public void validCalculateDiscount() {
        List<EventType> events = List.of(
                EventType.CHRISTMAS_DDAY_DISCOUNT,
                EventType.WEEKDAY_DISCOUNT
        );

        int totalBenefit = calculateDiscount(events);
        int expectedTotalBenefit = 9_469;
        assertEquals(expectedTotalBenefit, totalBenefit);
    }

    @DisplayName("크리스마스 디데이 할인 정상적으로 계산")
    @Test
    public void validCalculateDdayDiscount() {
        List<EventType> events = List.of(
                EventType.CHRISTMAS_DDAY_DISCOUNT
        );

        int totalBenefit = calculateDiscount(events);
        int expectedTotalBenefit = 3_400;
        assertEquals(expectedTotalBenefit, totalBenefit);
    }

    @DisplayName("평일 할인 정상적으로 계산")
    @Test
    public void validCalculateWeekDayDiscount() {
        List<EventType> events = List.of(
                EventType.WEEKDAY_DISCOUNT
        );

        int totalBenefit = calculateDiscount(events);
        int expectedTotalBenefit = 6_069;
        assertEquals(expectedTotalBenefit, totalBenefit);
    }

    @DisplayName("주말 할인 정상적으로 계산")
    @Test
    public void validCalculateWeekendDiscount() {
        List<EventType> events = List.of(
                EventType.WEEKEND_DISCOUNT
        );

        int totalBenefit = calculateDiscount(events);
        int expectedTotalBenefit = 6_069;
        assertEquals(expectedTotalBenefit, totalBenefit);
    }


    @DisplayName("특별 할인 정상적으로 계산")
    @Test
    public void validCalculateSpeicalDiscount() {
        List<EventType> events = List.of(
                EventType.SPECIAL_DISCOUNT
        );

        int totalBenefit = calculateDiscount(events);
        int expectedTotalBenefit = 1_000;
        assertEquals(expectedTotalBenefit, totalBenefit);
    }

    @DisplayName("증정품 정상적으로 계산")
    @Test
    public void validCalculateGiftPromotion() {
        List<EventType> events = List.of(
                EventType.GIFT_PROMOTION
        );

        int totalBenefit = calculateDiscount(events);
        int expectedTotalBenefit = 25_000;
        assertEquals(expectedTotalBenefit, totalBenefit);
    }

    private int calculateDiscount(List<EventType> events) {
        calculator.calculateDiscount(initReservationDate, reserveMenu, events);
        return calculator.calculateTotalDiscount();
    }
}
