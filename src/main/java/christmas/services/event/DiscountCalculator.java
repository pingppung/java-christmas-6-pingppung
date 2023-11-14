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
    private final Menu menu;
    private final List<EligibleEventVO> eligibleEvents;

    public DiscountCalculator() {
        this.menu = new Menu();
        eligibleEvents = new ArrayList<>();
    }

    public List<EligibleEventVO> calculateDiscount(int reserveDate, List<OrderMenuVO> reserveMenu,
                                                   List<EventType> events) {

        EventParamsDTO params = createEventParams(reserveDate, reserveMenu);

        for (EventType type : events) {
            Event event = type.createEvent(params);
            int discount = event.calculateDiscount();
            addEligibleEvent(new EligibleEventVO(event.getEventType(), discount));
        }

        return eligibleEvents;
    }

    public int calculateTotalBenefit(List<EligibleEventVO> eligibleEvents) {
        return eligibleEvents.stream()
                .mapToInt(EligibleEventVO::discount)
                .sum();
    }

    private EventParamsDTO createEventParams(int reserveDate, List<OrderMenuVO> reserveMenu) {
        int dessertCount = findMenuCount(reserveMenu, "Dessert");
        int mainCount = findMenuCount(reserveMenu, "MainDish");
        return new EventParamsDTO(reserveDate, dessertCount, mainCount);
    }

    private void addEligibleEvent(EligibleEventVO event) {
        eligibleEvents.add(event);
    }

    private int findMenuCount(List<OrderMenuVO> reserveMenu, String menuType) {
        long count = reserveMenu.stream()
                .filter(menuItem -> menu.getMenuCategory(menuItem.menuName()).equals(menuType))
                .mapToInt(OrderMenuVO::quantity)
                .sum();
        return (int) count;
    }
}
