package christmas.controller;

import christmas.constants.ConstantsMessage;
import christmas.domain.PreviewResult;
import christmas.domain.event.enums.EventDate;
import christmas.dto.EventResultDTO;
import christmas.services.date.DateValidator;
import christmas.services.order.AmountCalculator;
import christmas.services.order.InputHandler;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.OrderMenuVO;
import christmas.vo.ReservationVO;
import java.util.List;

public class ReservationController {
    private final OutputView outputView;
    private final InputView inputView;
    private final EventController eventController;
    private final AmountCalculator amountCalculator;


    public ReservationController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        eventController = new EventController();
        amountCalculator = new AmountCalculator();
    }

    public void reserve() {
        outputView.printFormattedMessage(
                String.format(ConstantsMessage.GREETING.message, EventDate.CHRISTMAS_DAY.getMonthValue()));

        int reservationDay = InputHandler.retryInputOnInvalid(this::getReservationDay);
        List<OrderMenuVO> reservedMenu = InputHandler.retryInputOnInvalid(this::getReservedMenu);
        int totalOrderAmount = calculateTotalOrderAmount(reservedMenu);

        EventResultDTO eventResultDTO = eventController.applyEvent(totalOrderAmount, reservationDay, reservedMenu);
        ReservationVO reservationVO = createReservationVO(reservationDay, reservedMenu, totalOrderAmount,
                eventResultDTO);
        generateReservationResult(reservationVO);
    }

    private int getReservationDay() {
        String inputDate = inputView.getVisitDate().trim();
        int reservationDate = DateValidator.validateDateNonNumeric(inputDate);
        DateValidator.validateDateRange(reservationDate);
        return reservationDate;
    }

    private List<OrderMenuVO> getReservedMenu() {
        String inputMenu = inputView.getOrderMenu();
        Parser parser = new Parser();
        return parser.parserOrder(inputMenu);
    }

    public int calculateTotalOrderAmount(List<OrderMenuVO> reservedMenu) {
        return amountCalculator.calculateTotalOrderAmount(reservedMenu);
    }

    public int calculatePaymentAmount(int totalOrderAmount, int totalDiscountAmount, int giftEventDiscount) {
        return amountCalculator.getDiscountedPaymentAmount(totalOrderAmount, totalDiscountAmount, giftEventDiscount);
    }

    private ReservationVO createReservationVO(int reservationDay, List<OrderMenuVO> reservedMenu, int totalOrderAmount,
                                              EventResultDTO eventResultDTO) {
        int paymentAmount = calculatePaymentAmount(totalOrderAmount, eventResultDTO.getTotalDiscountAmount(),
                eventResultDTO.getGiftEventDiscount());
        return ReservationVO.create(reservationDay, reservedMenu, eventResultDTO, totalOrderAmount, paymentAmount);
    }

    private void generateReservationResult(ReservationVO reservationVO) {
        PreviewResult previewResult = new PreviewResult();
        outputView.displayReservationResult(previewResult.generateResult(reservationVO));
    }
}
