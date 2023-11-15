package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.services.order.OrderValidator;
import christmas.vo.OrderMenuVO;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderValidatorTest {
    private static final int DEFAULT_DISCOUNT = 0;
    private OrderValidator orderValidator;

    private List<OrderMenuVO> validOrderMenu;
    private List<OrderMenuVO> InvalidOrderMenu;

    @BeforeEach
    public void setUp() {
        orderValidator = new OrderValidator();
        validOrderMenu = new ArrayList<>();
        InvalidOrderMenu = new ArrayList<>();
        setOrderMenu();
    }

    private void setOrderMenu() {
        validOrderMenu.add(new OrderMenuVO("타파스", 2));
        validOrderMenu.add(new OrderMenuVO("제로콜라", 3));

        InvalidOrderMenu.add(new OrderMenuVO("제로콜라", 2));
        InvalidOrderMenu.add(new OrderMenuVO("InvalidMenu", 3));
    }

    @DisplayName("메뉴 존재 여부 검증 - 주문이 유효한 경우")
    @Test
    public void validateMenuExistence_ValidOrder() {
        orderValidator.validateMenuExistence(validOrderMenu);
    }

    @DisplayName("메뉴 존재 여부 검증 - 주문이 유효하지 않은 경우")
    @Test
    public void invalidateMenuExistence_InvalidOrder() {
        assertThatThrownBy(() ->
                orderValidator.validateMenuExistence(InvalidOrderMenu)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }


    @DisplayName("주문 최대 수량 검증 - 최대 허용 수량 이하인 경우")
    @Test
    public void validateMaxOrderQuantity_ValidQuantity() {
        orderValidator.validateMaxOrderQuantity(validOrderMenu);
    }

    @DisplayName("주문 최대 수량 검증 - 최대 허용 수량 초과한 경우")
    @Test
    public void invalidateMaxOrderQuantity_InvalidQuantity() {
        validOrderMenu.add(new OrderMenuVO("ICE_CREAM", 16));
        assertThatThrownBy(() ->
                orderValidator.validateMaxOrderQuantity(validOrderMenu)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 주문은 최대 20개까지 가능합니다. 다시 입력해 주세요.");
    }
}
