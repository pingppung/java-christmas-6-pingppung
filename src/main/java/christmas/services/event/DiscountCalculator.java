package christmas.services.event;

import christmas.domain.event.enums.EventType;
import christmas.domain.event.items.Event;
import christmas.domain.menu.Menu;
import christmas.dto.EventParamsDTO;
import christmas.vo.EligibleEventVO;
import christmas.vo.OrderMenuVO;
import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator {
    private static final String DESSERT_TYPE = "Dessert";
    private static final String MAIN_DISH_TYPE = "MainDish";
    private final List<EligibleEventVO> eligibleEvents;

    public DiscountCalculator() {
        this.eligibleEvents = new ArrayList<>();
    }

    public List<EligibleEventVO> calculateDiscount(int reserveDate, List<OrderMenuVO> reserveMenu,
                                                   List<EventType> events) {
        EventParamsDTO params = createEventParams(reserveDate, reserveMenu);

        for (EventType type : events) {
            Event event = type.createEvent(params);
            int discount = event.calculateDiscount();
            if (discount != 0) {
                addEligibleEvent(new EligibleEventVO(event.getEventType(), discount));
            }
        }
        return eligibleEvents;
    }

    public int calculateTotalDiscount() {
        return eligibleEvents.stream()
                .mapToInt(EligibleEventVO::discount)
                .sum();
    }

    private EventParamsDTO createEventParams(int reserveDate, List<OrderMenuVO> reserveMenu) {
        int dessertCount = findMenuCount(reserveMenu, DESSERT_TYPE);
        int mainCount = findMenuCount(reserveMenu, MAIN_DISH_TYPE);
        return new EventParamsDTO(reserveDate, dessertCount, mainCount);
    }

    private void addEligibleEvent(EligibleEventVO event) {
        eligibleEvents.add(event);
    }

    private int findMenuCount(List<OrderMenuVO> reserveMenu, String menuType) {
        long count = reserveMenu.stream()
                .filter(menuItem -> Menu.getMenuCategory(menuItem.menuName()).equals(menuType))
                .mapToInt(OrderMenuVO::quantity)
                .sum();
        return (int) count;
    }

    public int getGiftPromotionDiscount() {
        return eligibleEvents.stream()
                .filter(event -> event.type() == EventType.GIFT_PROMOTION)
                .mapToInt(EligibleEventVO::discount)
                .findFirst()
                .orElse(0);
    }
}
