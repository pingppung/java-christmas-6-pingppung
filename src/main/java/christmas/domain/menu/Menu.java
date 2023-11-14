package christmas.domain.menu;


import christmas.domain.menu.items.Appetizer;
import christmas.domain.menu.items.Beverage;
import christmas.domain.menu.items.Dessert;
import christmas.domain.menu.items.MainDish;
import christmas.domain.menu.items.MenuItem;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<MenuItem> appetizers;
    private final List<MenuItem> mainCourses;
    private final List<MenuItem> desserts;
    private final List<MenuItem> beverages;

    public Menu() {
        this.appetizers = Arrays.asList(Appetizer.values());
        this.mainCourses = Arrays.asList(MainDish.values());
        this.desserts = Arrays.asList(Dessert.values());
        this.beverages = Arrays.asList(Beverage.values());
    }

    public String getMenuCategory(String menu) {
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

    private boolean isMenuInCategory(String menu, Iterable<? extends MenuItem> category) {
        for (MenuItem item : category) {
            if (item.getName().equals(menu)) {
                return true;
            }
        }
        return false;
    }

    public int getMenuPrice(String menuName, String menuType) {
        if ("Appetizer".equals(menuType)) {
            return getPrice(menuName, appetizers);
        }
        if ("MainDish".equals(menuType)) {
            return getPrice(menuName, mainCourses);
        }
        if ("Dessert".equals(menuType)) {
            return getPrice(menuName, desserts);
        }
        if ("Beverage".equals(menuType)) {
            return getPrice(menuName, beverages);
        }
        return -1;
    }

    private int getPrice(String menuName, List<MenuItem> category) {
        for (MenuItem item : category) {
            if (item.getName().equals(menuName)) {
                return item.getPrice();
            }
        }
        return -1;
    }
}
