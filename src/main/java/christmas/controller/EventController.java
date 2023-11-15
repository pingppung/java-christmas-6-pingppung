package christmas.controller;

import christmas.domain.event.Badge;
import christmas.domain.event.Judgement;
import christmas.domain.event.enums.EventType;
import christmas.dto.EventResultDTO;
import christmas.services.date.DateReferee;
import christmas.services.event.DiscountCalculator;
import christmas.vo.EligibleEventVO;
import christmas.vo.OrderMenuVO;
import java.util.List;

public class EventController {
    private final DiscountCalculator discountCalculator;

    public EventController() {
        discountCalculator = new DiscountCalculator();
    }

    public EventResultDTO start(int reservationDay, List<OrderMenuVO> reservedMenu, int totalOrderAmount) {
        List<EventType> events = findEligibleEvents(reservationDay, totalOrderAmount);
        List<EligibleEventVO> eligibleEvents = calculateEligibleEventDiscount(reservationDay, reservedMenu, events);
        int totalDiscountAmount = discountCalculator.calculateTotalDiscount();
        int giftEventDiscount = discountCalculator.getGiftPromotionDiscount();

        Badge badge = new Badge(totalDiscountAmount);
        String badgeType = badge.getBadgeType();

        return new EventResultDTO(eligibleEvents, totalDiscountAmount, giftEventDiscount, badgeType);
    }

    public List<EventType> findEligibleEvents(int reservationDay, int totalAmount) {
        DateReferee dateReferee = DateReferee.create(reservationDay);
        Judgement judgement = new Judgement(dateReferee);
        return judgement.processEligibilityEvents(reservationDay, totalAmount);
    }

    public List<EligibleEventVO> calculateEligibleEventDiscount(int reservationDate, List<OrderMenuVO> reservedMenu,
                                                                List<EventType> events) {
        return discountCalculator.calculateDiscount(reservationDate, reservedMenu, events);
    }
}
