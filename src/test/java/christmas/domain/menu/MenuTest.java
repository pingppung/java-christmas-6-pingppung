package christmas.domain.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.menu.items.Appetizer;
import christmas.domain.menu.items.Beverage;
import christmas.domain.menu.items.Dessert;
import christmas.domain.menu.items.MainDish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {

    @DisplayName("메뉴 카테고리 확인 테스트")
    @Test
    void getMenuCategoryTest() {
        assertEquals("Appetizer", Menu.getMenuCategory(Appetizer.CAESAR_SALAD.getName()));
        assertEquals("MainDish", Menu.getMenuCategory(MainDish.BBQ_RIB.getName()));
        assertEquals("Dessert", Menu.getMenuCategory(Dessert.ICE_CREAM.getName()));
        assertEquals("Beverage", Menu.getMenuCategory(Beverage.ZERO_COLA.getName()));
        assertEquals("None", Menu.getMenuCategory("InvalidMenuName"));
    }

    @DisplayName("메뉴 가격 확인 테스트")
    @Test
    void getMenuPriceTest() {
        assertEquals(8_000, Menu.getMenuPrice(Appetizer.CAESAR_SALAD.getName(), "Appetizer"));
        assertEquals(54_000, Menu.getMenuPrice(MainDish.BBQ_RIB.getName(), "MainDish"));
        assertEquals(5_000, Menu.getMenuPrice(Dessert.ICE_CREAM.getName(), "Dessert"));
        assertEquals(3_000, Menu.getMenuPrice(Beverage.ZERO_COLA.getName(), "Beverage"));
        assertEquals(-1, Menu.getMenuPrice("InvalidMenuName", "InvalidMenuType"));
    }
}
