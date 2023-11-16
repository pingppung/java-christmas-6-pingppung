package christmas.utils;

import christmas.services.order.OrderValidator;
import christmas.vo.OrderMenuVO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String FOOD_SEPARATOR = ",";
    private static final String MENU_ITEM_SEPARATOR = "-";
    private static final String MONEY_CURRENCY_PATTERN = "###,###";
    private final List<OrderMenuVO> orderDetails;
    private final OrderValidator orderValidator;

    public Parser() {
        this.orderDetails = new ArrayList<>();
        this.orderValidator = new OrderValidator();
    }

    public List<OrderMenuVO> parserOrder(String input) {
        String[] menus = input.split(FOOD_SEPARATOR);
        for (String menu : menus) {
            menu = menu.trim();
            orderValidator.validateMenuFormat(menu);
            String[] parts = menu.split(MENU_ITEM_SEPARATOR);
            String menuName = parts[0];
            int quantity = convertToInt(parts[1]);
            OrderMenuVO orderMenu = new OrderMenuVO(menuName, quantity);
            orderDetails.add(orderMenu);
        }
        validateOrderDetails();
        return orderDetails;
    }

    private Integer convertToInt(String number) {
        return orderValidator.validateQuantityNonNumeric(number);
    }

    public static String formatCurrency(int amount, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(amount);
    }

    public static String formatMoneyCurrency(int amount) {
        return formatCurrency(amount, MONEY_CURRENCY_PATTERN);
    }

    public void validateOrderDetails() {
        orderValidator.validateMenuExistence(orderDetails);
        orderValidator.validateMenuQuantity(orderDetails);
        orderValidator.validateDuplicateMenu(orderDetails);
        orderValidator.validateDrinkOnlyOrder(orderDetails);
        orderValidator.validateNonZeroOrderQuantity(orderDetails);
        orderValidator.validateMaxOrderQuantity(orderDetails);
    }
}
