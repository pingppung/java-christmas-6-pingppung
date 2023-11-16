package christmas.services.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.vo.OrderMenuVO;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AmountCalculatorTest {
    private AmountCalculator amountCalculator;
    private List<OrderMenuVO> reserveMenu;

    @BeforeEach
    public void setUp() {
        amountCalculator = new AmountCalculator();
        reserveMenu = List.of(
                new OrderMenuVO("양송이수프", 1),
                new OrderMenuVO("티본스테이크", 1),
                new OrderMenuVO("크리스마스파스타", 2),
                new OrderMenuVO("아이스크림", 1),
                new OrderMenuVO("샴페인", 1)
        );
    }

    @DisplayName("총 주문 금액 계산 테스트")
    @Test
    public void calculateTotalOrderAmount() {
        int totalAmount = amountCalculator.calculateTotalOrderAmount(reserveMenu);
        int expectedTotalAmount = 6_000 + 55_000 + (25_000 * 2) + 5_000 + 25_000;
        assertEquals(expectedTotalAmount, totalAmount);
    }


    @DisplayName("할인 후 결제 금액 계산 테스트")
    @Test
    public void getDiscountedPaymentAmount() {
        int totalOrderAmount = 10_000;
        int totalDiscountAmount = 2_000;
        int giftEventDiscount = 0;
        int discountedPaymentAmount = amountCalculator.getDiscountedPaymentAmount(totalOrderAmount,
                totalDiscountAmount, giftEventDiscount);
        int expectedPaymentAmount = 8_000;
        assertEquals(expectedPaymentAmount, discountedPaymentAmount);
    }
}
