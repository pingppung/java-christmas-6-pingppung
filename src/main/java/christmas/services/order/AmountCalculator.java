package christmas.services.order;

import christmas.domain.menu.Menu;
import christmas.vo.OrderMenuVO;
import java.util.List;

public class AmountCalculator {
    public int calculateTotalOrderAmount(List<OrderMenuVO> orderMenus) {
        int totalAmount = 0;
        Menu menu = new Menu();
        for (OrderMenuVO orderMenu : orderMenus) {
            String menuType = menu.getMenuCategory(orderMenu.menuName());
            int price = menu.getMenuPrice(orderMenu.menuName(), menuType);
            int quantity = orderMenu.quantity();
            totalAmount += price * quantity;
        }
        return totalAmount;
    }

    public int getDiscountedPaymentAmount(int totalOrderAmount, int totalDiscountAmount) {
        return totalOrderAmount - totalDiscountAmount;
    }
}
