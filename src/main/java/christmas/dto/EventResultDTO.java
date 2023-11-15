package christmas.dto;

import christmas.vo.EligibleEventVO;
import java.util.List;

public class EventResultDTO {
    private final List<EligibleEventVO> eligibleEvents;
    private final int totalDiscountAmount;
    private final int giftEventDiscount;
    private final String badge;

    public EventResultDTO(List<EligibleEventVO> eligibleEvents, int totalDiscountAmount, int giftEventDiscount,
                          String badge) {
        this.eligibleEvents = eligibleEvents;
        this.totalDiscountAmount = totalDiscountAmount;
        this.giftEventDiscount = giftEventDiscount;
        this.badge = badge;
    }

    public List<EligibleEventVO> getEligibleEvents() {
        return eligibleEvents;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public int getGiftEventDiscount() {
        return giftEventDiscount;
    }

    public String getEventBadge() {
        return badge;
    }
}
