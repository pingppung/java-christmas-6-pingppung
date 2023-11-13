package christmas.utils;

import christmas.vo.OrderMenuVO;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String FOOD_SEPARATOR = ",";
    private static final String MENU_ITEM_SEPARATOR = "-";
    private final List<OrderMenuVO> orderDetails;

    private final OrderValidator orderValidator;

    public Parser(OrderValidator orderValidator) {
        this.orderDetails = new ArrayList<>();
        this.orderValidator = orderValidator;
    }

    public List<OrderMenuVO> parserOrder(String input) {
        String[] menus = input.split(FOOD_SEPARATOR);
        for (String menu : menus) {
            String[] parts = menu.split(MENU_ITEM_SEPARATOR);
            String menuName = parts[0];
            int quantity = convertToInt(parts[1]);
            OrderMenuVO orderMenu = new OrderMenuVO(menuName, quantity);
            orderDetails.add(orderMenu);
        }
        return orderDetails;
    }

    //메뉴에 대한 개수에 대해서 예외처리
    private Integer convertToInt(String number) {
        return orderValidator.validateQuantityNonNumeric(number);
    }

}
