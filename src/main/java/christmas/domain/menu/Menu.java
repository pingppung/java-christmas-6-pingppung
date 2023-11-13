package christmas.domain.menu;


import christmas.enums.menuItems.Appetizer;
import christmas.enums.menuItems.Beverage;
import christmas.enums.menuItems.Dessert;
import christmas.enums.menuItems.MainCourse;
import christmas.enums.menuItems.MenuItem;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<MenuItem> appetizers;
    private final List<MenuItem> mainCourses;
    private final List<MenuItem> desserts;
    private final List<MenuItem> beverages;

    public Menu() {
        this.appetizers = Arrays.asList(Appetizer.values());
        this.mainCourses = Arrays.asList(MainCourse.values());
        this.desserts = Arrays.asList(Dessert.values());
        this.beverages = Arrays.asList(Beverage.values());
    }

    public String getMenuCategory(String menu) {
        if (isMenuInCategory(menu, appetizers)) {
            return "Appetizer";
        }
        if (isMenuInCategory(menu, mainCourses)) {
            return "Main Course";
        }
        if (isMenuInCategory(menu, desserts)) {
            return "Dessert";
        }
        if (isMenuInCategory(menu, beverages)) {
            return "Beverage";
        }
        return "Unknown";

    }

    private boolean isMenuInCategory(String menu, Iterable<? extends MenuItem> category) {
        for (MenuItem item : category) {
            if (item.getName().equals(menu)) {
                return true;
            }
        }
        return false;
    }
}
