package christmas.utils;

import christmas.vo.OrderMenuVO;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String FOOD_SEPARATOR = ",";
    private static final String MENU_ITEM_SEPARATOR = "-";
    private final List<OrderMenuVO> orderDetails;

    public Parser() {
        orderDetails = new ArrayList<>();
    }

    public void parseOrder(String input) {
        String[] menus = input.split(FOOD_SEPARATOR);
        for (String menu : menus) {
            String[] parts = menu.split(MENU_ITEM_SEPARATOR);
            String menuName = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            OrderMenuVO orderMenu = new OrderMenuVO(menuName, quantity);
            orderDetails.add(orderMenu);
        }
    }
}
