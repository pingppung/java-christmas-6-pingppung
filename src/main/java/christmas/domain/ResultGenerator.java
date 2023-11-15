package christmas.domain;

import christmas.constants.ConstantsMessage;
import christmas.domain.event.enums.EventDate;
import christmas.domain.event.items.GiftPromotion;
import christmas.utils.Parser;
import christmas.vo.EligibleEventVO;
import christmas.vo.OrderMenuVO;
import christmas.vo.ReservationVO;
import java.util.List;

public class ResultGenerator {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String MONEY_FORMAT = "%s원";
    private static final String ITEM_QUANTITY_FORMAT = "%s %d개";
    private final ReservationVO reservationVO;
    private final Parser parser;

    public ResultGenerator(ReservationVO reservationVO) {
        this.reservationVO = reservationVO;
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
        String giftMenu = "없음";
        if (giftStatus != 0) {
            giftMenu = String.format(ITEM_QUANTITY_FORMAT, GiftPromotion.getGiftItem(),
                    GiftPromotion.getGiftCount());
        }
        result.append(giftMenu).append(LINE_SEPARATOR);
        return result.append("\n").toString();
    }

    public String generateDiscountDetails(List<EligibleEventVO> eligibleEvents) {
        StringBuilder result = new StringBuilder();
        result.append(ConstantsMessage.BENEFIT_LIST.message).append(LINE_SEPARATOR);
        if (!eligibleEvents.isEmpty()) {
            for (EligibleEventVO event : eligibleEvents) {
                result.append(event.toString()).append(LINE_SEPARATOR);
            }
            return result.append("\n").toString();
        }
        result.append("없음").append(LINE_SEPARATOR);
        return result.append("\n").toString();
    }

    public String generateTotalDiscountAmount(int totalDiscount) {
        StringBuilder result = new StringBuilder();
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
        result.append(eventBadgePrompt).append(LINE_SEPARATOR);
        result.append(eventBadge).append(LINE_SEPARATOR);
        return result.append("n").toString();
    }

    public String generateResult() {

        StringBuilder result = new StringBuilder();
        String previewPrompt = String.format(ConstantsMessage.BENEFIT_PREVIEW.message,
                EventDate.CHRISTMAS_DAY.getMonthValue(), reservationVO.reservationDay());
        result.append(previewPrompt).append("\n");
        result.append(generateOrderMenuResult(reservationVO.reservedMenu()));
        result.append(generateTotalOrderAmountResult(reservationVO.totalOrderAmount()));
        result.append(generateTGiftMenuResult(reservationVO.eventResultDTO().getGiftEventDiscount()));
        result.append(generateDiscountDetails(reservationVO.eventResultDTO().getEligibleEvents()));
        result.append(generateTotalDiscountAmount(reservationVO.eventResultDTO().getTotalDiscountAmount()));
        result.append(generatePaymentAmount(reservationVO.paymentAmount()));
        result.append(generateEventBadge(reservationVO.eventResultDTO().getEventBadge()));

        return result.toString();
    }
}


