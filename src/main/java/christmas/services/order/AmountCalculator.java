package christmas.services.order;

import christmas.domain.menu.Menu;
import christmas.vo.OrderMenuVO;
import java.util.List;

public class AmountCalculator {

    public AmountCalculator() {
    }

    public int calculateTotalOrderAmount(List<OrderMenuVO> orderMenus) {
        int totalAmount = 0;
        for (OrderMenuVO orderMenu : orderMenus) {
            String menuType = Menu.getMenuCategory(orderMenu.menuName());
            int price = Menu.getMenuPrice(orderMenu.menuName(), menuType);
            int quantity = orderMenu.quantity();
            totalAmount += price * quantity;
        }
        return totalAmount;
    }

    public int getDiscountedPaymentAmount(int totalOrderAmount, int totalDiscountAmount, int giftEventDiscount) {
        return totalOrderAmount - totalDiscountAmount + giftEventDiscount;
    }
}
