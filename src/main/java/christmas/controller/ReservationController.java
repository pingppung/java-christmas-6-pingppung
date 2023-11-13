package christmas.controller;

import christmas.utils.DateValidator;
import christmas.utils.InputHandler;
import christmas.utils.OrderValidator;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.vo.OrderMenuVO;
import java.util.List;

public class ReservationController {
    private final InputView inputView;
    private final InputHandler inputHandler;
    private final DateValidator dateValidator;
    private final OrderValidator orderValidator;

    public ReservationController() {
        inputView = new InputView();
        inputHandler = new InputHandler();
        dateValidator = new DateValidator();
        orderValidator = new OrderValidator();
    }

    public void reserve() {
        String reserveDate = inputHandler.retryInputOnInvalid(this::getReserveDate);
        List<OrderMenuVO> reserveMenu = inputHandler.retryInputOnInvalid(this::getReserveMenu);
    }


    private String getReserveDate() {
        String inputDate = inputView.getVisitDate();
        int reservationDate = dateValidator.validateDateNonNumeric(inputDate);
        dateValidator.validateDateRange(reservationDate);
        return inputDate;
    }

    private List<OrderMenuVO> getReserveMenu() {
        String inputMenu = inputView.getOrderMenu();
        Parser parser = new Parser(orderValidator);
        List<OrderMenuVO> orderMenu = parser.parserOrder(inputMenu);
        parser.validateOrderDetails();
        return orderMenu;

    }


}
