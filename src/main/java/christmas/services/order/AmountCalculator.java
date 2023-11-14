package christmas.services.order;

import christmas.vo.OrderMenuVO;
import java.util.List;

public class AmountCalculator {
    public int calculateTotalOrderAmount(List<OrderMenuVO> orderMenus) {
        int totalAmount = 0;
        for (OrderMenuVO orderMenu : orderMenus) {
        }
        return totalAmount;
    }

    public int getDiscountedPaymentAmount(int totalOrderAmount, int totalDiscountAmount) {
        return totalOrderAmount - totalDiscountAmount;
    }
}
