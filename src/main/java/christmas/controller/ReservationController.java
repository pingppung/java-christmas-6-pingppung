package christmas.controller;

import christmas.utils.InputHandler;
import christmas.view.InputView;

public class ReservationController {
    private static InputView inputView;
    private static InputHandler inputHandler;

    public ReservationController() {
        inputView = new InputView();
        inputHandler = new InputHandler();
    }

    public void reserve() {
        String reserveDate = inputHandler.retryInputOnInvalid(this::inputReserveDate);
        String reserveMenu = inputHandler.retryInputOnInvalid(this::inputReserveMenu);
    }


    private String inputReserveDate() {
        String inputDate = inputView.getVisitDate();
        return inputDate;
    }

    private String inputReserveMenu() {
        String inputMenu = inputView.getOrderMenu();
        return inputMenu;
    }


}
