package christmas.domain;

import christmas.constants.ConstantsMessage;
import christmas.domain.event.enums.EventDate;
import christmas.vo.ReservationVO;

public class PreviewResult {
    private final ResultGenerator resultGenerator;

    public PreviewResult() {
        resultGenerator = new ResultGenerator();
    }

    public String generateResult(ReservationVO reservationVO) {
        String previewPrompt = String.format(ConstantsMessage.BENEFIT_PREVIEW.message,
                EventDate.CHRISTMAS_DAY.getMonthValue(), reservationVO.reservationDay());

        return new StringBuilder()
                .append(previewPrompt).append("\n\n")
                .append(resultGenerator.generateOrderMenuResult(reservationVO.reservedMenu()))
                .append(resultGenerator.generateTotalOrderAmountResult(reservationVO.totalOrderAmount()))
                .append(resultGenerator.generateGiftMenuResult(reservationVO.eventResultDTO().getGiftEventDiscount()))
                .append(resultGenerator.generateDiscountDetails(reservationVO.eventResultDTO().getEligibleEvents()))
                .append(resultGenerator.generateTotalDiscountAmount(
                        reservationVO.eventResultDTO().getTotalDiscountAmount()))
                .append(resultGenerator.generatePaymentAmount(reservationVO.paymentAmount()))
                .append(resultGenerator.generateEventBadge(reservationVO.eventResultDTO().getEventBadge()))
                .toString();
    }
}
