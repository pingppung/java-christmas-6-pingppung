package christmas.services.order;

import christmas.constants.ErrorMessage;
import christmas.domain.menu.Menu;
import christmas.vo.OrderMenuVO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderValidator {
    private static final int ZERO_ORDER_QUANTITY = 0;
    private static final int MIN_ORDER_QUANTITY = 1;
    private static final int MAX_ORDER_TOTAL_QUANTITY = 20;
    private static final String PATTERN = "^[가-힣]+-[0-9]+$";
    private static final String NONE_CATEGORY = "None";
    private static final String BEVERAGE_CATEGORY = "Beverage";

    public OrderValidator() {

    }

    public void validateMenuExistence(List<OrderMenuVO> orderMenuList) {
        for (OrderMenuVO orderMenu : orderMenuList) {
            String food = orderMenu.menuName();
            if (Menu.getMenuCategory(food).equals(NONE_CATEGORY)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MESSAGE.message);
            }
        }
    }

    public void validateMenuQuantity(List<OrderMenuVO> orderMenuList) {
        for (OrderMenuVO orderMenu : orderMenuList) {
            int quantity = orderMenu.quantity();
            if (quantity < MIN_ORDER_QUANTITY) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MESSAGE.message);
            }
        }
    }

    public int validateQuantityNonNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MESSAGE.message);
        }
    }

    public void validateMenuFormat(String food) {
        if (!food.matches(PATTERN)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MESSAGE.message);
        }
    }

    public void validateDuplicateMenu(List<OrderMenuVO> orderedMenu) {
        Set<OrderMenuVO> uniqueMenuSet = new HashSet<>();
        for (OrderMenuVO menu : orderedMenu) {
            if (!uniqueMenuSet.add(menu)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MESSAGE.message);
            }
        }
    }

    public void validateDrinkOnlyOrder(List<OrderMenuVO> orderedMenu) {
        boolean containsFood = orderedMenu
                .stream()
                .anyMatch(menu -> !isBeverage(menu.menuName()));

        if (!containsFood) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MESSAGE.message);
        }
    }

    private boolean isBeverage(String food) {
        return Menu.getMenuCategory(food).equals(BEVERAGE_CATEGORY);
    }


    public void validateNonZeroOrderQuantity(List<OrderMenuVO> orderedMenu) {
        if (orderedMenu.size() == ZERO_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_MESSAGE.message);
        }
    }

    public void validateMaxOrderQuantity(List<OrderMenuVO> orderedMenu) {
        int totalQuantity = orderedMenu.stream()
                .mapToInt(OrderMenuVO::quantity)
                .sum();
        if (totalQuantity > MAX_ORDER_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.MAX_ORDER_LIMIT_EXCEEDED.message);
        }
    }
}
