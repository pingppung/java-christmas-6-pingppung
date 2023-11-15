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

        StringBuilder result = new StringBuilder();
        String previewPrompt = String.format(ConstantsMessage.BENEFIT_PREVIEW.message,
                EventDate.CHRISTMAS_DAY.getMonthValue(), reservationVO.reservationDay());
        result.append(previewPrompt).append("\n");
        result.append(resultGenerator.generateOrderMenuResult(reservationVO.reservedMenu()));
        result.append(resultGenerator.generateTotalOrderAmountResult(reservationVO.totalOrderAmount()));

        result.append(resultGenerator.generateTGiftMenuResult(reservationVO.eventResultDTO().getGiftEventDiscount()));
        result.append(resultGenerator.generateDiscountDetails(reservationVO.eventResultDTO().getEligibleEvents()));
        result.append(
                resultGenerator.generateTotalDiscountAmount(reservationVO.eventResultDTO().getTotalDiscountAmount()));

        result.append(resultGenerator.generatePaymentAmount(reservationVO.paymentAmount()));

        result.append(resultGenerator.generateEventBadge(reservationVO.eventResultDTO().getEventBadge()));

        return result.toString();
    }
}
