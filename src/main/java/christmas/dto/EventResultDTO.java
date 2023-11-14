package christmas.dto;

import christmas.vo.EligibleEventVO;
import java.util.List;

public class EventResultDTO {
    private final List<EligibleEventVO> eligibleEvents;
    private final int totalDiscountAmount;
    private final int giftEventDiscount;

    public EventResultDTO(List<EligibleEventVO> eligibleEvents, int totalDiscountAmount, int giftEventDiscount) {
        this.eligibleEvents = eligibleEvents;
        this.totalDiscountAmount = totalDiscountAmount;
        this.giftEventDiscount = giftEventDiscount;
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

}
