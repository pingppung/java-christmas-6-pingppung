package christmas.utils;

import christmas.domain.menu.Menu;
import christmas.vo.OrderMenuVO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputValidator {
    private static final int ZERO_ORDER_QUANTITY = 0;
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private static final int MIN_ORDER_QUANTITY = 1;
    private static final int MAX_ORDER_TOTAL_QUANTITY = 20;

    private static final String menuPattern = "^[가-힣]+-[0-9]+$";
    private static final String UNKNOWN_CATEGORY = "NONE";
    private static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_LIMIT_EXCEEDED_ERROR_MESSAGE = "[ERROR] 주문은 최대 20개까지 가능합니다. 다시 입력해 주세요.";
    private final Menu menu;

    public InputValidator() {
        menu = new Menu();
    }

    public int validateNonNumeric(String input, String error) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(error);
        }
    }

    public int validateDateNonNumeric(String input) {
        return validateNonNumeric(input, INVALID_DATE_MESSAGE);
    }

    public void validateDateRange(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    public void validateMenuExistence(String food) {
        if (menu.getMenuCategory(food).equals(UNKNOWN_CATEGORY)) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    public void validateMenuQuantity(int quantity) {
        if (quantity > MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    public int validateQuantityNonNumeric(String input) {
        return validateNonNumeric(input, INVALID_ORDER_MESSAGE);
    }

    public void validateMenuFormat(String food) {
        if (!food.matches(menuPattern)) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    public void validateDuplicateMenu(List<OrderMenuVO> orderedMenu) {
        Set<OrderMenuVO> uniqueMenuSet = new HashSet<>();
        for (OrderMenuVO menu : orderedMenu) {
            if (!uniqueMenuSet.add(menu)) {
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        }
    }

    public void validateDrinkOnlyOrder(List<OrderMenuVO> orderedMenu) {
        boolean containsFood = orderedMenu
                .stream()
                .anyMatch(menu -> !isBeverage(menu.menuName()));

        if (!containsFood) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private boolean isBeverage(String food) {
        return menu.getMenuCategory(food).equals("Beverage");
    }


    public void validateMenuQuantity(List<OrderMenuVO> orderedMenu) {
        if (orderedMenu.size() == ZERO_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    public void validateMaxOrderQuantity(List<OrderMenuVO> orderedMenu) {
        int totalQuantity = orderedMenu.stream()
                .mapToInt(OrderMenuVO::quantity)
                .sum();
        if (totalQuantity > MAX_ORDER_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(ORDER_LIMIT_EXCEEDED_ERROR_MESSAGE);
        }
    }
}
