package christmas.view;

import christmas.enums.ConstantsMessage;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();

    //기본 메시지 출력 메소드
    public void printMessage(ConstantsMessage message) {
    }

    //주문 메뉴 출력
    public void printMenu(List<Map<String, Integer>> orderMenus) {

    }

    //금액 관련 출력
    public void printAmount(ConstantsMessage message, int amount) {

    }

    //증정 메뉴 출력
    public void printGift() {

    }

    //배지 출력
    public void printBadge(String badge) {

    }
}
