package christmas.domain.menu;

import christmas.domain.menu.items.Appetizer;
import christmas.domain.menu.items.Beverage;
import christmas.domain.menu.items.Dessert;
import christmas.domain.menu.items.MainDish;
import christmas.domain.menu.items.MenuItem;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private static final List<MenuItem> appetizers = Arrays.asList(Appetizer.values());
    private static final List<MenuItem> mainCourses = Arrays.asList(MainDish.values());
    private static final List<MenuItem> desserts = Arrays.asList(Dessert.values());
    private static final List<MenuItem> beverages = Arrays.asList(Beverage.values());

    private Menu() {
    }

    public static String getMenuCategory(String menu) {
        if (isMenuInCategory(menu, appetizers)) {
            return "Appetizer";
        }
        if (isMenuInCategory(menu, mainCourses)) {
            return "MainDish";
        }
        if (isMenuInCategory(menu, desserts)) {
            return "Dessert";
        }
        if (isMenuInCategory(menu, beverages)) {
            return "Beverage";
        }
        return "None";
    }

    private static boolean isMenuInCategory(String menu, Iterable<? extends MenuItem> category) {
        for (MenuItem item : category) {
            if (item.getName().equals(menu)) {
                return true;
            }
        }
        return false;
    }

    public static int getMenuPrice(String menuName, String menuType) {
        List<MenuItem> category;
        switch (menuType) {
            case "Appetizer":
                category = appetizers;
                break;
            case "MainDish":
                category = mainCourses;
                break;
            case "Dessert":
                category = desserts;
                break;
            case "Beverage":
                category = beverages;
                break;
            default:
                return -1;
        }
        return getPrice(menuName, category);
    }

    private static int getPrice(String menuName, List<MenuItem> category) {
        for (MenuItem item : category) {
            if (item.getName().equals(menuName)) {
                return item.getPrice();
            }
        }
        return -1;
    }
}
