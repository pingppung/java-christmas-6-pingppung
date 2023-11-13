package christmas.utils;

import java.util.List;

public class InputValidator {


    //    - [ERROR] "유효하지 않은 날짜입니다. 다시 입력해 주세요."
    public void validateDateRange(int day) {
        // 1 이상 31 이하의 숫자가 아닌 경우 날짜에 대한 로직
    }

    //    - [ERROR] "유효하지 않은 주문입니다. 다시 입력해 주세요."
    public void validateMenuExistence(String menu) {
        // 메뉴판에 없는 메뉴를 입력하는 경우에 대한 로직
    }

    public void validateMenuQuantity(int quantity) {
        // 메뉴 개수 1 이상의 숫자가 아닌 경우에 대한 로직
    }

    public void validateMenuFormat(String menu) {
        // 메뉴 형식이 예시와 다른 경우에 대한 로직
    }

    public void validateDuplicateMenu(List<String> orderedMenu) {
        // 동일한 메뉴를 중복으로 입력한 경우에 대한 로직
    }

    public void validateDrinkOnlyOrder(List<String> orderedMenu) {
        // 주문한 메뉴 중 음료만 주문한 경우에 대한 로직
    }

    //    - [ERROR] "주문은 최대 20개까지 가능합니다. 다시 입력해 주세요."
    public void validateMaxOrderQuantity(List<String> orderedMenu) {
        // 한 번에 최대 20개까지만 주문 가능한 경우 주문 총 개수에 대한 로직
    }
}
