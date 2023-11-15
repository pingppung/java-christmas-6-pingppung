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
    private final Parser parser;

    public ResultGenerator() {
        this.parser = new Parser();
    }

    public String generateOrderMenuResult(List<OrderMenuVO> orderMenu) {
        StringBuilder result = new StringBuilder();
        result.append(ConstantsMessage.ORDER_MENU.message).append(LINE_SEPARATOR);
        for (OrderMenuVO menu : orderMenu) {
            result.append(menu.toString()).append(LINE_SEPARATOR);
        }
        return result.append("\n").toString();
    }

    public String generateTotalOrderAmountResult(int total) {
        StringBuilder result = new StringBuilder();
        result.append(ConstantsMessage.TOTAL_ORDER_AMOUNT.message).append(LINE_SEPARATOR);
        String totalOrderAmount = String.format(MONEY_FORMAT, parser.formatMoneyCurrency(total));
        result.append(totalOrderAmount).append(LINE_SEPARATOR);
        return result.append("\n").toString();
    }

    public String generateTGiftMenuResult(int giftStatus) {
        StringBuilder result = new StringBuilder();
        result.append(ConstantsMessage.GIFT_MENU.message).append(LINE_SEPARATOR);
        if (giftStatus == 0) {
            return result.append("없음").append(LINE_SEPARATOR).toString();
        }
        String giftMenu = String.format(ITEM_QUANTITY_FORMAT, GiftPromotion.getGiftItem(),
                GiftPromotion.getGiftCount());
        result.append(giftMenu).append(LINE_SEPARATOR);
        return result.append("\n").toString();
    }

    public String generateDiscountDetails(List<EligibleEventVO> eligibleEvents) {
        StringBuilder result = new StringBuilder();
        result.append(ConstantsMessage.BENEFIT_LIST.message).append(LINE_SEPARATOR);
        if (eligibleEvents.isEmpty()) {
            return result.append("없음").append(LINE_SEPARATOR).toString();
        }
        for (EligibleEventVO event : eligibleEvents) {
            result.append(event.toString()).append(LINE_SEPARATOR);
        }
        return result.append("\n").toString();
    }

    public String generateTotalDiscountAmount(int totalDiscount) {
        StringBuilder result = new StringBuilder();
        if (totalDiscount == 0) {
            return result.append("없음").append(LINE_SEPARATOR).toString();
        }
        result.append(ConstantsMessage.TOTAL_BENEFIT_AMOUNT.message).append(LINE_SEPARATOR);
        String totalDiscountAmount = String.format(MONEY_FORMAT, parser.formatMoneyCurrency(-totalDiscount));
        result.append(totalDiscountAmount).append(LINE_SEPARATOR);
        return result.append("\n").toString();
    }

    public String generatePaymentAmount(int paymentAmount) {
        StringBuilder result = new StringBuilder();
        result.append(ConstantsMessage.FINAL_PAYMENT_AMOUNT.message).append(LINE_SEPARATOR);
        String totalDiscountAmount = String.format(MONEY_FORMAT, parser.formatMoneyCurrency(paymentAmount));
        result.append(totalDiscountAmount).append(LINE_SEPARATOR);
        return result.append("\n").toString();
    }

    public String generateEventBadge(String eventBadge) {
        StringBuilder result = new StringBuilder();
        String eventBadgePrompt = String.format(ConstantsMessage.EVENT_BADGE.message,
                EventDate.CHRISTMAS_DAY.getMonthValue());
        if (eventBadge == null) {
            return result.append("없음").append(LINE_SEPARATOR).toString();
        }
        result.append(eventBadgePrompt).append(LINE_SEPARATOR);
        result.append(eventBadge).append(LINE_SEPARATOR);
        return result.append("n").toString();
    }


}


