package christmas.view;

import christmas.enums.ConstantsMessage;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();

    public void printMessage(ConstantsMessage message) {
        System.out.println(message.getMessage());
    }

    public void printMenu(List<Map<String, Integer>> orderMenus) {
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append(ConstantsMessage.ORDER_MENU);
        for (Map<String, Integer> menu : orderMenus) {
            menuBuilder.append(menu).append(NEWLINE);
        }
        menuBuilder.append(NEWLINE);
        System.out.println(menuBuilder);
    }

    public void printAmount(ConstantsMessage message, int amount) {
        System.out.println(message.getMessage());
        System.out.println(amount + "원");
    }

    //증정 메뉴 출력
    public void printGift(String giftProduct) {
        System.out.println(ConstantsMessage.GIFT_MENU);
        System.out.println(giftProduct + "1개");
    }

    //배지 출력
    public void printBadge(String badge) {
        System.out.println(ConstantsMessage.EVENT_BADGE);
        System.out.println(badge);
    }
}
