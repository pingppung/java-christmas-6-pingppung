package christmas.view;

import christmas.constants.ConstantsMessage;
import christmas.vo.EligibleEventVO;
import christmas.vo.OrderMenuVO;
import java.util.List;

public class OutputView {
    private static final String MONEY_UNIT = "원";
    private static final String ITEM_QUANTITY_FORMAT = "%s %d개";

    public void printFormattedMessage(String output) {
        System.out.println(output);
    }

    public void printMenu(List<OrderMenuVO> orderMenus) {
        System.out.println(ConstantsMessage.ORDER_MENU.message);
        for (OrderMenuVO menu : orderMenus) {
            System.out.println(menu.toString());
        }
        System.out.println();
    }

    public void printAmount(ConstantsMessage message, String amount) {
        System.out.println(message.message);
        System.out.println(amount + MONEY_UNIT);
        System.out.println();
    }

    public void printGift(String giftProduct, int giftCount) {
        System.out.println(ConstantsMessage.GIFT_MENU.message);
        System.out.println(String.format(ITEM_QUANTITY_FORMAT, giftProduct, giftCount));
        System.out.println();
    }

    public void printEvents(List<EligibleEventVO> events) {
        System.out.println(ConstantsMessage.BENEFIT_LIST.message);
        for (EligibleEventVO event : events) {
            System.out.println(event.toString());
        }
        System.out.println();
    }

    public void printBadge(String badge) {
        System.out.println(ConstantsMessage.EVENT_BADGE);
        System.out.println(badge);
        System.out.println();
    }
}
