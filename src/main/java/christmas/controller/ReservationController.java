package christmas.controller;

import christmas.constants.ConstantsMessage;
import christmas.domain.event.enums.EventDate;
import christmas.dto.EventResultDTO;
import christmas.services.order.AmountCalculator;
import christmas.utils.DateValidator;
import christmas.utils.InputHandler;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.OrderMenuVO;
import christmas.vo.ReservationVO;
import java.util.List;

public class ReservationController {
    private final OutputView outputView;
    private final InputView inputView;
    private final InputHandler inputHandler;
    private final DateValidator dateValidator;
    private final EventController eventController;
    private final AmountCalculator amountCalculator;


    public ReservationController() {
        inputView = new InputView();
        outputView = new OutputView();
        inputHandler = new InputHandler();
        dateValidator = new DateValidator();
        eventController = new EventController();
        amountCalculator = new AmountCalculator();
    }

    public void reserve() {
        outputView.printFormattedMessage(
                String.format(ConstantsMessage.GREETING.message, EventDate.CHRISTMAS_DAY.getMonthValue()));

        int reservationDay = inputHandler.retryInputOnInvalid(this::getReservationDay);
        List<OrderMenuVO> reservedMenu = inputHandler.retryInputOnInvalid(this::getReservedMenu);
        int totalOrderAmount = calculateTotalOrderAmount(reservedMenu);

        EventResultDTO eventResultDTO = eventController.start(reservationDay, reservedMenu, totalOrderAmount);
        ReservationVO reservationVO = createReservationVO(reservationDay, reservedMenu, totalOrderAmount,
                eventResultDTO);

        outputView.printReservation(reservationVO);
    }

    private int getReservationDay() {
        String inputDate = inputView.getVisitDate();
        int reservationDate = dateValidator.validateDateNonNumeric(inputDate);
        dateValidator.validateDateRange(reservationDate);
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
        return ReservationVO.create(reservationDay, reservedMenu, eventResultDTO.getEligibleEvents(), totalOrderAmount,
                eventResultDTO.getTotalDiscountAmount(), paymentAmount);
    }
}
