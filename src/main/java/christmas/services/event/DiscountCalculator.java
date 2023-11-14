package christmas.services.event;

import christmas.domain.event.items.Event;
import christmas.domain.menu.Menu;
import christmas.dto.EventParamsDTO;
import christmas.enums.EventType;
import christmas.vo.EligibleEventVO;
import christmas.vo.OrderMenuVO;
import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator {
    private static Menu menu;

    public DiscountCalculator() {
        this.menu = new Menu();
    }

    private static List<EligibleEventVO> eligibleEvents = new ArrayList<>();

    public static List<EligibleEventVO> calculateDiscount(int reserveDate, List<OrderMenuVO> reserveMenu,
                                                          List<EventType> events) {
        int dessertCount = findMenuCount(reserveMenu, "Dessert");
        int mainCount = findMenuCount(reserveMenu, "MainFood");
        EventParamsDTO params = new EventParamsDTO(reserveDate, dessertCount, mainCount);

        for (EventType type : events) {
            Event event = type.createEvent(params);
            int discount = event.calculateDiscount();
            addEligibleEvent(new EligibleEventVO(event.getEventType(), discount));
        }

        return eligibleEvents;
    }

    public static void addEligibleEvent(EligibleEventVO event) {
        eligibleEvents.add(event);
    }

    private static int findMenuCount(List<OrderMenuVO> reserveMenu, String menuType) {
        long count = reserveMenu.stream()
                .filter(menuItem -> menu.getMenuCategory(menuItem.menuName()).equals(menuType))
                .count();
        return (int) count;
    }
}
