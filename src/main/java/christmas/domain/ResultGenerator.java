package christmas.domain;

import christmas.constants.ConstantsMessage;
import christmas.domain.event.enums.EventDate;
import christmas.domain.event.items.GiftPromotion;
import christmas.utils.Parser;
import christmas.vo.EligibleEventVO;
import christmas.vo.OrderMenuVO;
import java.util.List;

public class ResultGenerator {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String MONEY_FORMAT = "%s원";
    private static final String ITEM_QUANTITY_FORMAT = "%s %d개";

    public ResultGenerator() {

    }

    public String generateOrderMenuResult(List<OrderMenuVO> orderMenu) {
        return generateMessage(ConstantsMessage.ORDER_MENU.message, formatMenuDetails(orderMenu));
    }

    public String generateTotalOrderAmountResult(int total) {
        return generateMessage(ConstantsMessage.TOTAL_ORDER_AMOUNT.message,
                formatMoney(total)) + "\n";
    }

    public String generateGiftMenuResult(int giftStatus) {
        if (giftStatus == 0) {
            return generateMessage(ConstantsMessage.GIFT_MENU.message, "없음\n");
        }
        return generateMessage(ConstantsMessage.GIFT_MENU.message,
                String.format(ITEM_QUANTITY_FORMAT, GiftPromotion.getGiftItem(), GiftPromotion.getGiftCount())) + "\n";
    }

    public String generateDiscountDetails(List<EligibleEventVO> eligibleEvents) {
        if (eligibleEvents.isEmpty()) {
            return generateMessage(ConstantsMessage.BENEFIT_LIST.message, "없음\n");
        }
        return generateMessage(ConstantsMessage.BENEFIT_LIST.message, formatEligibleEvents(eligibleEvents));
    }

    public String generateTotalDiscountAmount(int totalDiscount) {
        if (totalDiscount == 0) {
            return generateMessage(ConstantsMessage.TOTAL_BENEFIT_AMOUNT.message, "없음\n");
        }
        return generateMessage(ConstantsMessage.TOTAL_BENEFIT_AMOUNT.message, formatMoney(-totalDiscount)) + "\n";
    }

    public String generatePaymentAmount(int paymentAmount) {
        return generateMessage(ConstantsMessage.FINAL_PAYMENT_AMOUNT.message,
                formatMoney(paymentAmount)) + "\n";
    }

    public String generateEventBadge(String eventBadge) {
        String eventBadgePrompt = String.format(ConstantsMessage.EVENT_BADGE.message,
                EventDate.CHRISTMAS_DAY.getMonthValue());
        if (eventBadge == null) {
            return generateMessage(eventBadgePrompt, "없음\n");
        }
        return generateMessage(eventBadgePrompt, eventBadge) + "\n";
    }

    private String generateMessage(String message, String content) {
        return String.format("%s%s%s\n", message, LINE_SEPARATOR, content);
    }

    private String formatMoney(int amount) {
        return String.format(MONEY_FORMAT, Parser.formatMoneyCurrency(amount));
    }

    private String formatMenuDetails(List<OrderMenuVO> orderMenu) {
        StringBuilder menuDetails = new StringBuilder();
        for (OrderMenuVO menu : orderMenu) {
            menuDetails.append(menu.toString()).append(LINE_SEPARATOR);
        }
        return menuDetails.toString();
    }

    private String formatEligibleEvents(List<EligibleEventVO> eligibleEvents) {
        StringBuilder eventDetails = new StringBuilder();
        for (EligibleEventVO event : eligibleEvents) {
            eventDetails.append(event.toString()).append(LINE_SEPARATOR);
        }
        return eventDetails.toString();
    }
}


