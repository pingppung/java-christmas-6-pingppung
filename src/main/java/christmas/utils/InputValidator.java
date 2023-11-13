package christmas.utils;

import christmas.domain.menu.Menu;
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

    public void validateDuplicateMenu(List<String> orderedMenu) {
        Set<String> uniqueMenuSet = new HashSet<>();
        for (String menu : orderedMenu) {
            if (!uniqueMenuSet.add(menu)) {
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        }
    }

    public void validateDrinkOnlyOrder(List<String> orderedMenu) {
        boolean containsFood = orderedMenu
                .stream()
                .anyMatch(menu -> !isBeverage(menu));

        if (!containsFood) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private boolean isBeverage(String food) {
        return menu.getMenuCategory(food).equals("Beverage");
    }


    public void validateMenuQuantity(List<String> orderedMenu) {
        if (orderedMenu.size() == ZERO_ORDER_QUANTITY) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    //    - [ERROR] "주문은 최대 20개까지 가능합니다. 다시 입력해 주세요."
    public void validateMaxOrderQuantity(List<String> orderedMenu) {
        // 한 번에 최대 20개까지만 주문 가능한 경우 주문 총 개수에 대한 로직
    }
}
