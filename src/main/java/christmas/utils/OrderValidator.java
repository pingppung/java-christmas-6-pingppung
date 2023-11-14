package christmas.utils;

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
    private static final String INVALID_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_LIMIT_EXCEEDED_MESSAGE = "[ERROR] 주문은 최대 20개까지 가능합니다. 다시 입력해 주세요.";

    private final Menu menu;

    public OrderValidator() {
        menu = new Menu();
    }

    public void validateMenuExistence(List<OrderMenuVO> orderMenuList) {
        for (OrderMenuVO orderMenu : orderMenuList) {
            String food = orderMenu.menuName();
            if (menu.getMenuCategory(food).equals(NONE_CATEGORY)) {
                throw new IllegalArgumentException("존재 x");
            }
        }
    }

    public void validateMenuQuantity(List<OrderMenuVO> orderMenuList) {
        for (OrderMenuVO orderMenu : orderMenuList) {
            int quantity = orderMenu.quantity();
            if (quantity < MIN_ORDER_QUANTITY) {
                throw new IllegalArgumentException("메뉴의 개수는 1 이상의 숫자");
            }
        }
    }

    public int validateQuantityNonNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("개수에 문자입력");
        }
    }

    public void validateMenuFormat(String food) {
        if (!food.matches(PATTERN)) {
            throw new IllegalArgumentException("메뉴 입력 형식 문제");
        }
    }

    public void validateDuplicateMenu(List<OrderMenuVO> orderedMenu) {
        Set<OrderMenuVO> uniqueMenuSet = new HashSet<>();
        for (OrderMenuVO menu : orderedMenu) {
            if (!uniqueMenuSet.add(menu)) {
                throw new IllegalArgumentException("중복 메뉴");
            }
        }
    }

    public void validateDrinkOnlyOrder(List<OrderMenuVO> orderedMenu) {
        boolean containsFood = orderedMenu
                .stream()
                .anyMatch(menu -> !isBeverage(menu.menuName()));

        if (!containsFood) {
            throw new IllegalArgumentException("음료수만 안됨");
        }
    }

    private boolean isBeverage(String food) {
        return menu.getMenuCategory(food).equals(BEVERAGE_CATEGORY);
    }


    public void validateNonZeroOrderQuantity(List<OrderMenuVO> orderedMenu) {
        if (orderedMenu.size() == ZERO_ORDER_QUANTITY) {
            throw new IllegalArgumentException("아무것도 주문 안함");
        }
    }

    public void validateMaxOrderQuantity(List<OrderMenuVO> orderedMenu) {
        int totalQuantity = orderedMenu.stream()
                .mapToInt(OrderMenuVO::quantity)
                .sum();
        if (totalQuantity > MAX_ORDER_TOTAL_QUANTITY) {
            throw new IllegalArgumentException("최대 주문 20개 넘음");
        }
    }
}
