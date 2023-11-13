package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.enums.ConstantsMessage;

public class InputView {
    public String getVisitDate() {
        return inputValue(ConstantsMessage.INPUT_VISIT_DATE);
    }

    public String getOrderMenu() {
        return inputValue(ConstantsMessage.INPUT_ORDER_MENU);
    }

    private String inputValue(ConstantsMessage prompt) {
        System.out.println(prompt.getMessage());
        return Console.readLine();
    }
}
