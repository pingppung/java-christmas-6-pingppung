package christmas.controller;

import christmas.view.InputView;

public class EventController {
    private static InputView inputView;

    public EventController() {
        inputView = new InputView();
    }

    private void reserveDate() {
        String inputDate = inputView.getVisitDate();
    }

    private void reserveMenu() {
        String inputMenu = inputView.getOrderMenu();
    }
}
