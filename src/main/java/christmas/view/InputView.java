package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.enums.ConstantsMessage;
import christmas.enums.EventDate;

public class InputView {
    public String getVisitDate() {
        String format = String.format(ConstantsMessage.INPUT_VISIT_DATE.message,
                EventDate.CHRISTMAS_DAY.getMonthValue());
        return inputValue(format);
    }

    public String getOrderMenu() {
        return inputValue(ConstantsMessage.INPUT_ORDER_MENU.message);
    }

    private String inputValue(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
