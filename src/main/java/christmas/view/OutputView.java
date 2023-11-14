package christmas.view;

import christmas.constants.ConstantsMessage;
import christmas.domain.event.enums.EventDate;
import christmas.domain.event.items.GiftPromotion;
import christmas.utils.Parser;
import christmas.vo.EligibleEventVO;
import christmas.vo.OrderMenuVO;
import christmas.vo.ReservationVO;
import java.util.List;

public class OutputView {
    private static final String MONEY_UNIT = "원";
    private static final String ITEM_QUANTITY_FORMAT = "%s %d개";

    public void printFormattedMessage(String output) {
        System.out.println(output);
    }

    public void printReservation(ReservationVO reservationVO) {
        int reservationDay = reservationVO.reservationDay();
        printBenefitPreview(reservationDay);
        printReservationDetails(reservationVO);
    }

    private void printBenefitPreview(int reservationDay) {
        String format = String.format(ConstantsMessage.BENEFIT_PREVIEW.message, EventDate.CHRISTMAS_DAY.getMonthValue(),
                reservationDay);
        printFormattedMessage(format);
        System.out.println();
    }

    private void printReservationDetails(ReservationVO reservationVO) {
        printMenu(reservationVO.reservedMenu());
        printTotalOrderAmount(reservationVO);
        printGiftPromotion();
        printEvents(reservationVO.eligibleEvents());
        printTotalBenefitAmount(reservationVO);
        printFinalPaymentAmount(reservationVO);
    }

    public void printMenu(List<OrderMenuVO> orderMenus) {
        System.out.println(ConstantsMessage.ORDER_MENU.message);
        for (OrderMenuVO menu : orderMenus) {
            System.out.println(menu.toString());
        }
        System.out.println();
    }

    private void printTotalOrderAmount(ReservationVO reservationVO) {
        int totalOrderAmount = reservationVO.totalOrderAmount();
        printAmount(ConstantsMessage.TOTAL_ORDER_AMOUNT, Parser.formatMoneyCurrency(totalOrderAmount));
    }

    public void printGiftPromotion() {
        GiftPromotion event = new GiftPromotion();
        System.out.println(ConstantsMessage.GIFT_MENU.message);
        System.out.println(String.format(ITEM_QUANTITY_FORMAT, event.getGiftItem(), event.getGiftCount()));
        System.out.println();
    }

    public void printEvents(List<EligibleEventVO> events) {
        System.out.println(ConstantsMessage.BENEFIT_LIST.message);
        for (EligibleEventVO event : events) {
            System.out.println(event.toString());
        }
        System.out.println();
    }

    private void printTotalBenefitAmount(ReservationVO reservationVO) {
        int totalDiscountAmount = reservationVO.totalDiscountAmount();
        printAmount(ConstantsMessage.TOTAL_BENEFIT_AMOUNT, Parser.formatMoneyCurrency(-totalDiscountAmount));
    }

    private void printFinalPaymentAmount(ReservationVO reservationVO) {
        int paymentAmount = reservationVO.paymentAmount();
        printAmount(ConstantsMessage.FINAL_PAYMENT_AMOUNT, Parser.formatMoneyCurrency(paymentAmount));
    }

    public void printAmount(ConstantsMessage message, String amount) {
        System.out.println(message.message);
        System.out.println(amount + MONEY_UNIT);
        System.out.println();
    }

    public void printBadge(String badge) {
        System.out.println(ConstantsMessage.EVENT_BADGE);
        System.out.println(badge);
        System.out.println();
    }
}
